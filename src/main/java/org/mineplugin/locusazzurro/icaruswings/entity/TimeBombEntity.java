package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.damage.DamageTimeRift;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.OptionalInt;
import java.util.UUID;

public class TimeBombEntity extends Entity {

    private static final EntityDataAccessor<Integer> LIFE = SynchedEntityData.defineId(TimeBombEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MAX_LIFE = SynchedEntityData.defineId(TimeBombEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<OptionalInt> ATTACHED_TO_TARGET = SynchedEntityData.defineId(TimeBombEntity.class, EntityDataSerializers.OPTIONAL_UNSIGNED_INT);
    private float damage;
    private float range;
    private int life = 0;
    private int maxLife;
    private float initialAngle = 0;
    private boolean pulsing;
    private UUID attachedUUID;
    private int attachedNetworkId;


    public TimeBombEntity(EntityType<? extends TimeBombEntity> type, Level world) {
        super(type, world);
    }

    public TimeBombEntity(Level worldIn, Entity holder, float damage, float range, int maxLife){
        this(EntityTypeRegistry.timeBombEntity.get(), worldIn);
        this.damage = damage;
        this.range = range;
        this.maxLife = maxLife;
        this.entityData.set(MAX_LIFE, maxLife);
        this.initialAngle = holder.yRot;
        this.setAttachedTo(holder);
        this.moveToAttached();
    }

    @Override
    public void tick(){
        super.tick();
        this.moveToAttached();
        this.tickLife();

        if(level.isClientSide()){
            level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0,0,0);
            if (this.life >= this.maxLife){
                level.addParticle(ParticleRegistry.timeRiftExplosion.get(), this.getX(), this.getY(), this.getZ(), 0,0,0);
            }
        }

        if (this.life >= this.maxLife){
            level.playSound(null, this.getX(), this.getY(),this.getZ(),
                    SoundRegistry.timeRiftCollapse.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
            this.explode();
        }

        if (pulsing && this.life % 5 == 0){
            Entity source = this.getAttachedTo();
            TimeRiftParticleEntity particle;
            for (int i = 0; i < 8; i++) {
                if (source instanceof net.minecraft.world.entity.LivingEntity) {
                    particle = new TimeRiftParticleEntity((net.minecraft.world.entity.LivingEntity) source, level);
                } else {
                    particle = new TimeRiftParticleEntity(this.getX(), this.getY(), this.getZ(), level);
                }
                particle.setNoGravity(true);
                particle.shootFromRotation(this, 0f, this.initialAngle + 45 * i + life * 2,  0.0f, 0.5f, 0.0f);
                level.addFreshEntity(particle);
            }
        }
    }


    private void explode(){

        float r = this.range;
        if (this.getAttachedTo() != null) {
            Entity attachedTo = this.getAttachedTo();
            AABB aabb = new AABB(r, r, r, -r, -r, -r).move(attachedTo.position());
            List<LivingEntity> entities = attachedTo.level.getEntitiesOfClass(net.minecraft.world.entity.LivingEntity.class, aabb);
            for (net.minecraft.world.entity.LivingEntity entity : entities) {
                entity.hurt(new DamageTimeRift(attachedTo), this.damage);
            }
        }
        this.remove();
    }

    private void tickLife(){
        this.life = entityData.get(LIFE);
        this.maxLife = this.entityData.get(MAX_LIFE);
        this.life++;
        this.entityData.set(LIFE, life);
    }

    private void moveToAttached(){
        this.entityData.get(ATTACHED_TO_TARGET).ifPresent((e) -> {
            Entity attachedTo = this.level.getEntity(e);
            if (attachedTo != null)
            this.setPos(attachedTo.getX(), attachedTo.getY(), attachedTo.getZ());
        });
    }

    private void setAttachedTo(@Nullable net.minecraft.world.entity.Entity attachedTo){
        if (attachedTo != null) {
            this.attachedUUID = attachedTo.getUUID();
            this.attachedNetworkId = attachedTo.getId();
            this.entityData.set(ATTACHED_TO_TARGET, OptionalInt.of(attachedTo.getId()));
        }
    }

    @Nullable
    public net.minecraft.world.entity.Entity getAttachedTo() {
        if (this.attachedUUID != null && this.level instanceof ServerLevel) {
            return ((ServerLevel)this.level).getEntity(this.attachedUUID);
        } else {
            return this.attachedNetworkId != 0 ? this.level.getEntity(this.attachedNetworkId) : null;
        }
    }

    public void setPulsing(){
        this.pulsing = true;
    }

    @Override
    public boolean isPickable(){
        return false;
    }

    @Override
    public void defineSynchedData() {
        this.entityData.define(LIFE, 0);
        this.entityData.define(MAX_LIFE, 0);
        this.entityData.define(ATTACHED_TO_TARGET, OptionalInt.empty());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if(nbt.hasUUID("Victim")){
            this.attachedUUID = nbt.getUUID("Victim");
        }
        this.damage = nbt.getFloat("Damage");
        this.range = nbt.getFloat("Range");
        this.life = nbt.getInt("Life");
        this.maxLife = nbt.getInt("MaxLife");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        if(this.attachedUUID != null){
            nbt.putUUID("Victim", this.attachedUUID);
        }
        nbt.putFloat("Damage", this.damage);
        nbt.putFloat("Range", this.range);
        nbt.putInt("Life", this.life);
        nbt.putInt("MaxLife", this.maxLife);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

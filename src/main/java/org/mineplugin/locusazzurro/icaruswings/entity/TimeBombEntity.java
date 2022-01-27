package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.damage.DamageTimeRift;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.OptionalInt;
import java.util.UUID;

public class TimeBombEntity extends Entity {

    private static final DataParameter<Integer> LIFE = EntityDataManager.defineId(TimeBombEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> MAX_LIFE = EntityDataManager.defineId(TimeBombEntity.class, DataSerializers.INT);
    private static final DataParameter<OptionalInt> ATTACHED_TO_TARGET = EntityDataManager.defineId(TimeBombEntity.class, DataSerializers.OPTIONAL_UNSIGNED_INT);
    private float damage;
    private float range;
    private int life = 0;
    private int maxLife;
    private float initialAngle = 0;
    private boolean pulsing;
    private UUID attachedUUID;
    private int attachedNetworkId;


    public TimeBombEntity(EntityType<? extends TimeBombEntity> type, World world) {
        super(type, world);
    }

    public TimeBombEntity(World worldIn, Entity holder, float damage, float range, int maxLife){
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
                    SoundRegistry.timeRiftCollapse.get(), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            this.explode();
        }

        if (pulsing && this.life % 2 == 0){
            Entity source = this.getAttachedTo();
            TimeRiftParticleEntity particle;
            for (int i = 0; i < 16; i++) {
                if (source instanceof LivingEntity) {
                    particle = new TimeRiftParticleEntity((LivingEntity) source, level);
                } else {
                    particle = new TimeRiftParticleEntity(this.getX(), this.getY(), this.getZ(), level);
                }
                particle.setNoGravity(true);
                particle.shootFromRotation(this, 0f, (float)(this.initialAngle + 22.5 * i + life * 2),  0.0f, 0.5f, 0.0f);
                level.addFreshEntity(particle);
            }
        }
    }


    private void explode(){

        float r = this.range;
        if (this.getAttachedTo() != null) {
            Entity attachedTo = this.getAttachedTo();
            AxisAlignedBB aabb = new AxisAlignedBB(r, r, r, -r, -r, -r).move(attachedTo.position());
            List<LivingEntity> entities = attachedTo.level.getEntitiesOfClass(LivingEntity.class, aabb);
            for (LivingEntity entity : entities) {
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

    private void setAttachedTo(@Nullable Entity attachedTo){
        if (attachedTo != null) {
            this.attachedUUID = attachedTo.getUUID();
            this.attachedNetworkId = attachedTo.getId();
            this.entityData.set(ATTACHED_TO_TARGET, OptionalInt.of(attachedTo.getId()));
        }
    }

    @Nullable
    public Entity getAttachedTo() {
        if (this.attachedUUID != null && this.level instanceof ServerWorld) {
            return ((ServerWorld)this.level).getEntity(this.attachedUUID);
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
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        if(nbt.hasUUID("Victim")){
            this.attachedUUID = nbt.getUUID("Victim");
        }
        this.damage = nbt.getFloat("Damage");
        this.range = nbt.getFloat("Range");
        this.life = nbt.getInt("Life");
        this.maxLife = nbt.getInt("MaxLife");
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        if(this.attachedUUID != null){
            nbt.putUUID("Victim", this.attachedUUID);
        }
        nbt.putFloat("Damage", this.damage);
        nbt.putFloat("Range", this.range);
        nbt.putInt("Life", this.life);
        nbt.putInt("MaxLife", this.maxLife);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

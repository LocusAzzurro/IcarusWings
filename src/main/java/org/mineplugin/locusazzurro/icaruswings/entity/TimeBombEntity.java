package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.damage.DamageTimeRift;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TimeBombEntity extends Entity {

    private float damage = 100f;
    private float range = 4.0f;
    private int life = 0;
    private int maxLife = 100;
    private UUID attachedUUID;
    private int attachedNetworkId;


    public TimeBombEntity(EntityType<? extends TimeBombEntity> type, World world) {
        super(type, world);
    }

    public TimeBombEntity(World worldIn, Entity victim, float damage, float range, int maxLife){
        this(EntityTypeRegistry.timeBombEntity.get(), worldIn);
        this.damage = damage;
        this.range = range;
        this.maxLife = maxLife;
        this.setAttachedTo(victim);
        this.moveToVictim(victim);
    }

    @Override
    public void tick(){
        super.tick();
        this.moveToVictim(this.getAttachedTo());

        if(level.isClientSide()){
            level.addParticle(ParticleTypes.SMOKE,this.getX(), this.getY(), this.getZ(), 0,0,0);
        }

        if (this.life >= this.maxLife){
            this.explode();
        }
        this.life++;

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

    private void moveToVictim(Entity victim){
        if (victim != null){
            this.setPos(victim.getX(), victim.getY(), victim.getZ());
        }
    }

    private void setAttachedTo(@Nullable Entity attachedTo){
        if (attachedTo != null) {
            this.attachedUUID = attachedTo.getUUID();
            this.attachedNetworkId = attachedTo.getId();
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

    @Override
    public boolean isPickable(){
        return false;
    }

    @Override
    protected void defineSynchedData() {

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

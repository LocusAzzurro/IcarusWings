package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class TimeRiftParticleEntity extends ThrowableItemProjectile {

    private final int maxLife = 600;
    private int life;

    public TimeRiftParticleEntity(EntityType<? extends TimeRiftParticleEntity> type, Level world) {
        super(type, world);
    }

    public TimeRiftParticleEntity(LivingEntity entity, Level world){
        super(EntityTypeRegistry.timeRiftParticleEntity.get(), entity, world);
    }

    public TimeRiftParticleEntity(double x, double y, double z, Level world){
        super(EntityTypeRegistry.timeRiftParticleEntity.get(), x, y, z, world);
    }

    @Override
    public void tick(){
        super.tick();
        //todo
        if (life >= maxLife) {
            this.remove(RemovalReason.DISCARDED);
        }
        life++;

        if (level.isClientSide()){
            double xR = level.random.nextDouble() * 0.1d - 0.05d;
            double yR = level.random.nextDouble() * 0.1d - 0.05d;
            double zR = level.random.nextDouble() * 0.1d - 0.05d;
            level.addParticle(net.minecraft.core.particles.ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR,yR,zR);
        }
    }

    @Override
    protected void onHit(HitResult ray){
        super.onHit(ray);
        if (level.isClientSide()){
            for (int i = 0; i < 10; i++) {
                double xR = level.random.nextDouble() * 0.1d - 0.05d;
                double yR = level.random.nextDouble() * 0.1d - 0.05d;
                double zR = level.random.nextDouble() * 0.1d - 0.05d;
                level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR, yR, zR);
            }
        }
        //todo
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onHitBlock(BlockHitResult ray){
        super.onHitBlock(ray);
    }

    @Override
    protected void onHitEntity(EntityHitResult ray){
        super.onHitEntity(ray);
        if (!this.level.isClientSide) {
            net.minecraft.world.entity.Entity entity = ray.getEntity();
            Entity owner = this.getOwner();
            entity.hurt(ModDamageSources.timeRift(owner), 2.0f);
            if (entity instanceof LivingEntity) {
                ((EffectInevitability) EffectRegistry.inevitability.get()).addEffect((LivingEntity) entity, 1);
            }
        }
    }

    @Override
    public boolean canHitEntity(Entity entity){
        return super.canHitEntity(entity);
    }

    @Override
    public boolean isPickable(){
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Life", this.life);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.life = nbt.getInt("Life");
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.timeRiftCharge.get();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}

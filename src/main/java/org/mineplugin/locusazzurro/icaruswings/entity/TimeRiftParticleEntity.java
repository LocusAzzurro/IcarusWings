package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class TimeRiftParticleEntity extends ProjectileItemEntity implements IRendersAsItem {

    private final int maxLife = 600;
    private int life;

    public TimeRiftParticleEntity(EntityType<? extends TimeRiftParticleEntity> type, World world) {
        super(type, world);
    }

    public TimeRiftParticleEntity(LivingEntity entity, World world){
        super(EntityTypeRegistry.timeRiftParticleEntity.get(), entity, world);
    }

    public TimeRiftParticleEntity(double x, double y, double z, World world){
        super(EntityTypeRegistry.timeRiftParticleEntity.get(), x, y, z, world);
    }

    @Override
    public void tick(){
        super.tick();
        if (life >= maxLife) this.remove();
        life++;

        if (level.isClientSide()){
            double xR = level.random.nextDouble() * 0.1d - 0.05d;
            double yR = level.random.nextDouble() * 0.1d - 0.05d;
            double zR = level.random.nextDouble() * 0.1d - 0.05d;
            level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR,yR,zR);
        }
    }

    @Override
    protected void onHit(RayTraceResult ray){
        super.onHit(ray);
        if (level.isClientSide()){
            for (int i = 0; i < 10; i++) {
                double xR = level.random.nextDouble() * 0.1d - 0.05d;
                double yR = level.random.nextDouble() * 0.1d - 0.05d;
                double zR = level.random.nextDouble() * 0.1d - 0.05d;
                level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR, yR, zR);
            }
        }
        this.remove();
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult ray){
        super.onHitBlock(ray);
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult ray){
        super.onHitEntity(ray);
        if (!this.level.isClientSide) {
            Entity entity = ray.getEntity();
            Entity owner = this.getOwner();
            entity.hurt(ModDamageSources.timeRift(owner), 2.0f);
            if (entity instanceof LivingEntity)
            ((EffectInevitability) EffectRegistry.inevitability.get()).addEffect((LivingEntity) entity, 1);
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Life", this.life);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
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
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}

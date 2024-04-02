package org.mineplugin.locusazzurro.icaruswings.common.entity;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

import javax.annotation.Nullable;
import java.util.UUID;

public class ArtemisMissileEntity extends AbstractHurtingProjectile {

    private UUID targetUUID;
    private int targetNetworkId;
    private int explosionPower = 1;
    private static final int DEFAULT_FUEL = 1200; // Default: 1200 (60s)
    private int fuel;
    private double homingSpeed = 1.1;
    private double cruisingSpeed = 0.8;
    private static final ParticleOptions PARTICLE = ParticleRegistry.PLASMA_TRAIL.get();
    
    // 空白粒子
    public static final ParticleOptions PARTICLE_EMPTY = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AIR.defaultBlockState()) ;

    public ArtemisMissileEntity(EntityType<? extends ArtemisMissileEntity> type, Level world) {
        super(type, world);
        this.fuel = DEFAULT_FUEL;
    }

    public ArtemisMissileEntity(double x, double y, double z, double accelX, double accelY, double accelZ, Level world){
        this(EntityTypeRegistry.ARTEMIS_MISSILE.get(), world);
        this.moveTo(x, y, z, this.getYRot(), this.getXRot());
        this.reapplyPosition();
        double v = Mth.sqrt((float) (accelX * accelX + accelY * accelY + accelZ * accelZ));
        if (v != 0.0D) {
            this.xPower = accelX / v * 0.1D;
            this.yPower = accelY / v * 0.1D;
            this.zPower = accelZ / v * 0.1D;
        }
    }

    public ArtemisMissileEntity(LivingEntity shooter, double accelX, double accelY, double accelZ, Level world){
        this(shooter.getX(), shooter.getY(), shooter.getZ(), accelX, accelY, accelZ, world);
        this.setOwner(shooter);
        this.setRot(shooter.getYRot(), shooter.getXRot());
    }

    public ArtemisMissileEntity(Level world, LivingEntity shooter, double powerX, double powerY, double powerZ){
        this(EntityTypeRegistry.ARTEMIS_MISSILE.get(), world);
        this.moveTo(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());
        this.reapplyPosition();
        this.setOwner(shooter);
        this.xPower = powerX;
        this.yPower = powerY;
        this.zPower = powerZ;
    }

    public ArtemisMissileEntity(Level world, LivingEntity shooter){
        this(EntityTypeRegistry.ARTEMIS_MISSILE.get(), world);
        this.setOwner(shooter);
        this.moveTo(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());
        this.reapplyPosition();
    }

    public ArtemisMissileEntity(Level world, LivingEntity shooter, LivingEntity target){
        this(EntityTypeRegistry.ARTEMIS_MISSILE.get(), world);
        this.setOwner(shooter);
        this.moveTo(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());
        this.reapplyPosition();
        this.setTarget(target);
    }

    @Override
    public void tick() {

        Vec3 vec = this.getDeltaMovement();
        Vec3 facing = this.getLookAngle();

        if (level().isClientSide()) {

            if (vec.length() > 0.1) {
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 4; i++) {
                        double xR = level().random.nextDouble() * 0.2d - 0.1d;
                        double yR = level().random.nextDouble() * 0.2d - 0.1d;
                        double zR = level().random.nextDouble() * 0.2d - 0.1d;
                        level().addParticle(PARTICLE,
                                this.getX() - (vec.x * 0.25 * j) + xR,
                                this.getY() - (vec.y * 0.25 * j) + yR + 0.1,
                                this.getZ() - (vec.z * 0.25 * j) + zR,
                                -(vec.x * 0.1),
                                -(vec.y * 0.1),
                                -(vec.z * 0.1));
                    }
                }
            }
            else {
                for (int i = 0; i < 2; i++){
                    double xR = level().random.nextDouble() * 0.1d - 0.05d;
                    double yR = level().random.nextDouble() * 0.1d - 0.05d;
                    double zR = level().random.nextDouble() * 0.1d - 0.05d;
                    level().addParticle(PARTICLE,
                            this.getX() + (facing.x * 0.1) + xR,
                            this.getY() + (facing.y * 0.1) + yR + 0.1,
                            this.getZ() + (facing.z * 0.1) + zR,
                            (facing.x * 0.03),
                            (facing.y * 0.03),
                            (facing.z * 0.03));
                }
            }
        }

        super.tick();

        if (this.getTarget() != null)
        {
            LivingEntity target = (LivingEntity) this.getTarget();
            Vec3 v3d = new Vec3(
                    target.getX() - this.getX(),
                    target.getY() - this.getY(),
                    target.getZ() - this.getZ())
                    .normalize().scale(homingSpeed);
            this.setDeltaMovement(v3d);
        }
        else {
            this.setDeltaMovement(this.getDeltaMovement().normalize().scale(cruisingSpeed));
        }

        if (this.fuel <= 0) {
            this.onEmptyFuel();
        }
        this.fuel--;


    }

    @Override
    protected void onHit(HitResult ray){
        super.onHit(ray);
        if(!this.level().isClientSide){
            boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
            this.level().explode(null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, Level.ExplosionInteraction.NONE);
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult ray){
        super.onHitBlock(ray);
    }

    @Override
    protected void onHitEntity(EntityHitResult ray){
        super.onHitEntity(ray);
        if (!this.level().isClientSide) {
            Entity entity = ray.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource = this.level().damageSources().explosion(this, owner);
            entity.hurt(damageSource, 8.0F);
            if (owner instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)owner, entity);
            }

        }
    }

    protected void onEmptyFuel(){
        if(!this.level().isClientSide){
            boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
            this.level().explode(null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower + 0.5f, flag, Level.ExplosionInteraction.NONE);
        }
        this.discard();
    }

    @Override
    public boolean canHitEntity(Entity entity){
        return super.canHitEntity(entity);
    }

    public void setTarget(@Nullable Entity target) {
        if (target != null) {
            this.targetUUID = target.getUUID();
            this.targetNetworkId = target.getId();
        }

    }

    @Nullable
    public Entity getTarget() {
        if (this.targetUUID != null && this.level() instanceof ServerLevel) {
            return ((ServerLevel)this.level()).getEntity(this.targetUUID);
        } else {
            return this.targetNetworkId != 0 ? this.level().getEntity(this.targetNetworkId) : null;
        }
    }

    public int getFuel(){
        return this.fuel;
    }

    public void setFuel(int fuel){
        this.fuel = fuel;
    }

    @Override
    protected boolean shouldBurn(){
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return PARTICLE_EMPTY ;
    }

    @Override
    public boolean isPickable(){
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        if (this.targetUUID != null) {
            nbt.putUUID("Target", this.targetUUID);
        }
        nbt.putInt("Fuel", this.fuel);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.hasUUID("Target")) {
            this.targetUUID = nbt.getUUID("Owner");
        }
        this.fuel = nbt.getInt("Fuel");
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}

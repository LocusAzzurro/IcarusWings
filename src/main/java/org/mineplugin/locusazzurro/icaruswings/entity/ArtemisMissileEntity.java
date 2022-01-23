package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.particles.BlockParticleData;

import javax.annotation.Nullable;
import java.util.UUID;

public class ArtemisMissileEntity extends DamagingProjectileEntity {

    private UUID targetUUID;
    private int targetNetworkId;
    private int explosionPower = 1;
    private static final int DEFAULT_FUEL = 1200; // Default: 1200 (60s)
    private int fuel;
    private double homingSpeed = 1.1;
    private static final IParticleData PARTICLE = ParticleRegistry.plasmaTrail.get();
    
    // 空白粒子
    public static final IParticleData PARTICLE_EMPTY = new BlockParticleData(ParticleTypes.BLOCK, Blocks.AIR.defaultBlockState()) ;

    public ArtemisMissileEntity(EntityType<? extends ArtemisMissileEntity> type, World world) {
        super(type, world);
        this.fuel = DEFAULT_FUEL;
    }

    public ArtemisMissileEntity(double x, double y, double z, double accelX, double accelY, double accelZ, World world){
        this(EntityTypeRegistry.artemisMissileEntity.get(), world);
        this.moveTo(x, y, z, this.yRot, this.xRot);
        this.reapplyPosition();
        double v = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        if (v != 0.0D) {
            this.xPower = accelX / v * 0.1D;
            this.yPower = accelY / v * 0.1D;
            this.zPower = accelZ / v * 0.1D;
        }
    }

    public ArtemisMissileEntity(LivingEntity shooter, double accelX, double accelY, double accelZ, World world){
        this(shooter.getX(), shooter.getY(), shooter.getZ(), accelX, accelY, accelZ, world);
        this.setOwner(shooter);
        this.setRot(shooter.yRot, shooter.xRot);
    }

    public ArtemisMissileEntity(World world, LivingEntity shooter, double powerX, double powerY, double powerZ){
        this(EntityTypeRegistry.artemisMissileEntity.get(), world);
        this.moveTo(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.yRot, shooter.xRot);
        this.reapplyPosition();
        this.setOwner(shooter);
        this.xPower = powerX;
        this.yPower = powerY;
        this.zPower = powerZ;
    }

    public ArtemisMissileEntity(World world, LivingEntity shooter){
        this(EntityTypeRegistry.artemisMissileEntity.get(), world);
        this.setOwner(shooter);
        this.moveTo(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.yRot, shooter.xRot);
        this.reapplyPosition();
    }

    public ArtemisMissileEntity(World world, LivingEntity shooter, LivingEntity target){
        this(EntityTypeRegistry.artemisMissileEntity.get(), world);
        this.setOwner(shooter);
        this.moveTo(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.yRot, shooter.xRot);
        this.reapplyPosition();
        this.setTarget(target);
    }

    @Override
    public void tick() {

        Vector3d vec = this.getDeltaMovement();
        Vector3d facing = this.getLookAngle();

        if (level.isClientSide()) {

            if (vec.length() > 0.1) {
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 4; i++) {
                        double xR = level.random.nextDouble() * 0.2d;
                        double yR = level.random.nextDouble() * 0.2d;
                        double zR = level.random.nextDouble() * 0.2d;
                        level.addParticle(PARTICLE,
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
                    double xR = level.random.nextDouble() * 0.1d;
                    double yR = level.random.nextDouble() * 0.1d;
                    double zR = level.random.nextDouble() * 0.1d;
                    level.addParticle(PARTICLE,
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
            Vector3d v3d = new Vector3d(
                    target.getX() - this.getX(),
                    target.getY() - this.getY(),
                    target.getZ() - this.getZ())
                    .normalize().scale(homingSpeed);
            this.setDeltaMovement(v3d);
        }
        if (this.fuel <= 0) {
            this.onEmptyFuel();
        }
        this.fuel--;


    }

    @Override
    protected void onHit(RayTraceResult ray){
        super.onHit(ray);
        if(!this.level.isClientSide){
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
            this.level.explode(null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, Explosion.Mode.NONE);
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
            entity.hurt(
                    (new IndirectEntityDamageSource("explosion", this, owner)).setExplosion()
                    , 4.0F);
            if (owner instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)owner, entity);
            }

        }
    }

    protected void onEmptyFuel(){
        if(!this.level.isClientSide){
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
            this.level.explode(null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower + 0.5f, flag, Explosion.Mode.NONE);
        }
        this.remove();
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
        if (this.targetUUID != null && this.level instanceof ServerWorld) {
            return ((ServerWorld)this.level).getEntity(this.targetUUID);
        } else {
            return this.targetNetworkId != 0 ? this.level.getEntity(this.targetNetworkId) : null;
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

    protected IParticleData getTrailParticle() {
        return PARTICLE_EMPTY ;
    }

    @Override
    public boolean isPickable(){
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        if (this.targetUUID != null) {
            nbt.putUUID("Target", this.targetUUID);
        }
        nbt.putInt("Fuel", this.fuel);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.hasUUID("Target")) {
            this.targetUUID = nbt.getUUID("Owner");
        }
        this.fuel = nbt.getInt("Fuel");
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}

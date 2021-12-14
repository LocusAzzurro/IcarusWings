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

import javax.annotation.Nullable;
import java.util.UUID;

public class ArtemisMissileEntity extends DamagingProjectileEntity {

    private LivingEntity target = null;
    private UUID targetUUID;
    private int targetNetworkId;
    private int explosionPower = 1;
    private static final int DEFAULT_FUEL = 1200; // Default: 1200 (60s)
    private int fuel;
    private double homingSpeed = 1.1;
    private static final IParticleData PARTICLE = ParticleRegistry.plasmaTrail.get();

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

        if (level.isClientSide()) {

            if (vec.length() > 0.05) {
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 4; i++) {
                        double xR = level.random.nextDouble() * 0.2d;
                        double yR = level.random.nextDouble() * 0.2d;
                        double zR = level.random.nextDouble() * 0.2d;
                        level.addParticle(PARTICLE,
                                this.getX() - (vec.x * 0.25 * j) + xR,
                                this.getY() - (vec.y * 0.25 * j) + yR,
                                this.getZ() - (vec.z * 0.25 * j) + zR,
                                -(vec.x * 0.1),
                                -(vec.y * 0.15),
                                -(vec.z * 0.1));
                    }
                }
            }
        }

        this.projectileTick();

        if (this.targetUUID != null)
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

        // inherited
        if (this.leftOwner) {
            nbt.putBoolean("LeftOwner", true);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.hasUUID("Target")) {
            this.targetUUID = nbt.getUUID("Owner");
        }
        this.fuel = nbt.getInt("Fuel");

        // inherited
        this.leftOwner = nbt.getBoolean("LeftOwner");
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    // START INHERITED TICK CALL -> this#tick -> DamagingProjectileEntity#tick -> ProjectileEntity#tick -> Entity#tick -> Entity#baseTick
    // This whole chain and related methods needed to be inherited to modify particle behavior.

    private boolean leftOwner;

    // DamagingProjectileEntity#tick
    private void projectileTick(){
        Entity entity = this.getOwner();
        if (this.level.isClientSide || (entity == null || !entity.removed) && this.level.hasChunkAt(this.blockPosition())) {

            // START ProjectileEntity#tick
            if (!this.leftOwner) {
                this.leftOwner = this.checkLeftOwner();
            }
                // START Entity#tick
                if (!this.level.isClientSide) {
                    this.setSharedFlag(6, this.isGlowing());
                }

                this.baseTick();

                // END Entity#tick

            // END ProjectileEntity#tick
            /*

            // removed burn check

            if (this.shouldBurn()) {
                this.setSecondsOnFire(1);
            }
            */

            RayTraceResult raytraceresult = ProjectileHelper.getHitResult(this, this::canHitEntity);
            if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
            }

            this.checkInsideBlocks();
            Vector3d vector3d = this.getDeltaMovement();
            double d0 = this.getX() + vector3d.x;
            double d1 = this.getY() + vector3d.y;
            double d2 = this.getZ() + vector3d.z;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getInertia();


            // removed bubble particle
            /*
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f1 = 0.25F;
                    this.level.addParticle(ParticleTypes.BUBBLE, d0 - vector3d.x * 0.25D, d1 - vector3d.y * 0.25D, d2 - vector3d.z * 0.25D, vector3d.x, vector3d.y, vector3d.z);
                }

                f = 0.8F;
            } */

            this.setDeltaMovement(vector3d.add(this.xPower, this.yPower, this.zPower).scale((double)f));
            //this.level.addParticle(this.getTrailParticle(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D); // particle disabled
            this.setPos(d0, d1, d2);
        } else {
            this.remove();
        }
    }

    private boolean checkLeftOwner() {
        Entity entity = this.getOwner();
        if (entity != null) {
            for(Entity entity1 : this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (p_234613_0_) -> {
                return !p_234613_0_.isSpectator() && p_234613_0_.isPickable();
            })) {
                if (entity1.getRootVehicle() == entity.getRootVehicle()) {
                    return false;
                }
            }
        }

        return true;
    }

    // END INHERITED TICK CALL

}

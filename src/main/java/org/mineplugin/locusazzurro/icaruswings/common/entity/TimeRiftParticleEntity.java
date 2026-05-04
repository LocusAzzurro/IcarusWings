package org.mineplugin.locusazzurro.icaruswings.common.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.effect.InevitabilityEffect;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class TimeRiftParticleEntity extends ThrowableItemProjectile {

    private static final PowerParticleOption DRAGON_BREATH = PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1.0f);

    private final int maxLife = 600;
    private int life;

    public TimeRiftParticleEntity(EntityType<? extends TimeRiftParticleEntity> type, Level world) {
        super(type, world);
    }

    public TimeRiftParticleEntity(LivingEntity entity, Level world){
        super(EntityTypeRegistry.TIME_RIFT_PARTICLE.get(), entity, world, new ItemStack(ItemRegistry.TIME_RIFT_CHARGE.get()));
    }

    public TimeRiftParticleEntity(double x, double y, double z, Level world){
        super(EntityTypeRegistry.TIME_RIFT_PARTICLE.get(), x, y, z, world, new ItemStack(ItemRegistry.TIME_RIFT_CHARGE.get()));
    }

    @Override
    public void tick(){
        super.tick();
        if (life >= maxLife) {
            this.discard();
        }
        life++;

        if (level().isClientSide()){
            double xR = level().getRandom().nextDouble() * 0.1d - 0.05d;
            double yR = level().getRandom().nextDouble() * 0.1d - 0.05d;
            double zR = level().getRandom().nextDouble() * 0.1d - 0.05d;
            level().addParticle(DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR,yR,zR);
        }
    }

    @Override
    protected void onHit(HitResult ray){
        super.onHit(ray);
        if (level().isClientSide()){
            for (int i = 0; i < 10; i++) {
                double xR = level().getRandom().nextDouble() * 0.1d - 0.05d;
                double yR = level().getRandom().nextDouble() * 0.1d - 0.05d;
                double zR = level().getRandom().nextDouble() * 0.1d - 0.05d;
                level().addParticle(DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR, yR, zR);
            }
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
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity entity = ray.getEntity();
            Entity owner = this.getOwner() == null ? this : this.getOwner();
            entity.hurtServer(serverLevel, ModDamageSources.timeRift(entity.level(), this, owner), 2.0f);
            if (entity instanceof LivingEntity) {
                InevitabilityEffect.addEffect((LivingEntity) entity, 1);
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
    protected void addAdditionalSaveData(ValueOutput nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Life", this.life);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput nbt) {
        super.readAdditionalSaveData(nbt);
        this.life = nbt.getIntOr("Life", this.life);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.TIME_RIFT_CHARGE.get();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_entity) {
        Entity entity = this.getOwner();
        return new ClientboundAddEntityPacket(this, p_entity, entity == null ? 0 : entity.getId());
    }
}


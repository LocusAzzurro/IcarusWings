package org.mineplugin.locusazzurro.icaruswings.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.mineplugin.locusazzurro.icaruswings.common.data.IcarusWingsConfig;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.effect.InevitabilityEffect;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.KayrosGenUtils;

public class KayrosBlastEntity extends ThrowableItemProjectile {


    private static final boolean ALLOW_TERRAIN = IcarusWingsConfig.ALLOW_DEMETER_CHANGE_TERRAIN.get();
    private final int maxLife = 600;
    private int life;
    private byte[] landscape = KayrosGenUtils.cubeTerrain;

    public KayrosBlastEntity(EntityType<? extends KayrosBlastEntity> type, Level level) {
        super(type, level);
    }

    public KayrosBlastEntity(LivingEntity entity, Level world, CompoundTag terrain){
        super(EntityTypeRegistry.KAYROS_BLAST.get(), entity, world, new ItemStack(ItemRegistry.DEMETER_CHARGE.get()));
        this.landscape = terrain.getByteArray("Landscape").orElse(KayrosGenUtils.cubeTerrain);
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
            level().addParticle(ParticleTypes.WITCH, this.getX(), this.getY(), this.getZ(), xR,yR,zR);
        }
    }

    @Override
    protected void onHit(HitResult ray){
        super.onHit(ray);
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundRegistry.TIME_RIFT_COLLAPSE.get(), SoundSource.PLAYERS, 2.0F, 1.1F);
    }

    @Override
    protected void onHitBlock(BlockHitResult ray){
        super.onHitBlock(ray);
        BlockPos pos = ray.getBlockPos();
        if (this.level() instanceof ServerLevel serverLevel){
            if (ALLOW_TERRAIN) {
                int xo = pos.getX() - 4;
                int yo = pos.getY() - this.level().getRandom().nextInt(3);
                int zo = pos.getZ() - 4;
                for (int i = 0; i < this.landscape.length; i++) {
                    int y = i / 64;
                    int z = (i - 64 * y) / 8;
                    int x = i - 64 * y - 8 * z;
                    this.level().setBlock(new BlockPos(xo + x, yo + y, zo + z), KayrosGenUtils.palette[this.landscape[i]], 3);
                }
            }
            this.level().getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(4)).forEach(e -> {
                InevitabilityEffect.addEffect(e, 3);
                e.hurtServer(serverLevel, ModDamageSources.timeRift(this.level(), this, this.getOwner() == null ? this : this.getOwner()), 5.0f);
            });
            serverLevel.sendParticles(ParticleTypes.WITCH,
                    pos.getX(), pos.getY(), pos.getZ(),
                    4000, 4, 4, 4, 0.001);
        }
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingEntity && this.level() instanceof ServerLevel serverLevel) {
            InevitabilityEffect.addEffect(livingEntity, 5);
            livingEntity.hurtServer(serverLevel, ModDamageSources.timeRift(this.level(), this, this.getOwner() == null ? this : this.getOwner()), 15.0f);
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) || entity instanceof LivingEntity;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Life", this.life);
        int[] raw = new int[this.landscape.length];
        for (int i = 0; i < this.landscape.length; i++) {
            raw[i] = this.landscape[i];
        }
        nbt.putIntArray("Landscape", raw);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput nbt) {
        super.readAdditionalSaveData(nbt);
        this.life = nbt.getIntOr("Life", this.life);
        int[] raw = nbt.getIntArray("Landscape").orElse(new int[0]);
        if (raw.length > 0) {
            this.landscape = new byte[raw.length];
            for (int i = 0; i < raw.length; i++) {
                this.landscape[i] = (byte)raw[i];
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.DEMETER_CHARGE.get();
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

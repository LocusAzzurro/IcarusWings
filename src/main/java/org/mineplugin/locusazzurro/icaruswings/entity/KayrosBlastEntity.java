package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.KayrosGenUtils;

public class KayrosBlastEntity extends ThrowableItemProjectile {


    private static final EntityDataAccessor<CompoundTag> LANDSCAPE = SynchedEntityData.defineId(KayrosBlastEntity.class, EntityDataSerializers.COMPOUND_TAG);
    private final int maxLife = 600;
    private int life;

    public KayrosBlastEntity(EntityType<? extends KayrosBlastEntity> type, Level level) {
        super(type, level);
    }

    public KayrosBlastEntity(LivingEntity entity, Level world, CompoundTag terrain){
        super(EntityTypeRegistry.kayrosBlastEntity.get(), entity, world);
        this.entityData.set(LANDSCAPE, terrain);
    }

    @Override
    public void tick(){
        super.tick();
        if (life >= maxLife) {
            this.discard();
        }
        life++;

        if (level.isClientSide()){
            /*
            double xR = level.random.nextDouble() * 0.1d - 0.05d;
            double yR = level.random.nextDouble() * 0.1d - 0.05d;
            double zR = level.random.nextDouble() * 0.1d - 0.05d;
            level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR,yR,zR);

             */
        }
    }

    @Override
    protected void onHit(HitResult ray){
        super.onHit(ray);
        if (level.isClientSide()){
            /*
            for (int i = 0; i < 10; i++) {
                double xR = level.random.nextDouble() * 0.1d - 0.05d;
                double yR = level.random.nextDouble() * 0.1d - 0.05d;
                double zR = level.random.nextDouble() * 0.1d - 0.05d;
                level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), xR, yR, zR);
            }

             */
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult ray){
        super.onHitBlock(ray);
        BlockPos pos = ray.getBlockPos();
        if (!level.isClientSide()){
            byte[] terrain = this.entityData.get(LANDSCAPE).getByteArray("Landscape");
            int xo = pos.getX() - 4;
            int yo = pos.getY() - level.random.nextInt(3);
            int zo = pos.getZ() - 4;
            for (int i = 0; i < terrain.length; i++){
                int y = i / 64;
                int z = (i - 64 * y) / 8;
                int x = i - 64 * y - 8 * z;
                level.setBlock(new BlockPos(xo + x, yo + y, zo + z), KayrosGenUtils.palette[terrain[i]], 3);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Life", this.life);
        nbt.putByteArray("Landscape", entityData.get(LANDSCAPE).getByteArray("Landscape"));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.life = nbt.getInt("Life");
        entityData.set(LANDSCAPE, KayrosGenUtils.convertToTag(nbt.getByteArray("Landscape")));
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.timeRiftCharge.get();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LANDSCAPE, KayrosGenUtils.convertToTag(KayrosGenUtils.cubeTerrain));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}

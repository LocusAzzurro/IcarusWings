package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.items.SpearItem;
import org.mineplugin.locusazzurro.icaruswings.registry.DamageTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.Nullable;

public class SpearEntity extends AbstractArrow {

    private static final EntityDataAccessor<ItemStack> SPEAR_ITEM = SynchedEntityData.defineId(SpearEntity.class, EntityDataSerializers.ITEM_STACK);
    private ItemStack spearItem;
    private boolean dealtDamage;

    public SpearEntity(EntityType<? extends SpearEntity> type, Level world) {
        super(type, world);
        this.spearItem = new ItemStack(ItemRegistry.woodenSpear.get());
    }

    public SpearEntity(Level worldIn, LivingEntity owner, ItemStack spear){
        super(EntityTypeRegistry.spearEntity.get(), owner, worldIn);
        this.spearItem = spear.copy();
        this.entityData.set(SPEAR_ITEM, spear);
    }

    @OnlyIn(Dist.CLIENT)
    public SpearEntity(Level p_i48791_1_, double p_i48791_2_, double p_i48791_4_, double p_i48791_6_) {
        super(EntityTypeRegistry.spearEntity.get(), p_i48791_2_, p_i48791_4_, p_i48791_6_, p_i48791_1_);
        this.spearItem = new ItemStack(ItemRegistry.woodenSpear.get());
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        super.tick();
    }

    @Override
    @Nullable
    protected EntityHitResult findHitEntity(Vec3 p_213866_1_, Vec3 p_213866_2_) {
        return this.dealtDamage ? null : super.findHitEntity(p_213866_1_, p_213866_2_);
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        Entity target = ray.getEntity();
        float f = ((SpearItem) this.spearItem.getItem()).getAttackDamage();
        if (target instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)target;
            f += EnchantmentHelper.getDamageBonus(this.spearItem, livingentity.getMobType());
        }

        Entity owner = this.getOwner();
        DamageSource damageSource = ModDamageSources.spear(this.level(), this, owner == null ? this : owner);
        //DamageSource damageSource = new DamageSource(this.level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypeRegistry.SPEAR),this, owner == null ? this : owner);
        this.dealtDamage = true;
        SoundEvent soundevent = SoundRegistry.spearHit.get();
        if (target.hurt(damageSource, f)) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (target instanceof LivingEntity) {
                LivingEntity targetLiving = (LivingEntity)target;
                if (owner instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(targetLiving, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)owner, targetLiving);
                }

                this.doPostHurtEffects(targetLiving);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));

        this.playSound(soundevent, 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundRegistry.spearHitGround.get();
    }

    @Override
    public void playerTouch(Player playerIn) {
        Entity owner = this.getOwner();
        if (owner == null || owner.getUUID() == playerIn.getUUID()) {
            super.playerTouch(playerIn);
        }
    }

    @Override
    public ItemStack getPickupItem() {
        return this.spearItem.copy();
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SPEAR_ITEM, new ItemStack(ItemRegistry.woodenSpear.get()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Spear", 10)) {
            this.spearItem = ItemStack.of(nbt.getCompound("Spear"));
        }

        this.dealtDamage = nbt.getBoolean("DealtDamage");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("Spear", this.spearItem.save(new CompoundTag()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    public void setSpearItemData(ItemStack stackIn){
        this.entityData.set(SPEAR_ITEM, stackIn);
    }

    public ItemStack getSpearItemData(){
        return this.entityData.get(SPEAR_ITEM);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isFoil() {
        return this.entityData.get(SPEAR_ITEM).hasFoil();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean shouldRender(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
        return true;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}


package org.mineplugin.locusazzurro.icaruswings.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.item.SpearItem;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.Nullable;

public class SpearEntity extends AbstractArrow {

    private static final EntityDataAccessor<ItemStack> SPEAR_ITEM = SynchedEntityData.defineId(SpearEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final ItemStack WOODEN_SPEAR = new ItemStack(ItemRegistry.WOODEN_SPEAR.get());
    private ItemStack spearItem;
    private boolean dealtDamage;



    public SpearEntity(EntityType<? extends SpearEntity> type, Level world) {
        super(type, world);
        this.setPickupItemStack(WOODEN_SPEAR);
        this.spearItem = new ItemStack(ItemRegistry.WOODEN_SPEAR.get());
    }

    public SpearEntity(Level worldIn, LivingEntity owner, ItemStack spear){
        super(EntityTypeRegistry.SPEAR.get(), owner, worldIn, WOODEN_SPEAR, null);
        this.setPickupItemStack(spear);
        this.spearItem = spear.copy();
        this.entityData.set(SPEAR_ITEM, spear);
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
        if (!level().isClientSide) {
            Entity target = ray.getEntity();
            float damage = ((SpearItem) this.getPickupItem().getItem()).getAttackDamage();

            Entity owner = this.getOwner();
            DamageSource damageSource = ModDamageSources.spear(this.level(), this, owner == null ? this : owner);
            this.dealtDamage = true;
            if (target.hurt(damageSource, damage)) {
                if (target.getType() == EntityType.ENDERMAN) {
                    return;
                }


                if (target instanceof LivingEntity targetLiving) {
                    if (owner instanceof LivingEntity) {
                        EnchantmentHelper.doPostAttackEffects((ServerLevel) this.level(), targetLiving, damageSource);
                    }
                    this.doPostHurtEffects(targetLiving);
                }

            }
            this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        }
        this.playSound(SoundRegistry.SPEAR_HIT.get());
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundRegistry.SPEAR_HIT_GROUND.get();
    }

    @Override
    public void playerTouch(Player playerIn) {
        Entity owner = this.getOwner();
        if (owner == null || owner.getUUID() == playerIn.getUUID()) {
            super.playerTouch(playerIn);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return WOODEN_SPEAR;
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SPEAR_ITEM, new ItemStack(ItemRegistry.WOODEN_SPEAR.get()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("spear", 10)) {
            var spear = ItemStack.parse(this.registryAccess(), nbt.getCompound("spear")).orElse(ItemStack.EMPTY);
            this.spearItem = spear;
            this.setSpearItemData(spear);
        }
        this.dealtDamage = nbt.getBoolean("dealt_damage");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("spear", this.spearItem.save(this.registryAccess(), new CompoundTag()));
        nbt.putBoolean("dealt_damage", this.dealtDamage);
    }

    public void setSpearItemData(ItemStack stackIn){
        this.entityData.set(SPEAR_ITEM, stackIn);
    }

    public ItemStack getSpearItemData(){
        return this.entityData.get(SPEAR_ITEM);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isFoil() {
        return this.getPickupItem().hasFoil();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean shouldRender(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
        return true;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_entity) {
        Entity entity = this.getOwner();
        return new ClientboundAddEntityPacket(this, p_entity, entity == null ? 0 : entity.getId());
    }
}


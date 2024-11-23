package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.effect.InevitabilityEffect;
import org.mineplugin.locusazzurro.icaruswings.common.entity.KayrosBlastEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.KayrosGenUtils;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class Demeter extends ProjectileWeaponItem {

    private static final float ATTACK_DAMAGE = 1.0f;
    private static final float ATTACK_SPEED = -2.0f;
    private static final ResourceLocation ATTACK_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "demeter_attack_damage");
    private static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "demeter_attack_speed");

    public Demeter(){
        super(new Item.Properties().durability(600).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        ItemStack charge = playerIn.getProjectile(itemStack);
        if (!playerIn.getAbilities().instabuild && charge.isEmpty()){
            return InteractionResultHolder.pass(itemStack);
        }
        if (!worldIn.isClientSide()) {
            KayrosBlastEntity particle = new KayrosBlastEntity(playerIn, worldIn,
                    KayrosGenUtils.generateTerrainTagWithJitter(playerIn.getX(), playerIn.getZ(), worldIn.random, 32f));
            particle.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 1.0f, 0.5f);
            particle.setNoGravity(true);
            worldIn.addFreshEntity(particle);
            worldIn.playSound(null, playerIn, SoundRegistry.DEMETER_BLAST.get(), SoundSource.PLAYERS, 1.0F, 1.2F);
            itemStack.hurtAndBreak(1, (ServerLevel) worldIn, playerIn,
                    item -> playerIn.onEquippedItemBroken(item, playerIn.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));
        }

        if (!playerIn.getAbilities().instabuild) {
            charge.shrink(1);
            if (charge.isEmpty()) {
                playerIn.getInventory().removeItem(charge);
            }
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        playerIn.getCooldowns().addCooldown(this, 60);
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack)
                .withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_ID, ATTACK_DAMAGE, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_ID, ATTACK_SPEED, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, pAttacker.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        InevitabilityEffect.addEffect(pTarget, 0);
        pTarget.hurt(ModDamageSources.timeRift(pTarget.level(), pAttacker), 2.0f);
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack thisItem, ItemStack repairItem) {
        return repairItem.getItem() == ItemRegistry.SYNAPSE_REPAIR_KIT.get() || super.isValidRepairItem(thisItem, repairItem);
    }

    public static final Predicate<ItemStack> DEMETER_CHARGE = (item) -> item.getItem().equals(ItemRegistry.DEMETER_CHARGE.get());

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return DEMETER_CHARGE;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 32;
    }

    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, velocity, inaccuracy);
    }

}

package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.entity.KayrosBlastEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.KayrosGenUtils;

import java.util.function.Predicate;

public class Demeter extends ProjectileWeaponItem {

    private static final float attackDamage = 1.0f;
    private static final float attackSpeed = -2.0f;
    private static final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public Demeter(){
        super(new Item.Properties().durability(600).rarity(Rarity.RARE));
    }

    static {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        defaultModifiers = builder.build();
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
            worldIn.playSound(null, playerIn, SoundRegistry.demeterBlast.get(), SoundSource.PLAYERS, 1.0F, 1.2F);
            itemStack.hurtAndBreak(1, playerIn, (player) -> player.broadcastBreakEvent(playerIn.getUsedItemHand()));
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

    @SuppressWarnings("deprecation")
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (attacker) -> {
            attacker.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        ((EffectInevitability) EffectRegistry.inevitability.get()).addEffect(pTarget, 1);
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

}

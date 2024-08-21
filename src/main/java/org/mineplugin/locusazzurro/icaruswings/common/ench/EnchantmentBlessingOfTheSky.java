package org.mineplugin.locusazzurro.icaruswings.common.ench;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EnchantmentRegistry;

@EventBusSubscriber
public class EnchantmentBlessingOfTheSky extends WingsEnchantment {

    public EnchantmentBlessingOfTheSky() {
        super(Rarity.VERY_RARE);
    }

    @Override
    public int getMinLevel() {return 1;}

    @Override
    public int getMaxLevel() {return 1;}

    @Override
    public int getMinCost(int lvl) {return lvl * 10;}

    @Override
    public int getMaxCost(int lvl) {return getMinCost(lvl) + 5;}

    @Override
    public boolean isTreasureOnly() {return true;}

    @Override
    public boolean isDiscoverable() {return false;}

    private static boolean isValidDamageType(DamageSource damage){
        return damage.is(DamageTypeTags.IS_EXPLOSION) || damage.is(DamageTypeTags.IS_FIRE) ||
                damage.is(DamageTypeTags.IS_PROJECTILE) ||  damage.is(DamageTypeTags.BYPASSES_ARMOR);
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent e) {
        if (e.getEntity() instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity) e.getEntity();
            ItemStack armor = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
            if (livingEntity.isFallFlying() && armor.getItem() instanceof ElytraItem
                && EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.BLESSING_OF_THE_SKY.get(), livingEntity) > 0
                && isValidDamageType(e.getSource())){
                e.setAmount(e.getAmount() / 2);
            }
        }
    }

}

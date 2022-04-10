package org.mineplugin.locusazzurro.icaruswings.ench;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public abstract class WingsEnchantment extends net.minecraft.world.item.enchantment.Enchantment {

    protected WingsEnchantment(Rarity rarity) {
        super(rarity, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[] {EquipmentSlot.CHEST});
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem;
    }

    protected static int getWingsEnchantmentLevel(Entity entityIn, Enchantment enchantment){
        if (entityIn instanceof LivingEntity && enchantment instanceof WingsEnchantment){
            LivingEntity livingEntity = (LivingEntity) entityIn;
            if (livingEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ElytraItem){
                return EnchantmentHelper.getEnchantmentLevel(enchantment, livingEntity);
            }
        }
        return 0;
    }


}

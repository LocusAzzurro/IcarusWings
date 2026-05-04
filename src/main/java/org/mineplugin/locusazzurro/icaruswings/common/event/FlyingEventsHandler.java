package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModEnchantments;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.AbstractWings;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

@EventBusSubscriber
@SuppressWarnings("unused")
public class FlyingEventsHandler {

    @SubscribeEvent
    public static void fireworkExtender(EntityJoinLevelEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof FireworkRocketEntity firework){
            if (firework.isAttachedToEntity() && firework.attachedToEntity != null
                    && firework.attachedToEntity.getItemBySlot(EquipmentSlot.CHEST).has(DataComponentRegistry.ICARUS_GLIDER)){
                int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.getHolder(e.getLevel(), ModEnchantments.PYROTECHNIC_AFFINITY), firework.attachedToEntity);
                firework.lifetime *= (int) (1 + enchantmentLevel * 0.1);
            }
        }
    }

    @SubscribeEvent
    public static void blessingOfSky(LivingDamageEvent.Pre e){
        if (!(e.getSource().is(DamageTypeTags.IS_EXPLOSION) ||
                e.getSource().is(DamageTypeTags.IS_FIRE) ||
                e.getSource().is(DamageTypeTags.IS_PROJECTILE) ||
                e.getSource().is(DamageTypeTags.IS_LIGHTNING)))
            return;
        LivingEntity entity = e.getEntity();
        Level level = entity.level();
        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.getHolder(level, ModEnchantments.BLESSING_OF_THE_SKY), entity);
        if (enchantmentLevel > 0){
            e.setNewDamage(e.getOriginalDamage() / 2);
        }
    }

    @SubscribeEvent
    public static void onCollision(LivingDamageEvent.Post e){
        if (!e.getSource().is(ModTags.IS_COLLISION)) return;
        LivingEntity entity = e.getEntity();
        Level level = entity.level();
        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.getHolder(level, ModEnchantments.COLLISION_PROTECTION), entity);
        ItemStack chestSlotItem = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (chestSlotItem.getItem() instanceof AbstractWings wings && enchantmentLevel > 0){
            int armorDamage = (int) Mth.clamp(e.getOriginalDamage() / enchantmentLevel, 1, 5);
            chestSlotItem.hurtAndBreak(armorDamage, entity, EquipmentSlot.CHEST);
        }
    }



}

package org.mineplugin.locusazzurro.icaruswings.common.ench;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.registry.EnchantmentRegistry;

@Mod.EventBusSubscriber
public class EnchantmentPyrotechnicAffinity extends WingsEnchantment {

    public EnchantmentPyrotechnicAffinity() {
        super(Rarity.RARE);
    }

    @Override
    public int getMinLevel() {return 1;}

    @Override
    public int getMaxLevel() {return 3;}

    @Override
    public int getMinCost(int lvl) {return lvl * 12;}

    @Override
    public int getMaxCost(int lvl) {return getMinCost(lvl) + 5;}

    @SubscribeEvent
    public static void fireworkExtender(EntityJoinLevelEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof FireworkRocketEntity){
            FireworkRocketEntity firework = (FireworkRocketEntity) entity;
            if (firework.isAttachedToEntity()
                    && firework.attachedToEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ElytraItem){
                int enchLvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.PYROTECHNIC_AFFINITY.get(), firework.attachedToEntity);
                firework.lifetime *= 1 + enchLvl * 0.1;
            }
        }
    }
}

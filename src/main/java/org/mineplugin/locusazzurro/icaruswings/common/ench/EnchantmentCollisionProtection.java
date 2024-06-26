package org.mineplugin.locusazzurro.icaruswings.common.ench;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EnchantmentRegistry;

@EventBusSubscriber
public class EnchantmentCollisionProtection extends WingsEnchantment {

	public EnchantmentCollisionProtection() {
		super(Rarity.UNCOMMON);
	}

	@Override
	public int getMinLevel() {return 1;}

	@Override
	public int getMaxLevel() {return 3;}

	@Override
	public int getMinCost(int lvl) {return lvl * 10;}

	@Override
	public int getMaxCost(int lvl) {return getMinCost(lvl) + 5;}

	@Override
	public int getDamageProtection(int level, DamageSource source) {
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return 0;
		if (source.is(DamageTypes.FLY_INTO_WALL)) return level * 3;
		else return 0;
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent e) {
		if (e.getSource().is(DamageTypes.FLY_INTO_WALL)){
			int lvl = getWingsEnchantmentLevel(e.getEntity(), EnchantmentRegistry.COLLISION_PROTECTION.get());
			int dmg = (int) (e.getAmount() * 0.1 * lvl);
			if (e.getEntity() != null){
				LivingEntity livingIn = e.getEntity();
				livingIn.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(dmg, livingIn, e1 -> e1.broadcastBreakEvent((EquipmentSlot.CHEST)));
			}
		}
	}

}

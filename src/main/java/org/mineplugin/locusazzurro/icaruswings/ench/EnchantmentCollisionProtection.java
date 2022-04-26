package org.mineplugin.locusazzurro.icaruswings.ench;

import net.minecraft.world.entity.LivingEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.EnchantmentRegistry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

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
		if (source.isBypassInvul()) return 0;
		if (source == DamageSource.FLY_INTO_WALL) return level * 3;
		else return 0;
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent e) {
		if (e.getSource() == DamageSource.FLY_INTO_WALL){
			int lvl = getWingsEnchantmentLevel(e.getEntity(), EnchantmentRegistry.collisionProtection.get());
			int dmg = (int) (e.getAmount() * 0.1 * lvl);
			if (e.getEntity() instanceof LivingEntity){
				LivingEntity livingIn = (LivingEntity) e.getEntity();
				livingIn.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(dmg, livingIn, e1 -> e1.broadcastBreakEvent((EquipmentSlot.CHEST)));
			}
		}
		/*
		if (e.getEntity() instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity) e.getEntity();
			ItemStack armor = player.getItemBySlot(EquipmentSlotType.CHEST);
			if (e.getSource() == DamageSource.FLY_INTO_WALL) {
				int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.collisionProtection.get(), player);
				int dmg = (int) (e.getAmount() * 0.1 * lvl);
				armor.hurtAndBreak(dmg, player,	ae -> ae.broadcastBreakEvent(EquipmentSlotType.CHEST));
			}
		}
		 */
	}
	
}

package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import javax.annotation.Nullable;

@SuppressWarnings("unused")

public abstract class AbstractWings extends ElytraItem {

	protected WingsType type;

	protected AbstractWings(WingsType type) {
		super(new Properties().durability(type.getDurability()));
		this.type = type;
	}

	protected AbstractWings(WingsType type, Rarity rarity) {
		super(new Properties().durability(type.getDurability()).rarity(rarity));
		this.type = type;
	}

	protected AbstractWings() {
		super(new Properties().durability(WingsTypes.FEATHER.getDurability()));
		this.type = WingsTypes.FEATHER;
	}

	public WingsType getType() {
		return this.type;
	}

	@Nullable
	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.CHEST;
	}

	@Override
	public Holder<SoundEvent> getEquipSound() {
		return getType().getEquipSound();
	}

	@Override
	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return super.canElytraFly(stack, entity);
	}

	@Override
	public boolean isValidRepairItem(ItemStack toBeRepaired, ItemStack repairMat) {
		Item item = toBeRepaired.getItem();
		if (item instanceof AbstractWings) {
			return ((AbstractWings) item).getType().getRepairItem() == repairMat.getItem();
		}
		return false;
	}

	@Override
	public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
		return super.elytraFlightTick(stack, entity, flightTicks);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public int getEnchantmentValue() {
		return 15;
	}

	public static boolean isEntityFloating (LivingEntity entity){
		return entity.hasEffect(MobEffects.SLOW_FALLING) || entity.hasEffect(MobEffects.LEVITATION);
	}

}

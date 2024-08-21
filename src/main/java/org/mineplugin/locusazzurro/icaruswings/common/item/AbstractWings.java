package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.common.data.IWingsType;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;

import javax.annotation.Nullable;

@SuppressWarnings("unused")

public abstract class AbstractWings extends ElytraItem {

	protected IWingsType type;

	protected AbstractWings(IWingsType type) {
		super(new Properties().durability(type.getDurability()));
		this.type = type;
	}

	protected AbstractWings(IWingsType type, Rarity rarity) {
		super(new Properties().durability(type.getDurability()).rarity(rarity));
		this.type = type;
	}

	protected AbstractWings() {
		super(new Properties().durability(WingsType.FEATHER.getDurability()));
		this.type = WingsType.FEATHER;
	}

	public IWingsType getType() {
		return this.type;
	}

	@Nullable
	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.CHEST;
	}

	@Override
	public SoundEvent getEquipSound() {
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
	public int getEnchantmentValue() {
		return 15;
	}

	public static boolean isEntityFloating (LivingEntity entity){
		return entity.hasEffect(MobEffects.SLOW_FALLING) || entity.hasEffect(MobEffects.LEVITATION);
	}

}

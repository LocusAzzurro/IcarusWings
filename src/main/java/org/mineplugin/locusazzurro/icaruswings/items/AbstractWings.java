package org.mineplugin.locusazzurro.icaruswings.items;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.IWingsType;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class AbstractWings extends ElytraItem {

	protected IWingsType type;

	protected AbstractWings(IWingsType type) {
		super(new Properties().tab(ModGroup.itemGroup).durability(type.getDurability()));
		this.type = type;
	}

	protected AbstractWings(IWingsType type, Rarity rarity) {
		super(new Properties().tab(ModGroup.itemGroup).durability(type.getDurability()).rarity(rarity));
		this.type = type;
	}

	protected AbstractWings() {
		super(new Properties().tab(ModGroup.itemGroup).durability(WingsType.FEATHER.getDurability()));
		this.type = WingsType.FEATHER;
	}

	public IWingsType getType() {
		return this.type;
	}

	@Nullable
	@Override
	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return EquipmentSlotType.CHEST;
	}

	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
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
	public boolean elytraFlightTick(ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
		return super.elytraFlightTick(stack, entity, flightTicks);
	}
	
	@Override
	public int getEnchantmentValue() {
		return 15;
	}

}

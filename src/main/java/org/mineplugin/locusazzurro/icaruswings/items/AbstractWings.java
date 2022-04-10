package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.data.IWingsType;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import javax.annotation.Nullable;

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
	public EquipmentSlot getEquipmentSlot(net.minecraft.world.item.ItemStack stack) {
		return EquipmentSlot.CHEST;
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

}

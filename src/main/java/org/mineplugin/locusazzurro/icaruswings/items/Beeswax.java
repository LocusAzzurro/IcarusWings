package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class Beeswax extends Item{

	public Beeswax() {
		super(new Properties().tab(ModGroup.itemGroup).food(food));
	}
	
	private static final Food food = (new Food.Builder())
		.saturationMod(0)
		.nutrition(1)
		.build();

	@SubscribeEvent
	public static void onFuelTime(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == ItemRegistry.beeswax.get())
		event.setBurnTime(200);
	}
	
	public int getBurnTime(ItemStack stack) {
		return 200;
	}
	
}


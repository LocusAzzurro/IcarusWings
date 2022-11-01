package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import net.minecraft.world.item.Item.Properties;

@Mod.EventBusSubscriber()
public class Beeswax extends Item {

	public Beeswax() {
		super(new Properties().tab(ModGroup.itemGroup).food(food));
	}
	
	private static final FoodProperties food = (new FoodProperties.Builder())
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


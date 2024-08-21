package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

@EventBusSubscriber()
public class Beeswax extends Item {

	public Beeswax() {
		super(new Properties().food(food));
	}
	
	private static final FoodProperties food = (new FoodProperties.Builder())
		.saturationMod(0)
		.nutrition(1)
		.build();

	@SubscribeEvent
	public static void onFuelTime(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == ItemRegistry.BEESWAX.get())
			event.setBurnTime(200);
	}
	
}


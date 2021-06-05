package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.ModGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class Beeswax extends Item{

	public Beeswax() {
		super(new Properties().tab(ModGroup.itemGroup).food(food));
	}
	
	private static final Food food = (new Food.Builder())
		.saturationMod(2)
		.nutrition(2)
		.build();

	@SubscribeEvent
	public static void onFuelTime(FurnaceFuelBurnTimeEvent event) {
		event.setBurnTime(200);
		event.setResult(Event.Result.ALLOW);
	};
}


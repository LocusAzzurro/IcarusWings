package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class QuantumFuel extends Item{

	public QuantumFuel() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(4));
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return true;
	}
	
	//TODO: 1 nether star makes 4 fuel, wings recharger needs repair kit + 2 fuel
}

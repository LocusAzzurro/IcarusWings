package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class QuantumFuel extends Item{

	public QuantumFuel() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(4).rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return true;
	}
	
}

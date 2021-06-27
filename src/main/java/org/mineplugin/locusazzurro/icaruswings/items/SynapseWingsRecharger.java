package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SynapseWingsRecharger extends Item{
	
	public SynapseWingsRecharger() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(1).fireResistant());
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return true;
	}
}

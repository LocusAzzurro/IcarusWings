package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SynapseRepairKit extends Item{
	
	public SynapseRepairKit() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(1).fireResistant());
	}
	
	//TODO; made from restored relic or expensive material, add tag to all restored relics for recipe
}

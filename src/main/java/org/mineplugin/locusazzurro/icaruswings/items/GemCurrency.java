package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.world.item.Item;

public class GemCurrency extends Item{

	public GemCurrency() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
	
}

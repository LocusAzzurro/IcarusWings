package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.world.item.Item;

import net.minecraft.world.item.Item.Properties;

public class GemCurrency extends Item{

	public GemCurrency() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
	
}

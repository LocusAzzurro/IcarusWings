package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;

public class WorldGem extends Item{

	public WorldGem() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
	
}

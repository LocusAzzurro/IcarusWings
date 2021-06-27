package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;

public class WorldEssence extends Item{

	public WorldEssence() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
	//TODO: zephir from wandering trader, nether from piglin, void from outer islands while flying
}

package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;

public class MagicMembrane extends Item{

	public MagicMembrane() {
		super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
}

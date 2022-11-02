package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class MagicMembrane extends Item{

	public MagicMembrane() {
		super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
}

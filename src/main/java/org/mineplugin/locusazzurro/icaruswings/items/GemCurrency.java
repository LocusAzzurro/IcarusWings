package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class GemCurrency extends Item{

	public GemCurrency() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}
	
}

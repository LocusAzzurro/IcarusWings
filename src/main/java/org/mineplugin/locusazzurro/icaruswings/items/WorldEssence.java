package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class WorldEssence extends Item {
	public WorldEssence() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
	}

}

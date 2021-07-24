package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.ModItemTier;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class SynapseAlloySword extends SwordItem{

	public SynapseAlloySword() {
		super(ModItemTier.SYNAPSE_ALLOY, 4, -2.0F, new Item.Properties().tab(ModGroup.itemGroup));
	}

}

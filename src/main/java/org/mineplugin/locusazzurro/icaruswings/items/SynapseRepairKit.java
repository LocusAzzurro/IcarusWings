package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class SynapseRepairKit extends Item{
	
	public SynapseRepairKit() {
		super(new Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
	}
	
}

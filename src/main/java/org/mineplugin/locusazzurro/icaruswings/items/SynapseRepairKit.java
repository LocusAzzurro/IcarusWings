package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class SynapseRepairKit extends Item{
	
	public SynapseRepairKit() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
	}
	
}

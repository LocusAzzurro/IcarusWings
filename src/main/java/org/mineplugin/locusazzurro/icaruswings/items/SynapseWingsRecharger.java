package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class SynapseWingsRecharger extends Item{
	
	public SynapseWingsRecharger() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return true;
	}
}

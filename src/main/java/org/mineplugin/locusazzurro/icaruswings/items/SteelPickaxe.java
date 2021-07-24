package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.ModItemTier;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class SteelPickaxe extends PickaxeItem{

	public SteelPickaxe() {
		super(ModItemTier.STEEL, 1, -2.5F, new Item.Properties().tab(ModGroup.itemGroup));
	}

}

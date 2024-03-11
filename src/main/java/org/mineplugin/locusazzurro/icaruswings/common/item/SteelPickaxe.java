package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModItemTier;

public class SteelPickaxe extends PickaxeItem {

	public SteelPickaxe() {
		super(ModItemTier.STEEL, 1, -2.5F, new Item.Properties());
	}

}

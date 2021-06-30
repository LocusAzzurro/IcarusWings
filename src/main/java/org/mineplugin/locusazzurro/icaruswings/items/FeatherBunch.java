package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.WingsMaterial;

import net.minecraft.item.Item;

public class FeatherBunch extends Item {

	public WingsMaterial type;

	public FeatherBunch(WingsMaterial type) {
		super(new Properties().tab(ModGroup.itemGroup));
		this.type = type;
	}

	public WingsMaterial getType() {
		return this.type;
	}
}

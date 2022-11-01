package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import net.minecraft.world.item.Item.Properties;

public class FeatherBunch extends Item {

	public WingsType type;

	public FeatherBunch(WingsType type) {
		super(new Properties().tab(ModGroup.itemGroup));
		this.type = type;
	}

	public WingsType getType() {
		return this.type;
	}
}

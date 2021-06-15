package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;

public class FeatherBunch extends Item {

	public FeatherWings.FeatherWingsType type;

	public FeatherBunch(FeatherWings.FeatherWingsType type) {
		super(new Properties().tab(ModGroup.itemGroup));
		this.type = type;
	}

	public FeatherWings.FeatherWingsType getType() {
		return this.type;
	}
}

package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;

public class FeatherBunch extends Item {

	public WingsType type;

	public FeatherBunch(WingsType type) {
		super(new Properties());
		this.type = type;
	}

	public WingsType getType() {
		return this.type;
	}
}

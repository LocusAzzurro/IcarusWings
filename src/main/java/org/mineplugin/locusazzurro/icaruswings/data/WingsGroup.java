package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WingsGroup extends ItemGroup{
	public WingsGroup() {
	super("icaruswings_group");
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemRegistry.featherWings.get());
	}
}

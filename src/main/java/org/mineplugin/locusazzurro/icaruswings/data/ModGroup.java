package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class ModGroup {
	public static final CreativeModeTab itemGroup = new WingsGroup();

	public static class WingsGroup extends CreativeModeTab {
		public WingsGroup() {
		super("icaruswings_group");
		}

		@Override
		public net.minecraft.world.item.ItemStack makeIcon() {
			return new ItemStack(ItemRegistry.featherWings.get());
		}
	}
}

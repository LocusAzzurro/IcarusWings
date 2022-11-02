package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class ModGroup {
	public static final CreativeModeTab itemGroup = new WingsGroup();

	public static class WingsGroup extends CreativeModeTab {
		public WingsGroup() {
		super("icaruswings_group");
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegistry.iconBadge.get());
		}
	}
}

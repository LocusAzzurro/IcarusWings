package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class ModGroup {
	public static final ItemGroup itemGroup = new WingsGroup();

	public static class WingsGroup extends ItemGroup{
		public WingsGroup() {
		super("icaruswings_group");
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegistry.featherWings.get());
		}
	}
}

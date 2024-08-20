package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RefinedBeeswaxBar extends Item{
	
	private boolean isGlistering = false;
	public RefinedBeeswaxBar() {
		this(false);
	}
	
	public RefinedBeeswaxBar(boolean isGlistering) {
		super(new Properties());
		this.isGlistering = isGlistering;
	}

	public boolean isPiglinCurrency(ItemStack stack) {
		return ((RefinedBeeswaxBar)stack.getItem()).isGlistering;
	}
}

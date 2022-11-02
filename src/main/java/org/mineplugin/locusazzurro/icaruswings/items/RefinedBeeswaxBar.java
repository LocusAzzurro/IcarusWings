package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class RefinedBeeswaxBar extends Item{
	
	private boolean isGlistering = false;
	public RefinedBeeswaxBar() {
		this(false);
	}
	
	public RefinedBeeswaxBar(boolean isGlistering) {
		super(new Properties().tab(ModGroup.itemGroup));
		this.isGlistering = isGlistering;
	}
	
	public boolean isGlistering() {
		return this.isGlistering;
	}

	public boolean isPiglinCurrency(ItemStack stack) {
		return ((RefinedBeeswaxBar)stack.getItem()).isGlistering;
	}
}

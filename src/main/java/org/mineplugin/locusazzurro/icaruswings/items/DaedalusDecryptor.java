package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DaedalusDecryptor extends Item {
	
	private boolean isPersistent = true;
	
	public DaedalusDecryptor() {
		this(true);
	}
	
	public DaedalusDecryptor(boolean isPersistent) {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(1));
		this.isPersistent = isPersistent;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(ItemRegistry.daedalusDecryptor.get());
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		if(stack.getItem() instanceof DaedalusDecryptor) {
			return ((DaedalusDecryptor)stack.getItem()).isPersistent;
		}
		return true;
	}
	
	//TODO: manuscript + relic -> decryptor
}

package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DaedalusDecryptor extends Item {

	public DaedalusDecryptor() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(1));
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(ItemRegistry.daedalusDecryptor.get());
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	//TODO: manuscript + relic -> decryptor
}

package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class DaedalusDecryptor extends Item {
	
	private boolean isPersistent = true;
	
	public DaedalusDecryptor() {
		this(true);
	}
	
	public DaedalusDecryptor(boolean isPersistent) {
		super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
		this.isPersistent = isPersistent;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		return new ItemStack(ItemRegistry.DAEDALUS_DECRYPTOR.get());
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		if(stack.getItem() instanceof DaedalusDecryptor) {
			return ((DaedalusDecryptor)stack.getItem()).isPersistent;
		}
		return true;
	}
	
}

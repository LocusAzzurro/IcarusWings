package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStackTemplate;
import org.jspecify.annotations.Nullable;
import net.minecraft.world.item.Rarity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class DaedalusDecryptor extends Item {
	
	private final boolean isPersistent;
	
	public DaedalusDecryptor(boolean isPersistent) {
		this(isPersistent, new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	public DaedalusDecryptor(boolean isPersistent, Properties properties) {
		super(properties.stacksTo(1).rarity(Rarity.UNCOMMON));
		this.isPersistent = isPersistent;
	}

	@Override
	public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance itemStack) {
		return isPersistent ? new ItemStackTemplate(this) : null;
	}
	
}

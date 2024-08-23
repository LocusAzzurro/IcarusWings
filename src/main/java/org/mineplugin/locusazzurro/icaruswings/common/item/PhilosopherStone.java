package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModFoods;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class PhilosopherStone extends Item {

	public PhilosopherStone() {
		super(new Item.Properties().stacksTo(1).food(ModFoods.PHILOSOPHER_STONE));
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 100;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
		return new ItemStack(ItemRegistry.PHILOSOPHER_STONE.get());
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}
}

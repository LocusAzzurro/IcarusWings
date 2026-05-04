package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import org.jspecify.annotations.Nullable;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModFoods;

public class PhilosopherStone extends Item {

	public PhilosopherStone(Item.Properties properties) {
		super(properties.stacksTo(1).food(ModFoods.PHILOSOPHER_STONE));
	}

	@Override
	public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance itemStack) {
		return new ItemStackTemplate(this);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 100;
	}

}

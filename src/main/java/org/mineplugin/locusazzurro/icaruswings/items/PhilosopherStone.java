package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class PhilosopherStone extends Item {

	public PhilosopherStone() {
		super(new Item.Properties().stacksTo(1).food(food));
	}
	
	private static final FoodProperties food = (new FoodProperties.Builder())
			.saturationMod(10)
			.nutrition(10)
			.alwaysEat()
			.effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1)
			.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1)
			.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1)
			.effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2), 1)
			.build();
	
	@Override
	public int getUseDuration(ItemStack stackIn) {
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

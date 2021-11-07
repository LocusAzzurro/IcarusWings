package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PhilosopherStone extends Item{

	public PhilosopherStone() {
		super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1).food(food));
	}
	
	private static final Food food = (new Food.Builder())
			.saturationMod(10)
			.nutrition(10)
			.alwaysEat()
			.effect(() -> {return new EffectInstance(Effects.REGENERATION, 400, 1);}, 1)
			.effect(() -> {return new EffectInstance(Effects.DAMAGE_RESISTANCE, 1200, 1);}, 1)
			.effect(() -> {return new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0);}, 1)
			.effect(() -> {return new EffectInstance(Effects.HEALTH_BOOST, 1200, 2);}, 1)
			.build();
	
	@Override
	public int getUseDuration(ItemStack stackIn) {
		return 100;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(ItemRegistry.philosopherStone.get());
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
}

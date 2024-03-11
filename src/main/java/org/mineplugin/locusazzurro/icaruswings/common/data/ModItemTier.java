package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.IWLazy;

import java.util.function.Supplier;

public enum ModItemTier implements Tier {
	
	STEEL(2, 300, 7.0F, 2.5F, 9, () -> {return Ingredient.of(ItemRegistry.STEEL_INGOT.get());}),
	SYNAPSE_ALLOY(4, 2400, 10.0F, 5.0F, 16, () -> {return Ingredient.of(ItemRegistry.SYNAPSE_ALLOY_INGOT.get());});
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final IWLazy<Ingredient> repairIngredient;

	ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = IWLazy.of(repairMaterial);
	}
	
	@Override
	public int getUses() {
		return this.maxUses;
	}

	@Override
	public float getSpeed() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.attackDamage;
	}

	@Override
	public int getLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

}

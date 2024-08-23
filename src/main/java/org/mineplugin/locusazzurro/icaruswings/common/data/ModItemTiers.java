package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class ModItemTiers {

    public static final Tier STEEL = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 300, 7.0F, 2.5F, 9, () -> Ingredient.of(ItemRegistry.STEEL_INGOT.get()));
    public static final Tier SYNAPSE_ALLOY = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2400, 10.0F, 5.0F, 16, () -> Ingredient.of(ItemRegistry.SYNAPSE_ALLOY_INGOT.get()));

}

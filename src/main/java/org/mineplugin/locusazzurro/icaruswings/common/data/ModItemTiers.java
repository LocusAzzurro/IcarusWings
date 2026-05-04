package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModItemTiers {
    public static final ToolMaterial STEEL = new ToolMaterial(
        BlockTags.INCORRECT_FOR_IRON_TOOL,
        300,
        7.0F,
        2.5F,
        9,
        ItemTags.IRON_TOOL_MATERIALS
    );

    public static final ToolMaterial SYNAPSE_ALLOY = new ToolMaterial(
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
        2400,
        10.0F,
        5.0F,
        16,
        ItemTags.NETHERITE_TOOL_MATERIALS
    );
}
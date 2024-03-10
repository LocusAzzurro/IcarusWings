package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.StairBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class RefinedBeeswaxStairs extends StairBlock {

	public RefinedBeeswaxStairs() {
		super( () -> BlockRegistry.REFINED_BEESWAX_BLOCK.get().defaultBlockState(), Properties.copy(BlockRegistry.REFINED_BEESWAX_BLOCK.get()));
	}
}

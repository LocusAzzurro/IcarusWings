package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.StairBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RefinedBeeswaxStairs extends StairBlock {

	public RefinedBeeswaxStairs() {
		super( () -> BlockRegistry.refinedBeeswaxBlock.get().defaultBlockState(), Properties.copy(BlockRegistry.refinedBeeswaxBlock.get()));
	}
}

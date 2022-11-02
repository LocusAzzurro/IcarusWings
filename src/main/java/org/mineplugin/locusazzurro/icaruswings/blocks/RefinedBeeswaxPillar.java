package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RefinedBeeswaxPillar extends RotatedPillarBlock {
	
	public RefinedBeeswaxPillar() {
		super(Properties.copy(BlockRegistry.refinedBeeswaxBlock.get()));
	}
}

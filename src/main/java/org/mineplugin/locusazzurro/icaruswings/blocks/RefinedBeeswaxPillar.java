package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class RefinedBeeswaxPillar extends RotatedPillarBlock {
	
	public RefinedBeeswaxPillar() {
		super(Properties.copy(BlockRegistry.REFINED_BEESWAX_BLOCK.get()));
	}
}

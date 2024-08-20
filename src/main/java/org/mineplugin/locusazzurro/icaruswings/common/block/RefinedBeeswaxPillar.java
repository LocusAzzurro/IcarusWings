package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.world.level.block.RotatedPillarBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class RefinedBeeswaxPillar extends RotatedPillarBlock {
	
	public RefinedBeeswaxPillar() {
		super(Properties.ofFullCopy(BlockRegistry.REFINED_BEESWAX_BLOCK.get()));
	}
}

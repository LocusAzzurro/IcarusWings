package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.world.level.block.SlabBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class RefinedBeeswaxSlab extends SlabBlock {
	
	public RefinedBeeswaxSlab() {
		super(Properties.copy(BlockRegistry.REFINED_BEESWAX_BLOCK.get()));
	}
}

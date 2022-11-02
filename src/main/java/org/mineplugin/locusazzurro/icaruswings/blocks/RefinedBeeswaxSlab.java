package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.SlabBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RefinedBeeswaxSlab extends SlabBlock {
	
	public RefinedBeeswaxSlab() {
		super(Properties.copy(BlockRegistry.refinedBeeswaxBlock.get()));
	}
}

package org.mineplugin.locusazzurro.icaruswings.blocks;

import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.StairsBlock;

public class RefinedBeeswaxStairs extends StairsBlock{

	public RefinedBeeswaxStairs() {
		super( () -> BlockRegistry.refinedBeeswaxBlock.get().defaultBlockState(), 
				AbstractBlock.Properties.copy(new RefinedBeeswaxBlock()));
	}
}

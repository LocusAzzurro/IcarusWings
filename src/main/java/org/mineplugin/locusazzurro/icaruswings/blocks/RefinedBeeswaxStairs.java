package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.StairBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class RefinedBeeswaxStairs extends StairBlock {

	public RefinedBeeswaxStairs() {
		super( () -> BlockRegistry.refinedBeeswaxBlock.get().defaultBlockState(), 
				net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy(new RefinedBeeswaxBlock()));
	}
}

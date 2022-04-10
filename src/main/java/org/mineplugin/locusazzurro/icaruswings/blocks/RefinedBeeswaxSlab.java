package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RefinedBeeswaxSlab extends SlabBlock {
	
	public RefinedBeeswaxSlab() {
		super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy(new RefinedBeeswaxBlock()));
	}
}

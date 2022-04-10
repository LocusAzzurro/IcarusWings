package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;

public class RefinedBeeswaxPillar extends RotatedPillarBlock {
	
	public RefinedBeeswaxPillar() {
		super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy(new RefinedBeeswaxBlock()));
	}
}

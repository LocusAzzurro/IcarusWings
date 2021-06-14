package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.RotatedPillarBlock;

public class RefinedBeeswaxPillar extends RotatedPillarBlock{
	
	public RefinedBeeswaxPillar() {
		super(AbstractBlock.Properties.copy(new RefinedBeeswaxBlock()));
	}
}

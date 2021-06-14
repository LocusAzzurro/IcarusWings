package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlabBlock;

public class RefinedBeeswaxSlab extends SlabBlock{
	
	public RefinedBeeswaxSlab() {
		super(AbstractBlock.Properties.copy(new RefinedBeeswaxBlock()));
	}
}

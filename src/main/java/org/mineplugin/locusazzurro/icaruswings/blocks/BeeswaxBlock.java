package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BeeswaxBlock extends Block{

	public BeeswaxBlock() {
		super(AbstractBlock
				.Properties.of(Material.CLAY)
				.strength(0.5f)
				.speedFactor(1.1f)
				.friction(0.9f)
				.sound(SoundType.HONEY_BLOCK)
				);
		
	}
	

}

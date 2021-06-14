package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RefinedBeeswaxBlock extends Block{

	public RefinedBeeswaxBlock() {
		super(AbstractBlock
				.Properties.of(Material.STONE)
				.strength(1.5f, 6.0f)
				.speedFactor(1.2f)
				.friction(0.8f)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops()
				.harvestLevel(1)
				.harvestTool(ToolType.PICKAXE)
				);
		
	}
	

}
package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class RefinedBeeswaxBlock extends Block{

	public RefinedBeeswaxBlock() {
		super(BlockBehaviour.Properties.of()
				.strength(1.5f, 6.0f)
				.speedFactor(1.2f)
				.friction(0.8f)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops()
				);
		
	}
	

}
package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;

public class BeeswaxBlock extends Block {

	public BeeswaxBlock() {
		super(BlockBehaviour.Properties.of(Material.CLAY)
				.strength(0.5f)
				.speedFactor(1.1f)
				.friction(0.9f)
				.sound(SoundType.HONEY_BLOCK)
				);
		
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face)
	{
		return 200;
	}
	

}

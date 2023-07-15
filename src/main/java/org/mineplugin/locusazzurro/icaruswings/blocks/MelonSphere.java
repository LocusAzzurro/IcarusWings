package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MelonSphere extends Block {
	
	private static final VoxelShape SHAPE = box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);
	
	public MelonSphere() {
		super(BlockBehaviour.Properties.of()
				.strength(1.2f)
				.noOcclusion()
				.sound(SoundType.WOOD));
	}
	@Override
	public VoxelShape getShape(BlockState stateIn, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		return SHAPE;
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_149645_1_) {
		return RenderShape.MODEL;
	}
}

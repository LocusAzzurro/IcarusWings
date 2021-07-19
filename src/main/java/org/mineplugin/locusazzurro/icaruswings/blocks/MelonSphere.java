package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class MelonSphere extends Block{
	
	private static final VoxelShape SHAPE = box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);
	
	public MelonSphere() {
		super(AbstractBlock
				.Properties.of(Material.VEGETABLE)
				.strength(1.2f)
				.noOcclusion()
				.sound(SoundType.WOOD));
	}
	@Override
	public VoxelShape getShape(BlockState stateIn, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}
	
	@Override
	public BlockRenderType getRenderShape(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}
}

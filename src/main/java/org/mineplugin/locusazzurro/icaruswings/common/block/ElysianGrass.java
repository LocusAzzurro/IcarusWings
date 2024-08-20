package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class ElysianGrass extends TallGrassBlock {

    public ElysianGrass() {
        super(BlockBehaviour.Properties.of()
                .noCollission()
                .instabreak()
                .replaceable()
                .sound(SoundType.GRASS)
        );
    }

    @Override
    protected boolean mayPlaceOn(BlockState stateIn, BlockGetter iBlockReader, BlockPos blockPos) {
        return stateIn.is(BlockRegistry.ELYSIAN_GRASS_BLOCK.get()) || stateIn.is(BlockRegistry.ELYSIAN_SOIL.get());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_180670_1_, RandomSource p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return false;
    }
}

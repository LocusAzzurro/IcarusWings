package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class ElysianGrass extends TallGrassBlock {

    public ElysianGrass() {
        super(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT)
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS)
        );
    }

    @Override
    protected boolean mayPlaceOn(BlockState stateIn, BlockGetter iBlockReader, BlockPos blockPos) {
        return stateIn.is(BlockRegistry.elysianGrassBlock.get()) || stateIn.is(BlockRegistry.elysianSoil.get());
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_180670_1_, RandomSource p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return false;
    }
}

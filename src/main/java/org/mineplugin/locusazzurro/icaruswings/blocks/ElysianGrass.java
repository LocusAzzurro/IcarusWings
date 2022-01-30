package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import java.util.Random;

public class ElysianGrass extends TallGrassBlock {

    public ElysianGrass() {
        super(AbstractBlock
                .Properties.of(Material.REPLACEABLE_PLANT)
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS)
        );
    }

    @Override
    protected boolean mayPlaceOn(BlockState stateIn, IBlockReader iBlockReader, BlockPos blockPos) {
        return stateIn.is(BlockRegistry.elysianGrassBlock.get()) || stateIn.is(BlockRegistry.elysianSoil.get());
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return false;
    }
}

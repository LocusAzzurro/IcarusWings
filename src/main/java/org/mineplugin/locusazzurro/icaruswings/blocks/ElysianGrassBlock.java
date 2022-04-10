package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ElysianGrassBlock extends GrassBlock {

    public ElysianGrassBlock() {
        super(BlockBehaviour.Properties.of(Material.GRASS)
                .randomTicks()
                .strength(0.7F)
                .sound(SoundType.GRASS)
        );
    }
}

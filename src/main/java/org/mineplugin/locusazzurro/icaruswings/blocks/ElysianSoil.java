package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ElysianSoil extends Block {

    public ElysianSoil() {
        super(BlockBehaviour.Properties.of(Material.DIRT)
                .strength(0.7f)
                .sound(SoundType.GRAVEL)
                .randomTicks()
        );
    }
}

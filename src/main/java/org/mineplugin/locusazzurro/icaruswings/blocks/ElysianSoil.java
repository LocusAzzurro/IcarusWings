package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ElysianSoil extends Block {

    public ElysianSoil() {
        super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.DIRT)
                .strength(0.7f)
                .sound(SoundType.GRAVEL)
                .randomTicks()
        );
    }
}

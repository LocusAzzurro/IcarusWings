package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class ElysianGrassBlock extends GrassBlock implements IGrowable {

    public ElysianGrassBlock() {
        super(AbstractBlock
                .Properties.of(Material.GRASS)
                .randomTicks()
                .strength(0.7F)
                .sound(SoundType.GRASS)
        );
    }
}

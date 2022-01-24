package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ElysianGrassBlock extends Block {

    public ElysianGrassBlock() {
        super(AbstractBlock
                .Properties.of(Material.GRASS)
                .strength(0.7f)
                .sound(SoundType.GRASS)
                .randomTicks()
        );

    }
}

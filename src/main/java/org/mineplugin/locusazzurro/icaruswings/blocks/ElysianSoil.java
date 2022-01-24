package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ElysianSoil extends Block {

    public ElysianSoil() {
        super(AbstractBlock
                .Properties.of(Material.DIRT)
                .strength(0.7f)
                .sound(SoundType.GRAVEL)
                .randomTicks()
        );
    }
}

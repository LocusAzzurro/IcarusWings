package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FlaxCrop extends CropsBlock {

    public FlaxCrop() {
        super(AbstractBlock.Properties.of(Material.PLANT)
                .noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}

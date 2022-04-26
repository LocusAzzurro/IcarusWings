package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class FlaxCrop extends CropBlock {

    public FlaxCrop() {
        super(BlockBehaviour.Properties.of(Material.PLANT)
                .noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}

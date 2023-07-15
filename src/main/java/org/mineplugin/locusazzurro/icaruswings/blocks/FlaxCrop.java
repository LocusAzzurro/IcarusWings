package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class FlaxCrop extends CropBlock {

    public FlaxCrop() {
        super(BlockBehaviour.Properties.of()
                .noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}

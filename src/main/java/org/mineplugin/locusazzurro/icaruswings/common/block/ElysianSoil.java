package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class ElysianSoil extends Block {

    public ElysianSoil() {
        super(BlockBehaviour.Properties.of()
                .strength(0.7f)
                .sound(SoundType.GRAVEL)
                .randomTicks()
        );
    }
}

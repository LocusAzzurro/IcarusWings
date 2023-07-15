package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class GoldenWoolBlock extends Block {

    public GoldenWoolBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(0.8f)
                .lightLevel((p) -> 3)
                .sound(SoundType.WOOL)
        );
    }
}

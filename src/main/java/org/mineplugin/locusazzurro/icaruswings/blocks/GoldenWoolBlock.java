package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class GoldenWoolBlock extends Block {

    public GoldenWoolBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOL)
                .strength(0.8f)
                .lightLevel((p) -> 3)
                .sound(SoundType.WOOL)
        );
    }
}

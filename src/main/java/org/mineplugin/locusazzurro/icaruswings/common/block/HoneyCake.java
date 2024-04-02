package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class HoneyCake extends CakeBlock {

    public HoneyCake() {
        super(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL).noLootTable());
    }
}

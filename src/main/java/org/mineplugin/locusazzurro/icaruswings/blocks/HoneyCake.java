package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class HoneyCake extends CakeBlock {

    public HoneyCake() {
        super(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL));
    }
}

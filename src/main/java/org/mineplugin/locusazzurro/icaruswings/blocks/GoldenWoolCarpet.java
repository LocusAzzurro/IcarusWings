package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.CarpetBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class GoldenWoolCarpet extends CarpetBlock {

    public GoldenWoolCarpet() {
        super(Properties.copy(BlockRegistry.beeswaxBlock.get()));
    }
}

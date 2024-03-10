package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.CarpetBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class GoldenWoolCarpet extends CarpetBlock {

    public GoldenWoolCarpet() {
        super(Properties.copy(BlockRegistry.GOLDEN_WOOL_BLOCK.get()));
    }
}

package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Material;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;

public class GreekFireBlock extends LiquidBlock {

    public GreekFireBlock(){
        super(FluidRegistry.greekFire, Block.Properties.of(Material.LAVA).noCollission().strength(100.0F).noLootTable());
    }


}

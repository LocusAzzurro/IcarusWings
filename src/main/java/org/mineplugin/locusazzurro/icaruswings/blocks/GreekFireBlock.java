package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.*;

public class GreekFireBlock extends FlowingFluidBlock {

    public GreekFireBlock(){
        super(FluidRegistry.greekFire, Block.Properties.of(Material.LAVA).noCollission().strength(100.0F).noDrops());
    }


}

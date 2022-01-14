package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.*;

public class GreekFire extends FlowingFluidBlock {

    public GreekFire(){
        super(FluidRegistry.greekFire, Block.Properties.of(Material.LAVA).noCollission().strength(100.0F).noDrops());
    }

    public static final ResourceLocation GREEK_FIRE_STILL = new ResourceLocation(ModData.MOD_ID, "block/greek_fire_still");
    public static final ResourceLocation GREEK_FIRE_FLOWING = new ResourceLocation(ModData.MOD_ID, "block/greek_fire_flow");

    public static ForgeFlowingFluid.Properties fluidProperties = new ForgeFlowingFluid.Properties(greekFire, greekFireFlowing,
            FluidAttributes.builder(GREEK_FIRE_STILL, GREEK_FIRE_FLOWING)
                    .color(0xfff79059).density(4000).viscosity(4000))
                    .bucket(ItemRegistry.greekFireBucket).block(BlockRegistry.greekFire)
                    .slopeFindDistance(3).explosionResistance(100F);

    public static class Source extends ForgeFlowingFluid.Source{
        public Source() {
            super(fluidProperties);
        }
    }

    public static class Flowing extends ForgeFlowingFluid.Flowing{
        public Flowing() {
            super(fluidProperties);
        }
    }
}

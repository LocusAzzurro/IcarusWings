package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.greekFire;
import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.greekFireFlowing;

public abstract class GreekFireFluid extends ForgeFlowingFluid{

    public static final ResourceLocation GREEK_FIRE_STILL = new ResourceLocation(ModData.MOD_ID, "block/greek_fire_still");
    public static final ResourceLocation GREEK_FIRE_FLOWING = new ResourceLocation(ModData.MOD_ID, "block/greek_fire_flow");

    public static ForgeFlowingFluid.Properties fluidProperties = new ForgeFlowingFluid.Properties(greekFire, greekFireFlowing,
            FluidAttributes.builder(GREEK_FIRE_STILL, GREEK_FIRE_FLOWING)
                    .color(0xfff79059).density(4000).viscosity(7000).luminosity(15).temperature(1500)
                    .translationKey("block.locusazzurro_icaruswings.greek_fire")
                    .sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)) //todo custom sound events
                    .bucket(ItemRegistry.greekFireBucket).block(BlockRegistry.greekFire)
                    .slopeFindDistance(1).explosionResistance(100F);

    public GreekFireFluid(Properties properties) {
        super(properties);
    }

    //todo particles animateTick

    public static class Source extends GreekFireFluid{
        public Source() {
            super(fluidProperties);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
    }

    public static class Flowing extends GreekFireFluid{
        public Flowing() {
            super(fluidProperties);
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }
}

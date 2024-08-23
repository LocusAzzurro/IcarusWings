package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.GREEK_FIRE;
import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.GREEK_FIRE_FLUID_TYPE;

public abstract class GreekFireFluid extends BaseFlowingFluid {

    public static final ResourceLocation GREEK_FIRE_STILL = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "block/greek_fire_still");
    public static final ResourceLocation GREEK_FIRE_FLOWING = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "block/greek_fire_flow");
    public static BaseFlowingFluid.Properties fluidProperties = new BaseFlowingFluid.Properties(GREEK_FIRE_FLUID_TYPE, GREEK_FIRE, FluidRegistry.GREEK_FIRE_FLOWING)
            .bucket(ItemRegistry.GREEK_FIRE_BUCKET).block(BlockRegistry.GREEK_FIRE).slopeFindDistance(1).explosionResistance(100F);

    public GreekFireFluid(BaseFlowingFluid.Properties properties) {
        super(properties);
    }

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
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }
}

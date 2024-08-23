package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.block.GreekFireFluid;
import org.mineplugin.locusazzurro.icaruswings.common.block.GreekFireFluidType;

import java.util.function.Supplier;

public class FluidRegistry {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, IcarusWings.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, IcarusWings.MOD_ID);

    public static Supplier<FluidType> GREEK_FIRE_FLUID_TYPE = FLUID_TYPES.register("greek_fire_fluid_type", GreekFireFluidType::new);
    public static Supplier<FlowingFluid> GREEK_FIRE = FLUIDS.register("greek_fire", GreekFireFluid.Source::new);
    public static Supplier<FlowingFluid> GREEK_FIRE_FLOWING = FLUIDS.register("greek_fire_flowing", GreekFireFluid.Flowing::new);

}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.blocks.GreekFireFluid;
import org.mineplugin.locusazzurro.icaruswings.blocks.GreekFireFluidType;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class FluidRegistry {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ModData.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ModData.MOD_ID);

    public static RegistryObject<FluidType> greekFireFluidType = FLUID_TYPES.register("greek_fire_fluid_type", GreekFireFluidType::new);
    public static RegistryObject<FlowingFluid> greekFire = FLUIDS.register("greek_fire", GreekFireFluid.Source::new);
    public static RegistryObject<FlowingFluid> greekFireFlowing = FLUIDS.register("greek_fire_flowing", GreekFireFluid.Flowing::new);

}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.blocks.GreekFireFluid;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class FluidRegistry {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ModData.MOD_ID);
    public static RegistryObject<FlowingFluid> greekFire = FLUIDS.register("greek_fire", GreekFireFluid.Source::new);
    public static net.minecraftforge.registries.RegistryObject<FlowingFluid> greekFireFlowing = FLUIDS.register("greek_fire_flowing", GreekFireFluid.Flowing::new);

}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.blocks.GreekFireBlock;
import org.mineplugin.locusazzurro.icaruswings.blocks.GreekFireFluid;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class FluidRegistry {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ModData.MOD_ID);
    public static RegistryObject<FlowingFluid> greekFire = FLUIDS.register("greek_fire", GreekFireFluid.Source::new);
    public static RegistryObject<FlowingFluid> greekFireFlowing = FLUIDS.register("greek_fire_flowing", GreekFireFluid.Flowing::new);

}

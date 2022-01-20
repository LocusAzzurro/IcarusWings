package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class ParticleRegistry {

    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModData.MOD_ID);

    public static RegistryObject<BasicParticleType> plasmaTrail = PARTICLES.register("plasma_trail",
            () -> new BasicParticleType(true));
    public static RegistryObject<BasicParticleType> goldenSparkle = PARTICLES.register("golden_sparkle",
            () -> new BasicParticleType(true));


}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

public class ParticleRegistry {

    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModData.MOD_ID);

    public static RegistryObject<SimpleParticleType> NULLITY = PARTICLES.register("nullity",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> PLASMA_TRAIL = PARTICLES.register("plasma_trail",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> ELECTRONIC_BIT = PARTICLES.register("electronic_bit",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> GOLDEN_SPARKLE_BASE = PARTICLES.register("golden_sparkle_base",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> GOLDEN_SPARKLE = PARTICLES.register("golden_sparkle",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> TIME_RIFT_EXPLOSION = PARTICLES.register("time_rift_explosion",
            () -> new SimpleParticleType(true));



}

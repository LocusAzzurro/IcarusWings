package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.function.Supplier;

public class ParticleRegistry {

    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, ModData.MOD_ID);

    public static Supplier<SimpleParticleType> NULLITY = PARTICLES.register("nullity",
            () -> new SimpleParticleType(true));
    public static Supplier<SimpleParticleType> PLASMA_TRAIL = PARTICLES.register("plasma_trail",
            () -> new SimpleParticleType(true));
    public static Supplier<SimpleParticleType> ELECTRONIC_BIT = PARTICLES.register("electronic_bit",
            () -> new SimpleParticleType(true));
    public static Supplier<SimpleParticleType> GOLDEN_SPARKLE_BASE = PARTICLES.register("golden_sparkle_base",
            () -> new SimpleParticleType(true));
    public static Supplier<SimpleParticleType> GOLDEN_SPARKLE = PARTICLES.register("golden_sparkle",
            () -> new SimpleParticleType(true));
    public static Supplier<SimpleParticleType> TIME_RIFT_EXPLOSION = PARTICLES.register("time_rift_explosion",
            () -> new SimpleParticleType(true));



}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class ParticleRegistry {

    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModData.MOD_ID);

    public static net.minecraftforge.registries.RegistryObject<SimpleParticleType> nullity = PARTICLES.register("nullity",
            () -> new SimpleParticleType(true));
    public static net.minecraftforge.registries.RegistryObject<SimpleParticleType> plasmaTrail = PARTICLES.register("plasma_trail",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> electronicBit = PARTICLES.register("electronic_bit",
            () -> new SimpleParticleType(true));
    public static net.minecraftforge.registries.RegistryObject<SimpleParticleType> goldenSparkle = PARTICLES.register("golden_sparkle",
            () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> timeRiftExplosion = PARTICLES.register("time_rift_explosion",
            () -> new SimpleParticleType(true));



}

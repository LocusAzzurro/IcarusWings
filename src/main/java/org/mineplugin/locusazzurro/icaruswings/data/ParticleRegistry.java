package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {

    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModData.MOD_ID);

    public static RegistryObject<BasicParticleType> plasmaTrail = PARTICLES.register("plasma_trail",
            () -> new BasicParticleType(true));


}

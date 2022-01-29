package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.particles.*;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientParticleFactoryHandler {

    @SubscribeEvent
    public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event){
        ParticleManager particleEngine = Minecraft.getInstance().particleEngine;
        particleEngine.register(ParticleRegistry.nullity.get(), NullityParticle.Factory::new);
        particleEngine.register(ParticleRegistry.plasmaTrail.get(), PlasmaTrailParticle.Factory::new);
        particleEngine.register(ParticleRegistry.electronicBit.get(), ElectronicBitParticle.Factory::new);
        particleEngine.register(ParticleRegistry.goldenSparkle.get(), GoldenSparkleParticle.Factory::new);
        particleEngine.register(ParticleRegistry.timeRiftExplosion.get(), TimeRiftExplosionParticle.Factory::new);
    }

}

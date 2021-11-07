package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.particles.PlasmaTrailParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientParticleFactoryHandler {

    @SubscribeEvent
    public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.plasmaTrail.get(), PlasmaTrailParticle.Factory::new);
    }

}

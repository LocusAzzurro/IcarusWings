package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mineplugin.locusazzurro.icaruswings.data.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.render.GoldenRamRenderer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamRenderer::new);

    }
}

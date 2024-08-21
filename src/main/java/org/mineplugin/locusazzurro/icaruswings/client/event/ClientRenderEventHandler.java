package org.mineplugin.locusazzurro.icaruswings.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.mineplugin.locusazzurro.icaruswings.client.render.layers.WingsLayer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientRenderEventHandler {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SubscribeEvent
    public static void addPlayerWingsLayer (RenderPlayerEvent.Pre event){
        event.getRenderer().addLayer(new WingsLayer<>((RenderLayerParent) event.getRenderer(), Minecraft.getInstance().getEntityModels()));
    }

}

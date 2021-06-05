package org.mineplugin.locusazzurro.icaruswings.render;

import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
public class WingsRenderer {
	
	@SubscribeEvent
	public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
		PlayerEntity player = event.getPlayer();
        PlayerRenderer renderer = event.getRenderer();
        renderer.addLayer(new FeatherWingsLayer<>(renderer));
		
	}
}

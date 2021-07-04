package org.mineplugin.locusazzurro.icaruswings.render;


import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WingsRenderer {
	
	private static boolean init = false;
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onRenderPlayer(RenderPlayerEvent.Post event) {
		PlayerRenderer renderer = event.getRenderer();
		if(!init) {
		renderer.addLayer(new WingsLayer<>(renderer));
		init = true;
		}

	}
}

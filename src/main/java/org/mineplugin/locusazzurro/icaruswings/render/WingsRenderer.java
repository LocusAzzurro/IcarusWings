package org.mineplugin.locusazzurro.icaruswings.render;


import com.mojang.blaze3d.matrix.MatrixStack;

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
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent (priority = EventPriority.HIGH)
	public static void onRenderPlayer(RenderPlayerEvent event) {
		PlayerRenderer renderer = event.getRenderer();
        renderer.addLayer(new WingsLayer<>(renderer));
	}
	
}

package org.mineplugin.locusazzurro.icaruswings.render;


import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WingsRenderer {
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
		PlayerEntity player = event.getPlayer();
		MatrixStack stack = event.getMatrixStack();
		PlayerRenderer renderer = event.getRenderer();
		stack.pushPose();
        renderer.addLayer(new WingsLayer<>(renderer));
        stack.popPose();
		
	}
	
}

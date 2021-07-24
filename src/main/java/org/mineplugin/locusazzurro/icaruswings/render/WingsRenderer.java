package org.mineplugin.locusazzurro.icaruswings.render;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WingsRenderer {
	
	private static boolean init = false;
	private static List<Entity> entityList = new ArrayList<>();
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onRenderPlayer(RenderPlayerEvent.Post event) {
		PlayerRenderer renderer = event.getRenderer();
		if(!init) {
		renderer.addLayer(new WingsLayer<>(renderer));
		init = true;
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onRenderEntity(RenderLivingEvent<?, ?> event) {
		LivingRenderer<? extends LivingEntity, ? extends EntityModel<?>> renderer = event.getRenderer();
		Entity entity = event.getEntity();
		if(!entityList.contains(entity)) {
			renderer.addLayer(new WingsLayer(renderer));
			entityList.add(entity);
		}
		
	}
}

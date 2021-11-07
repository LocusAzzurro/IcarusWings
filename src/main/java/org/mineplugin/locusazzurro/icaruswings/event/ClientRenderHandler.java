package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.render.ArtemisMissileRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.GoldenRamRenderer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        register(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamRenderer::new);
        register(EntityTypeRegistry.artemisMissileEntity.get(), ArtemisMissileRenderer::new);
    }

    private static void register(EntityType<? extends Entity> type, IRenderFactory renderer){
        RenderingRegistry.registerEntityRenderingHandler(type, renderer);
    }

}

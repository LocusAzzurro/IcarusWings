package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.render.*;

import java.util.Map;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        register(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamRenderer::new);
        register(EntityTypeRegistry.artemisMissileEntity.get(), ArtemisMissileRenderer::new);
        register(EntityTypeRegistry.timeBombEntity.get(), TimeBombRenderer::new);
        register(EntityTypeRegistry.spearEntity.get(), SpearRenderer::new);
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent e) {
        Map<ResourceLocation, IBakedModel> modelRegistry = e.getModelRegistry();
        ModelResourceLocation location = new ModelResourceLocation(ItemRegistry.stoneSpear.get().getRegistryName(), "inventory");
        IBakedModel existingModel = modelRegistry.get(location);
        if (existingModel == null) {
            throw new RuntimeException("Did not find original model in registry");
        } else if (existingModel instanceof SpearBakedModel) {
            throw new RuntimeException("Tried to replace model twice");
        } else {
            SpearBakedModel spearBakedModel = new SpearBakedModel(existingModel);
            e.getModelRegistry().put(location, spearBakedModel);
        }
    }

    private static void register(EntityType<? extends Entity> type, IRenderFactory renderer){
        RenderingRegistry.registerEntityRenderingHandler(type, renderer);
    }

}

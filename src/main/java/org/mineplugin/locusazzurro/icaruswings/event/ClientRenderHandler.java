package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeRiftParticleEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.render.*;

import java.util.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){

        ItemRenderer itemRenderer = e.getMinecraftSupplier().get().getItemRenderer();

        register(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamRenderer::new);
        register(EntityTypeRegistry.artemisMissileEntity.get(), ArtemisMissileRenderer::new);
        register(EntityTypeRegistry.timeBombEntity.get(), TimeBombRenderer::new);
        register(EntityTypeRegistry.timeRiftParticleEntity.get(),
                (rendererManager) -> new SpriteRenderer<TimeRiftParticleEntity>(rendererManager, itemRenderer));
        register(EntityTypeRegistry.spearEntity.get(), SpearRenderer::new);

        e.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(FluidRegistry.greekFire.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(FluidRegistry.greekFireFlowing.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(BlockRegistry.elysianGrass.get(), RenderType.cutoutMipped());
        });
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent e) {
        Map<ResourceLocation, IBakedModel> modelRegistry = e.getModelRegistry();
        ModelResourceLocation location;
        for (RegistryObject<Item> spear : SPEARS){
            location = new ModelResourceLocation(spear.get().getRegistryName(), "inventory");
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

    }

    private static void register(EntityType<? extends Entity> type, IRenderFactory renderer){
        RenderingRegistry.registerEntityRenderingHandler(type, renderer);
    }

    private static final List<RegistryObject<Item>> SPEARS = Arrays.asList(
            ItemRegistry.woodenSpear,
            ItemRegistry.stoneSpear,
            ItemRegistry.ironSpear,
            ItemRegistry.steelSpear,
            ItemRegistry.goldenSpear,
            ItemRegistry.diamondSpear,
            ItemRegistry.netheriteSpear,
            ItemRegistry.synapseAlloySpear
    );

}

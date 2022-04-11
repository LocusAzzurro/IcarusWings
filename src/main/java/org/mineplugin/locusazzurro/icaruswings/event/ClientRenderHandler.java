package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.render.models.SpearBakedModel;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.ArtemisMissileRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.GoldenRamRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.SpearRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.TimeBombRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        register(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamRenderer::new);
        register(EntityTypeRegistry.artemisMissileEntity.get(), ArtemisMissileRenderer::new);
        register(EntityTypeRegistry.timeBombEntity.get(), TimeBombRenderer::new);
        register(EntityTypeRegistry.timeRiftParticleEntity.get(), ThrownItemRenderer::new);
        register(EntityTypeRegistry.spearEntity.get(), SpearRenderer::new);

        e.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(FluidRegistry.greekFire.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidRegistry.greekFireFlowing.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.elysianGrass.get(), RenderType.cutoutMipped());
        });
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent e) {
        Map<ResourceLocation, BakedModel> modelRegistry = e.getModelRegistry();
        ModelResourceLocation location;
        for (RegistryObject<Item> spear : SPEARS){
            location = new ModelResourceLocation(spear.get().getRegistryName(), "inventory");
            BakedModel existingModel = modelRegistry.get(location);
            if (existingModel == null) {
                throw new RuntimeException("Did not find original model in registry");
            }
            else if (existingModel instanceof SpearBakedModel) {
                throw new RuntimeException("Tried to replace model twice");
            }
            else {
                SpearBakedModel spearBakedModel = new SpearBakedModel(existingModel);
                e.getModelRegistry().put(location, spearBakedModel);
            }
        }

    }

    private static <ENTITY extends Entity> void register(EntityType<ENTITY> type, EntityRendererProvider<ENTITY> renderer){
        EntityRenderers.register(type, renderer);
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

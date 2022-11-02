package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent.BakingCompleted;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.particles.*;
import org.mineplugin.locusazzurro.icaruswings.registry.*;
import org.mineplugin.locusazzurro.icaruswings.render.layers.WingsLayer;
import org.mineplugin.locusazzurro.icaruswings.render.models.SpearBakedModel;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.ArtemisMissileRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.GoldenRamRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.SpearRenderer;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.TimeBombRenderer;

import java.util.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientRenderEventHandler{

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){

        registerEntityRenderer(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.artemisMissileEntity.get(), ArtemisMissileRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.timeBombEntity.get(), TimeBombRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.timeRiftParticleEntity.get(), ThrownItemRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.kayrosBlastEntity.get(), ThrownItemRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.spearEntity.get(), SpearRenderer::new);

        e.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(FluidRegistry.greekFire.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidRegistry.greekFireFlowing.get(), RenderType.translucent());
        });
    }

    @SubscribeEvent
    public static void onModelBaked(BakingCompleted e) {
        Map<ResourceLocation, BakedModel> modelRegistry = e.getModels();
        ModelResourceLocation location;
        for (RegistryObject<Item> spear : SPEARS){
            var spearResource = ForgeRegistries.ITEMS.getKey(spear.get());
            location = new ModelResourceLocation(spearResource, "inventory");
            BakedModel existingModel = modelRegistry.get(location);
            if (existingModel == null) {
                throw new RuntimeException("Did not find original model in registry");
            }
            else if (existingModel instanceof SpearBakedModel) {
                throw new RuntimeException("Tried to replace model twice");
            }
            else {
                SpearBakedModel spearBakedModel = new SpearBakedModel(existingModel);
                e.getModels().put(location, spearBakedModel);
            }
        }
    }

    /**
     * @author DustW
     */
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterRenderers event) {
        Map<ModelLayerLocation, LayerDefinition> layers = ModelLayerRegistry.createLayerDefinitions();
        layers.forEach((location, definition) -> ForgeHooksClient.registerLayerDefinition(location, () -> definition));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SubscribeEvent
    public static void addWingsLayer(EntityRenderersEvent.AddLayers event){

        event.getSkins().forEach(skin -> {
            var renderer = event.getSkin(skin);
            if (renderer != null)
                renderer.addLayer(new WingsLayer<>((RenderLayerParent) renderer, Minecraft.getInstance().getEntityModels()));
        });

        Minecraft.getInstance().getEntityRenderDispatcher().renderers.forEach((entityType, entityRenderer) -> {
            if (entityRenderer instanceof LivingEntityRenderer<?,?> livingEntityRenderer && !(livingEntityRenderer instanceof PlayerRenderer)) {
                livingEntityRenderer.addLayer(new WingsLayer<>((RenderLayerParent) livingEntityRenderer, Minecraft.getInstance().getEntityModels()));
            }
        });
    }

    @SubscribeEvent
    public static void onParticleFactoryRegister(RegisterParticleProvidersEvent event){
        ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
        particleEngine.register(ParticleRegistry.nullity.get(), NullityParticle.Factory::new);
        particleEngine.register(ParticleRegistry.plasmaTrail.get(), PlasmaTrailParticle.Factory::new);
        particleEngine.register(ParticleRegistry.electronicBit.get(), ElectronicBitParticle.Factory::new);
        particleEngine.register(ParticleRegistry.goldenSparkleBase.get(), GoldenSparkleParticle.BaseFactory::new);
        particleEngine.register(ParticleRegistry.goldenSparkle.get(), GoldenSparkleParticle.AdvFactory::new);
        particleEngine.register(ParticleRegistry.timeRiftExplosion.get(), TimeRiftExplosionParticle.Factory::new);
    }

    private static <ENTITY extends Entity> void registerEntityRenderer(EntityType<ENTITY> type, EntityRendererProvider<ENTITY> renderer){
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

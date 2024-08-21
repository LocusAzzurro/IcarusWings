package org.mineplugin.locusazzurro.icaruswings.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.mineplugin.locusazzurro.icaruswings.client.particle.*;
import org.mineplugin.locusazzurro.icaruswings.registry.*;
import org.mineplugin.locusazzurro.icaruswings.client.render.layers.WingsLayer;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.SpearBakedModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.ArtemisMissileRenderer;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.GoldenRamRenderer;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.SpearRenderer;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.TimeBombRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientRenderEventHandler{

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){

        registerEntityRenderer(EntityTypeRegistry.GOLDEN_RAM.get(), GoldenRamRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.ARTEMIS_MISSILE.get(), ArtemisMissileRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.TIME_BOMB.get(), TimeBombRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.TIME_RIFT_PARTICLE.get(), ThrownItemRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.KAYROS_BLAST.get(), ThrownItemRenderer::new);
        registerEntityRenderer(EntityTypeRegistry.SPEAR.get(), SpearRenderer::new);

        e.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(FluidRegistry.GREEK_FIRE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidRegistry.GREEK_FIRE_FLOWING.get(), RenderType.translucent());
        });
    }

    @SubscribeEvent
    public static void onModelBaked(ModelEvent.ModifyBakingResult e) {
        Map<ResourceLocation, BakedModel> modelRegistry = e.getModels();
        ModelResourceLocation location;
        for (Supplier<Item> spear : SPEARS){
            var spearResource = BuiltInRegistries.ITEM.getKey(spear.get());
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
        layers.forEach((location, definition) -> ClientHooks.registerLayerDefinition(location, () -> definition));
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @SubscribeEvent
    public static void addLivingWingsLayer(EntityRenderersEvent.AddLayers event){

        Minecraft.getInstance().getEntityRenderDispatcher().renderers.forEach((entityType, entityRenderer) -> {
            if (entityRenderer instanceof LivingEntityRenderer<?,?> livingEntityRenderer && !(livingEntityRenderer instanceof PlayerRenderer)) {
                livingEntityRenderer.addLayer(new WingsLayer<>((RenderLayerParent) livingEntityRenderer, Minecraft.getInstance().getEntityModels()));
            }
        });
    }

    @SubscribeEvent
    public static void onParticleFactoryRegister(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ParticleRegistry.NULLITY.get(), NullityParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.PLASMA_TRAIL.get(), PlasmaTrailParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.ELECTRONIC_BIT.get(), ElectronicBitParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), GoldenSparkleParticle.BaseFactory::new);
        event.registerSpriteSet(ParticleRegistry.GOLDEN_SPARKLE.get(), GoldenSparkleParticle.AdvFactory::new);
        event.registerSpriteSet(ParticleRegistry.TIME_RIFT_EXPLOSION.get(), TimeRiftExplosionParticle.Factory::new);
    }

    private static <E extends Entity> void registerEntityRenderer(EntityType<E> type, EntityRendererProvider<E> renderer){
        EntityRenderers.register(type, renderer);
    }

    private static final List<Supplier<Item>> SPEARS = Arrays.asList(
            ItemRegistry.WOODEN_SPEAR,
            ItemRegistry.STONE_SPEAR,
            ItemRegistry.IRON_SPEAR,
            ItemRegistry.STEEL_SPEAR,
            ItemRegistry.GOLDEN_SPEAR,
            ItemRegistry.DIAMOND_SPEAR,
            ItemRegistry.NETHERITE_SPEAR,
            ItemRegistry.SYNAPSE_ALLOY_SPEAR
    );
}

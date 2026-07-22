package org.mineplugin.locusazzurro.icaruswings.client.event;

import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.PlayerModelType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.fluid.FluidTintSources;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.particle.ElectronicBitParticle;
import org.mineplugin.locusazzurro.icaruswings.client.particle.GoldenSparkleParticle;
import org.mineplugin.locusazzurro.icaruswings.client.particle.NullityParticle;
import org.mineplugin.locusazzurro.icaruswings.client.particle.PlasmaTrailParticle;
import org.mineplugin.locusazzurro.icaruswings.client.render.layers.MelonHeadLayer;
import org.mineplugin.locusazzurro.icaruswings.client.render.layers.WingsLayer;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.properties.SpearThrowingProperty;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.*;
import org.mineplugin.locusazzurro.icaruswings.common.block.GreekFireFluid;
import org.mineplugin.locusazzurro.icaruswings.common.block.GreekFireFluidType;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

@EventBusSubscriber(modid = IcarusWings.MOD_ID, value = Dist.CLIENT)
@SuppressWarnings("unused")
public class ModClientRenderEventHandler {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        registerEntityRenderer(event, EntityTypeRegistry.GOLDEN_RAM.get(), GoldenRamRenderer::new);
        registerEntityRenderer(event, EntityTypeRegistry.ARTEMIS_MISSILE.get(), ArtemisMissileRenderer::new);
        registerEntityRenderer(event, EntityTypeRegistry.TIME_BOMB.get(), TimeBombRenderer::new);
        registerEntityRenderer(event, EntityTypeRegistry.TIME_RIFT_PARTICLE.get(), ThrownItemRenderer::new);
        registerEntityRenderer(event, EntityTypeRegistry.KAYROS_BLAST.get(), ThrownItemRenderer::new);
        registerEntityRenderer(event, EntityTypeRegistry.SPEAR.get(), SpearRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelLayerRegistry.createLayerDefinitions().forEach((layer, factory) -> event.registerLayerDefinition(layer, () -> factory));
    }

    @SubscribeEvent
    public static void addWingsLayer(EntityRenderersEvent.AddLayers event) {
        for (PlayerModelType skin : event.getSkins()) {
            var playerRenderer = event.getPlayerRenderer(skin);
            if (playerRenderer != null) {
                playerRenderer.addLayer(new WingsLayer<>(playerRenderer, event.getEntityModels()));
                playerRenderer.addLayer(new MelonHeadLayer<>(playerRenderer, event.getEntityModels()));
            }
            var mannequinRenderer = event.getMannequinRenderer(skin);
            if (mannequinRenderer != null) {
                mannequinRenderer.addLayer(new WingsLayer<>(mannequinRenderer, event.getEntityModels()));
                mannequinRenderer.addLayer(new MelonHeadLayer<>(mannequinRenderer, event.getEntityModels()));
            }
        }
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(GreekFireFluidType.createClientExtensions(), FluidRegistry.GREEK_FIRE_FLUID_TYPE.get());
    }

    @SubscribeEvent
    public static void registerFluidModels(RegisterFluidModelsEvent event) {
        FluidModel.Unbaked greekFireModel = new FluidModel.Unbaked(
                new Material(GreekFireFluid.GREEK_FIRE_STILL, true),
                new Material(GreekFireFluid.GREEK_FIRE_FLOWING),
                new Material(GreekFireFluidType.FLUID_OVERLAY_TEXTURE),
                FluidTintSources.constant(GreekFireFluidType.TINT_COLOR)
        );
        event.register(greekFireModel, FluidRegistry.GREEK_FIRE, FluidRegistry.GREEK_FIRE_FLOWING);
    }

    @SubscribeEvent
    public static void registerSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(
                Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "spear_first_person"),
                SpearItemStackTileEntityRenderer.FirstPersonUnbaked.MAP_CODEC
        );
        event.register(
                Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "spear_third_person"),
                SpearItemStackTileEntityRenderer.ThirdPersonUnbaked.MAP_CODEC
        );
        event.register(
                Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "spear_third_person_throwing"),
                SpearItemStackTileEntityRenderer.ThirdPersonThrowingUnbaked.MAP_CODEC
        );
    }

    @SubscribeEvent
    public static void registerConditionalItemModelProperties(RegisterConditionalItemModelPropertyEvent event) {
        event.register(
                Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "spear_throwing"),
                SpearThrowingProperty.MAP_CODEC
        );
    }

    @SubscribeEvent
    public static void onParticleFactoryRegister(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.NULLITY.get(), NullityParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.PLASMA_TRAIL.get(), PlasmaTrailParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.ELECTRONIC_BIT.get(), ElectronicBitParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), GoldenSparkleParticle.BaseFactory::new);
        event.registerSpriteSet(ParticleRegistry.GOLDEN_SPARKLE.get(), GoldenSparkleParticle.AdvFactory::new);
        //event.registerSpriteSet(ParticleRegistry.TIME_RIFT_EXPLOSION.get(), TimeRiftExplosionParticle.Factory::new);
    }

    private static <E extends Entity> void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event, EntityType<E> type, net.minecraft.client.renderer.entity.EntityRendererProvider<E> renderer) {
        event.registerEntityRenderer(type, renderer);
    }
}

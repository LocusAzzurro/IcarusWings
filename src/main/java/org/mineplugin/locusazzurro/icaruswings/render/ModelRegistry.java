package org.mineplugin.locusazzurro.icaruswings.render;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.render.models.ArtemisMissileModel;
import org.mineplugin.locusazzurro.icaruswings.render.models.GoldenRamModel;
import org.mineplugin.locusazzurro.icaruswings.render.models.SpearModel;
import org.mineplugin.locusazzurro.icaruswings.render.models.TimeBombModel;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelRegistry {
    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
        ForgeHooksClient.registerLayerDefinition(ArtemisMissileModel.LAYER_LOCATION, ArtemisMissileModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(SpearModel.LAYER_LOCATION, SpearModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(TimeBombModel.LAYER_LOCATION, TimeBombModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(GoldenRamModel.LAYER_LOCATION, GoldenRamModel::createBodyLayer);
    }
}

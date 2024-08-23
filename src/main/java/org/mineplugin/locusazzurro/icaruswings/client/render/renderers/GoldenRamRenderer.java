package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.layers.GoldenRamFleeceLayer;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.GoldenRamModel;
import org.mineplugin.locusazzurro.icaruswings.common.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GoldenRamRenderer extends MobRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>>{
    private static final ResourceLocation GOLDEN_RAM_TEXTURE = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/golden_ram.png");

    public GoldenRamRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldenRamModel<>(context.bakeLayer(ModelLayerRegistry.GOLDEN_RAM)), 0.7F);
        this.addLayer(new GoldenRamFleeceLayer(this, context.getModelSet()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public ResourceLocation getTextureLocation(GoldenRamEntity entity) {
        return GOLDEN_RAM_TEXTURE;
    }
}

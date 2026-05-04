package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.layers.GoldenRamFleeceLayer;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.GoldenRamModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.state.GoldenRamRenderState;
import org.mineplugin.locusazzurro.icaruswings.common.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
public class GoldenRamRenderer extends MobRenderer<GoldenRamEntity, GoldenRamRenderState, GoldenRamModel> {
    private static final Identifier GOLDEN_RAM_TEXTURE = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/golden_ram.png");

    public GoldenRamRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldenRamModel(context.bakeLayer(ModelLayerRegistry.GOLDEN_RAM)), 0.7F);
        this.addLayer(new GoldenRamFleeceLayer(this, context.getModelSet()));
    }

    public Identifier getTextureLocation(GoldenRamRenderState state) {
        return GOLDEN_RAM_TEXTURE;
    }

    @Override
    public GoldenRamRenderState createRenderState() {
        return new GoldenRamRenderState();
    }

    @Override
    public void extractRenderState(GoldenRamEntity entity, GoldenRamRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.headEatAngleScale = entity.getHeadEatAngleScale(partialTicks);
        state.headEatPositionScale = entity.getHeadEatPositionScale(partialTicks);
        state.isSheared = entity.isSheared();
    }
}


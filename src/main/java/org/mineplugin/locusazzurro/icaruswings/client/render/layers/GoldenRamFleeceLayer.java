package org.mineplugin.locusazzurro.icaruswings.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.GoldenRamFleeceModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.GoldenRamModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.state.GoldenRamRenderState;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
public class GoldenRamFleeceLayer extends RenderLayer<GoldenRamRenderState, GoldenRamModel> {

    private static final Identifier FLEECE_TEXTURE = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final GoldenRamFleeceModel model;

    public GoldenRamFleeceLayer(RenderLayerParent<GoldenRamRenderState, GoldenRamModel> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new GoldenRamFleeceModel(set.bakeLayer(ModelLayerRegistry.GOLDEN_RAM_FLEECE));
    }

    @Override
    public void submit(PoseStack stack, SubmitNodeCollector submitNodeCollector, int packedLightIn, GoldenRamRenderState state, float yRot, float xRot) {
        if (!state.isSheared) {
            coloredCutoutModelCopyLayerRender(this.model, FLEECE_TEXTURE, stack, submitNodeCollector, packedLightIn, state, 0xFFFFFFFF, 0);
        }
    }
}


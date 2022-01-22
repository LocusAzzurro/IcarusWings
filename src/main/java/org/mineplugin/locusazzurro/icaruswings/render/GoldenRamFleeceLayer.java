package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

public class GoldenRamFleeceLayer extends LayerRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> {

    private static final ResourceLocation FLEECE_TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final GoldenRamFleeceModel<GoldenRamEntity> model = new GoldenRamFleeceModel();

    public GoldenRamFleeceLayer(IEntityRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> renderer) {
        super(renderer);
    }

    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int p_225628_3_, GoldenRamEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if (!entity.isSheared() &&!entity.isInvisible()) {
            //IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(FLEECE_TEXTURE));
            //model.renderToBuffer(stack, vertexBuilder, p_225628_3_, LivingRenderer.getOverlayCoords(entity, 0.0F), 1,1,1,1);
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLEECE_TEXTURE, stack, buffer, p_225628_3_, entity, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, p_225628_7_, 1,1,1);
        }
    }
}

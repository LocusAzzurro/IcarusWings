package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;

public class TimeBombRenderer extends EntityRenderer<TimeBombEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/time_bomb");
    private final TimeBombModel model = new TimeBombModel();

    public TimeBombRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }


    public void render(TimeBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(TimeBombEntity p_110775_1_) {
        return TEXTURE;
    }
}

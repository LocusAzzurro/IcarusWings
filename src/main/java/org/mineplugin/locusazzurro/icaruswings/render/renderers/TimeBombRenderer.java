package org.mineplugin.locusazzurro.icaruswings.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.render.models.TimeBombModel;

@OnlyIn(Dist.CLIENT)
public class TimeBombRenderer extends EntityRenderer<TimeBombEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/time_bomb.png");
    private final TimeBombModel<TimeBombEntity> model;

    public TimeBombRenderer(Context context) {
        super(context);
        model = new TimeBombModel<>(context.bakeLayer(TimeBombModel.LAYER_LOCATION));
    }


    @Override
    public void render(TimeBombEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn){
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.1f,0.1f, 0.1f);
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(TimeBombEntity p_110775_1_) {
        return TEXTURE;
    }
}

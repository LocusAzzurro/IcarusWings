package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.TimeBombModel;
import org.mineplugin.locusazzurro.icaruswings.common.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;

@OnlyIn(Dist.CLIENT)
public class TimeBombRenderer extends EntityRenderer<TimeBombEntity> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/time_bomb.png");
    private final TimeBombModel<TimeBombEntity> model;

    public TimeBombRenderer(Context context) {
        super(context);
        model = new TimeBombModel<>(context.bakeLayer(ModelLayerRegistry.TIME_BOMB));
    }


    @Override
    public void render(TimeBombEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn){
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.1f, 0.1f, 0.1f);
        VertexConsumer vertexBuilder = bufferIn.getBuffer(model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStackIn, vertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0xff_ff_ff_ff);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(TimeBombEntity entity) {
        return TEXTURE;
    }
}

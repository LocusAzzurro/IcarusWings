package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.ArtemisMissileModel;

@OnlyIn(Dist.CLIENT)
public class ArtemisMissileRenderer extends EntityRenderer<ArtemisMissileEntity> {

    private static final ResourceLocation MISSILE = ResourceLocation.fromNamespaceAndPath(ModData.MOD_ID, "textures/entity/artemis_missile.png");
    private final ArtemisMissileModel model;

    public ArtemisMissileRenderer(Context context) {
        super(context);
        model = new ArtemisMissileModel(context.bakeLayer(ModelLayerRegistry.ARTEMIS_MISSILE));
    }

    @Override
    public ResourceLocation getTextureLocation(ArtemisMissileEntity p_110775_1_) {
        return MISSILE;
    }

    @Override
    public void render(ArtemisMissileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn){
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
        poseStack.pushPose();
        poseStack.scale(0.15f,0.15f, 0.15f);
        poseStack.translate(0f, -0.25f, 0f);
        poseStack.mulPose(Axis.YN.rotationDegrees(entityYaw));
        poseStack.mulPose(Axis.XN.rotationDegrees(entityIn.getXRot()));
        VertexConsumer vertexConsumer = bufferIn.getBuffer(model.renderType(MISSILE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 0xff_ff_ff_ff);
        poseStack.popPose();
    }
}

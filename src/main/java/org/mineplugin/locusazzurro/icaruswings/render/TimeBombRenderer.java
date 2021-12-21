package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;

@OnlyIn(Dist.CLIENT)
public class TimeBombRenderer extends EntityRenderer<TimeBombEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/time_bomb.png");
    private final TimeBombModel model = new TimeBombModel();

    public TimeBombRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }


    public void render(TimeBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        matrixStackIn.scale(0.1f,0.1f, 0.1f);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(TimeBombEntity p_110775_1_) {
        return TEXTURE;
    }
}

package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Tier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.items.SpearItem;
import org.mineplugin.locusazzurro.icaruswings.render.models.SpearModel;

@OnlyIn(Dist.CLIENT)
public class SpearRenderer extends EntityRenderer<SpearEntity> {

    private final SpearModel model = new SpearModel();

    public SpearRenderer(Context p_i48828_1_) {
        super(p_i48828_1_);
    }

    @Override
    public void render(SpearEntity spearEntity, float p_225623_2_, float p_225623_3_, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
        stack.pushPose();
        stack.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(Mth.lerp(p_225623_3_, spearEntity.yRotO, spearEntity.getYRot()) - 90.0F));
        stack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(p_225623_3_, spearEntity.xRotO, spearEntity.getXRot()) + 90.0F));
        VertexConsumer ivertexbuilder = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(spearEntity)), false, spearEntity.isFoil());
        this.model.renderToBuffer(stack, ivertexbuilder, p_225623_6_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(spearEntity, p_225623_2_, p_225623_3_, stack, buffer, p_225623_6_);
    }

    public ResourceLocation getTextureLocation(SpearEntity entity) {
        Tier tier = ((SpearItem)entity.getSpearItemData().getItem()).getTier();
        return SpearModel.getTexture(tier);
    }

}

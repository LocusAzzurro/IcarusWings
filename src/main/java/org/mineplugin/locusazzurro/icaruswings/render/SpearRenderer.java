package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.IItemTier;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.items.SpearItem;

@OnlyIn(Dist.CLIENT)
public class SpearRenderer extends EntityRenderer<SpearEntity> {

    private final SpearModel model = new SpearModel();

    public SpearRenderer(EntityRendererManager p_i48828_1_) {
        super(p_i48828_1_);
    }

    public void render(SpearEntity spearEntity, float p_225623_2_, float p_225623_3_, MatrixStack stack, IRenderTypeBuffer buffer, int p_225623_6_) {
        stack.pushPose();
        stack.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(p_225623_3_, spearEntity.yRotO, spearEntity.yRot) - 90.0F));
        stack.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(p_225623_3_, spearEntity.xRotO, spearEntity.xRot) + 90.0F));
        IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(spearEntity)), false, spearEntity.isFoil());
        this.model.renderToBuffer(stack, ivertexbuilder, p_225623_6_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(spearEntity, p_225623_2_, p_225623_3_, stack, buffer, p_225623_6_);
    }

    public ResourceLocation getTextureLocation(SpearEntity entity) {
        IItemTier tier = ((SpearItem)entity.getSpearItemData().getItem()).getTier();
        return SpearModel.getTexture(tier);
    }

}

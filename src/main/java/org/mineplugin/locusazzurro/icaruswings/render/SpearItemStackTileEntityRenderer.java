package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.model.TridentModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.items.SpearItem;

@OnlyIn(Dist.CLIENT)
public class SpearItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {

    private final SpearModel model = new SpearModel();

    @Override
    public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay){
        if (stack.getItem() instanceof SpearItem) {
            matrixStack.pushPose();
            //todo process texture diff
            IVertexBuilder vertexBuilder = ItemRenderer.getFoilBuffer(buffer, RenderType.entitySolid(SpearModel.SPEAR_TEXTURE), false, stack.hasFoil());
            matrixStack.scale(1.0F, -1.0F, 1.0F);
            matrixStack.translate(1.5F, -0.2F, 1.5F);
            model.renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entitySolid(SpearModel.SPEAR_TEXTURE)), combinedLight, combinedOverlay, 1,1,1,1);
            matrixStack.popPose();
        }
    }



}

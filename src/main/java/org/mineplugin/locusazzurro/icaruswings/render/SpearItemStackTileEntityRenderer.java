package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.TridentModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.items.SpearItem;

public class SpearItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {

    private SpearModel model = new SpearModel();

    @Override
    public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay){
        if (stack.getItem() instanceof SpearItem) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getModel(stack, null, null);
            matrixStack.pushPose();
            matrixStack.scale(1.0F, -1.0F, -1.0F);
            itemRenderer.render(stack, transformType, false, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel.getBakedModel());
            matrixStack.popPose();
        }
    }



}

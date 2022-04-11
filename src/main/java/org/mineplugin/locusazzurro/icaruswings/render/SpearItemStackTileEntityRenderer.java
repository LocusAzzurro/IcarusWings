package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.items.SpearItem;
import org.mineplugin.locusazzurro.icaruswings.render.models.SpearModel;
import org.mineplugin.locusazzurro.icaruswings.render.renderers.SpearRenderer;

@OnlyIn(Dist.CLIENT)
public class SpearItemStackTileEntityRenderer extends BlockEntityWithoutLevelRenderer {

    private final SpearModel model;

    public SpearItemStackTileEntityRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        model = new SpearModel(pEntityModelSet.bakeLayer(SpearModel.LAYER_LOCATION));
    }

    public SpearItemStackTileEntityRenderer(){
        this(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay){
        if (stack.getItem() instanceof SpearItem) {
            boolean throwing = false;
            if (stack.getOrCreateTag().contains("Throwing")){
                throwing = stack.getTag().getBoolean("Throwing");
            }
            matrixStack.pushPose();
            VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, RenderType.entitySolid(SpearRenderer.getTexture(((SpearItem) stack.getItem()).getTier())), false, stack.hasFoil());
            if (transformType.firstPerson()) {
                matrixStack.translate(0.5F, 1.4F, 0.6F);
                matrixStack.scale(1.0F, -1.0F, -1.0F);
            }
            else{
                matrixStack.translate(0.5F, 1.5F, 0.6F);
                matrixStack.scale(1.0F, -1.0F, -1.0F);
                if (throwing){
                    matrixStack.scale(1.0F, -1.0F, -1.0F);
                    matrixStack.translate(0.0F, -2.0F, 0.0F);
                }
            }
            model.renderToBuffer(matrixStack, vertexBuilder, combinedLight, combinedOverlay, 1,1,1,1);
            matrixStack.popPose();
        }
    }



}

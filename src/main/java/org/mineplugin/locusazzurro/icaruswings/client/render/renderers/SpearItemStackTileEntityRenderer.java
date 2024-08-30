package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.SpearModel;
import org.mineplugin.locusazzurro.icaruswings.common.item.SpearItem;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

@OnlyIn(Dist.CLIENT)
public class SpearItemStackTileEntityRenderer extends BlockEntityWithoutLevelRenderer {

    private final SpearModel model;

    public SpearItemStackTileEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        model = new SpearModel(entityModelSet.bakeLayer(SpearModel.LAYER_LOCATION));
    }

    public SpearItemStackTileEntityRenderer(){
        this(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay){
        if (stack.getItem() instanceof SpearItem) {
            boolean throwing = Boolean.TRUE.equals(stack.get(DataComponentRegistry.THROWING));
            matrixStack.pushPose();
            VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, RenderType.entitySolid(SpearRenderer.getTexture(((SpearItem) stack.getItem()).getTier())), false, stack.hasFoil());
            boolean isFirstPerson = transformType.firstPerson();
            matrixStack.translate(0.5F, isFirstPerson ? 1.4F : 1.5F, 0.6F);
            matrixStack.scale(1.0F, -1.0F, -1.0F);
            if (!isFirstPerson && throwing){
                matrixStack.scale(1.0F, -1.0F, -1.0F);
                matrixStack.translate(0.0F, -2.0F, 0.0F);
            }
            model.renderToBuffer(matrixStack, vertexBuilder, combinedLight, combinedOverlay, 0xff_ff_ff_ff);
            matrixStack.popPose();
        }
    }



}

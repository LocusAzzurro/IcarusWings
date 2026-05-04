package org.mineplugin.locusazzurro.icaruswings.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.common.item.MelonItem;

public class MelonHeadLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {

    private final HumanoidModel<S> playerModel;
    private final ItemModelResolver itemModelResolver;
    private final ItemStackRenderState itemRenderState = new ItemStackRenderState();

    public MelonHeadLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.playerModel = new HumanoidModel<>(modelSet.bakeLayer(ModelLayers.PLAYER));
        this.itemModelResolver = Minecraft.getInstance().getItemModelResolver();
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        ItemStack headItem = state.headEquipment;
        if (!(headItem.getItem() instanceof MelonItem)) {
            return;
        }

        this.itemModelResolver.updateForTopItem(this.itemRenderState, headItem, ItemDisplayContext.HEAD, null, null, 0);

        poseStack.pushPose();
        this.playerModel.head.translateAndRotate(poseStack);
        this.itemRenderState.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
    }
}

package org.mineplugin.locusazzurro.icaruswings.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsTranslucent;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.WingsModel;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.AbstractWings;

public class WingsLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    private final WingsModel model;
    private final WingsModel babyModel;

    public WingsLayer(RenderLayerParent<S, M> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new WingsModel(modelSet.bakeLayer(ModelLayers.ELYTRA));
        this.babyModel = new WingsModel(modelSet.bakeLayer(ModelLayers.ELYTRA_BABY));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        ItemStack chestItem = state.chestEquipment;
        if (!(chestItem.getItem() instanceof AbstractWings wingsItem)) {
            return;
        }

        boolean expanded = state.isFallFlying && chestItem.getItem() instanceof IWingsExpandable;
        Identifier texture = expanded ? wingsItem.getType().getTextureReversed() : wingsItem.getType().getTexture();
        boolean translucent = chestItem.getItem() instanceof IWingsTranslucent;
        RenderType renderType = translucent ? RenderTypes.entityTranslucent(texture) : RenderTypes.armorCutoutNoCull(texture);
        WingsModel wingsModel = state.isBaby ? this.babyModel : this.model;

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.125F);
        if (expanded && chestItem.getItem() instanceof IWingsExpandable expandable) {
            float factor = (float) expandable.getExpansionFactor();
            poseStack.scale(factor, factor, 1.0F);
        }
        wingsModel.setupAnim(state, false);
        submitNodeCollector.submitModel(wingsModel, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        poseStack.popPose();
    }
}

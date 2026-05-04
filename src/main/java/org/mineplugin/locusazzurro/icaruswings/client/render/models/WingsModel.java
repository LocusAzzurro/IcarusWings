package org.mineplugin.locusazzurro.icaruswings.client.render.models;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class WingsModel extends ElytraModel {

    public WingsModel(ModelPart root) {
        super(root);
    }

    public void setupAnim(HumanoidRenderState state, boolean floating) {
        float originalX = state.elytraRotX;
        float originalY = state.elytraRotY;
        float originalZ = state.elytraRotZ;
        if (floating) {
            state.elytraRotX = 0.3491F;
            state.elytraRotY = 0.0F;
            state.elytraRotZ = (float) (-Math.PI / 1.95F);
        }
        super.setupAnim(state);
        state.elytraRotX = originalX;
        state.elytraRotY = originalY;
        state.elytraRotZ = originalZ;
    }
}

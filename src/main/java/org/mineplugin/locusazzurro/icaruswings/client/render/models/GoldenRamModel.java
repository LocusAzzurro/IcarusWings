package org.mineplugin.locusazzurro.icaruswings.client.render.models;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.mineplugin.locusazzurro.icaruswings.client.render.state.GoldenRamRenderState;

public class GoldenRamModel extends QuadrupedModel<GoldenRamRenderState> {

    public GoldenRamModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(12, false, true, CubeDeformation.NONE);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(GoldenRamRenderState state) {
        super.setupAnim(state);
        this.head.y = this.head.y + state.headEatPositionScale * 9.0F * state.ageScale;
        this.head.xRot = state.headEatAngleScale;
    }
}

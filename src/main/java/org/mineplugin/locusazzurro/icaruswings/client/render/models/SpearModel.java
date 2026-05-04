package org.mineplugin.locusazzurro.icaruswings.client.render.models;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;

public class SpearModel extends EntityModel<EntityRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = ModelLayerRegistry.SPEAR;
	private final ModelPart bone;

	public SpearModel(ModelPart root) {
		super(root);
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(1, 2).addBox(-0.5F, -14.0F, -0.5F, 1.0F, 26.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 2).addBox(-1.5F, -17.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 7).addBox(-0.5F, -19.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 32);
	}

}

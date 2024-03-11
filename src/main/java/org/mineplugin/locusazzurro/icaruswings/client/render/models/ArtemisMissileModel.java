package org.mineplugin.locusazzurro.icaruswings.client.render.models;// Made with Blockbench 4.2.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ArtemisMissileModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart missile;

	public ArtemisMissileModel(ModelPart root) {
		this.missile = root.getChild("missile");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition missile = partdefinition.addOrReplaceChild("missile", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -2.0F, 11.0F, 6.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(63, 19).addBox(-2.0F, -1.0F, 18.0F, 4.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -9.0F));

		PartDefinition bulgemiddle_r1 = missile.addOrReplaceChild("bulgemiddle_r1", CubeListBuilder.create().texOffs(38, 38).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 9.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition payload_r1 = missile.addOrReplaceChild("payload_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -8.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition head_r1 = missile.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-6.9792F, -2.0308F, -7.2936F, 14.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0436F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		missile.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
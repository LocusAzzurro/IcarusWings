package org.mineplugin.locusazzurro.icaruswings.render;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WingsModel<T extends LivingEntity> extends ElytraModel<T> {
	private final ModelRenderer rightWing;
	private final ModelRenderer leftWing = new ModelRenderer(this, 22, 0);

	public WingsModel() {
		this.leftWing.addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, 1.0F);
		this.rightWing = new ModelRenderer(this, 22, 0);
		this.rightWing.mirror = true;
		this.rightWing.addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, 1.0F);
	}

	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of();
	}

	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(this.leftWing, this.rightWing);
	}

	public void setupAnim(T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 0.2617994F;
		float f1 = -0.2617994F;
		float f2 = 0.0F;
		float f3 = 0.0F;
		if (entityLivingBaseIn.isFallFlying()) {
			float f4 = 1.0F;
			Vector3d vector3d = entityLivingBaseIn.getDeltaMovement();
			if (vector3d.y < 0.0D) {
				Vector3d vector3d1 = vector3d.normalize();
				f4 = 1.0F - (float) Math.pow(-vector3d1.y, 1.5D);
			}

			f = f4 * 0.34906584F + (1.0F - f4) * f;
			f1 = f4 * (-(float) Math.PI / 2F) + (1.0F - f4) * f1;
		} else if (entityLivingBaseIn.isCrouching()) {
			f = 0.6981317F;
			f1 = (-(float) Math.PI / 4F);
			f2 = 3.0F;
			f3 = 0.08726646F;
		}

		this.leftWing.x = 5.0F;
		this.leftWing.y = f2;
		if (entityLivingBaseIn instanceof AbstractClientPlayerEntity) {
			AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityLivingBaseIn;
			abstractclientplayerentity.elytraRotX = (float) ((double) abstractclientplayerentity.elytraRotX
					+ (double) (f - abstractclientplayerentity.elytraRotX) * 0.1D);
			abstractclientplayerentity.elytraRotY = (float) ((double) abstractclientplayerentity.elytraRotY
					+ (double) (f3 - abstractclientplayerentity.elytraRotY) * 0.1D);
			abstractclientplayerentity.elytraRotZ = (float) ((double) abstractclientplayerentity.elytraRotZ
					+ (double) (f1 - abstractclientplayerentity.elytraRotZ) * 0.1D);
			this.leftWing.xRot = abstractclientplayerentity.elytraRotX;
			this.leftWing.yRot = abstractclientplayerentity.elytraRotY;
			this.leftWing.zRot = abstractclientplayerentity.elytraRotZ;
		} else {
			this.leftWing.xRot = f;
			this.leftWing.zRot = f1;
			this.leftWing.yRot = f3;
		}
		this.rightWing.x = -this.leftWing.x;
		this.rightWing.yRot = -this.leftWing.yRot;
		this.rightWing.y = this.leftWing.y;
		this.rightWing.xRot = this.leftWing.xRot;
		this.rightWing.zRot = -this.leftWing.zRot;
	}
}

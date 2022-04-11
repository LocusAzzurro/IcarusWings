package org.mineplugin.locusazzurro.icaruswings.render.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WingsModel<T extends LivingEntity> extends ElytraModel<T> {
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public WingsModel(ModelPart pRoot) {
		super(pRoot);
		this.leftWing = pRoot.getChild("left_wing");
		this.rightWing = pRoot.getChild("right_wing");
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.leftWing, this.rightWing);
	}

	@Override
	public void setupAnim(T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 0.2617994F;
		float f1 = -0.2617994F;
		float f2 = 0.0F;
		float f3 = 0.0F;
		if (entityLivingBaseIn.isFallFlying()) {
			float f4 = 1.0F;
			Vec3 vector3d = entityLivingBaseIn.getDeltaMovement();
			if (vector3d.y < 0.0D) {
				Vec3 vector3d1 = vector3d.normalize();
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
		if (entityLivingBaseIn instanceof AbstractClientPlayer) {
			AbstractClientPlayer abstractclientplayerentity = (AbstractClientPlayer) entityLivingBaseIn;
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

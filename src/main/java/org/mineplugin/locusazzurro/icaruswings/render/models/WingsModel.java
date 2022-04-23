package org.mineplugin.locusazzurro.icaruswings.render.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;

@OnlyIn(Dist.CLIENT)
public class WingsModel<T extends LivingEntity> extends ElytraModel<T> {
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final float PI = (float) Math.PI;

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
	public void setupAnim(T pEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float xRot = PI / 12; //15 deg
		float zRot = - PI / 12;
		float yPos = 0.0F;
		float yRot = 0.0F;
		if (pEntity.isFallFlying()) {
			float fallMod = 1.0F;
			Vec3 mov = pEntity.getDeltaMovement();
			if (mov.y < 0.0D) {
				Vec3 movN = mov.normalize();
				fallMod = 1.0F - (float) Math.pow(-movN.y, 1.5D);
			}

			xRot = fallMod * 0.3491F + (1.0F - fallMod) * xRot; //20 deg
			zRot = fallMod * (- PI / 2F) + (1.0F - fallMod) * zRot;
		} else if (pEntity.isCrouching()) {
			xRot = 0.6981F; //40 deg
			zRot = (- PI / 4F);
			yPos = 3.0F;
			yRot = 0.0873F; //50 deg
		}
		else if (AbstractWings.isEntityFloating(pEntity)){
			xRot = 0.3491F; //20 deg
			zRot = - PI / 1.95F;
		}

		this.leftWing.x = 5.0F;
		this.leftWing.y = yPos;
		if (pEntity instanceof AbstractClientPlayer player) {
			player.elytraRotX += (xRot - player.elytraRotX) * 0.1f;
			player.elytraRotY += (yRot - player.elytraRotY) * 0.1f;
			player.elytraRotZ += (zRot - player.elytraRotZ) * 0.1f;
			this.leftWing.xRot = player.elytraRotX;
			this.leftWing.yRot = player.elytraRotY;
			this.leftWing.zRot = player.elytraRotZ;
		} else {
			this.leftWing.xRot = xRot;
			this.leftWing.yRot = yRot;
			this.leftWing.zRot = zRot;
		}
		this.rightWing.x = -this.leftWing.x;
		this.rightWing.yRot = -this.leftWing.yRot;
		this.rightWing.y = this.leftWing.y;
		this.rightWing.xRot = this.leftWing.xRot;
		this.rightWing.zRot = -this.leftWing.zRot;
	}
}

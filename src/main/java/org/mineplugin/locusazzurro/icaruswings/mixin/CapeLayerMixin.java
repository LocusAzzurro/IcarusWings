package org.mineplugin.locusazzurro.icaruswings.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {

	@Inject(at = @At(value = "HEAD"), method = "render*", cancellable = true)
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer livingEntity,
					   float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch,
					   CallbackInfo cb) {

		ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (itemstack.getItem() instanceof AbstractWings) {
        	cb.cancel();
        }
	}
}

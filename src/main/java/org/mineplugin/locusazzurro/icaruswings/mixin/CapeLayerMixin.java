package org.mineplugin.locusazzurro.icaruswings.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.common.item.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {

	@Inject(at = @At(value = "HEAD"), method = "render*", cancellable = true)
	public void render(PoseStack pose, MultiBufferSource p_225628_2_, int p_225628_3_,
					   AbstractClientPlayer clientPlayer, float p_225628_5_, float p_225628_6_, float p_225628_7_,
					   float p_225628_8_, float p_225628_9_, float p_225628_10_, CallbackInfo cb) {
		
		ItemStack itemstack = clientPlayer.getItemBySlot(EquipmentSlot.CHEST);
        if (itemstack.getItem() instanceof AbstractWings) {
        	cb.cancel();
        }
	}
}

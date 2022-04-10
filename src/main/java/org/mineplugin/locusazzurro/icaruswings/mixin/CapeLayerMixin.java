package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.client.player.AbstractClientPlayer;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {

	@Inject(at = @At(value = "HEAD"), method = "render", cancellable = true)
	public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_,
					   AbstractClientPlayer p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_,
					   float p_225628_8_, float p_225628_9_, float p_225628_10_, CallbackInfo cb) {
		
		ItemStack itemstack = p_225628_4_.getItemBySlot(EquipmentSlot.CHEST);
        if (itemstack.getItem() instanceof AbstractWings) {
        	cb.cancel();
        }
	}
}

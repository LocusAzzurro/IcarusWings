package org.mineplugin.locusazzurro.icaruswings.mixin;

import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {

	@Inject(at = @At(value = "HEAD"), method = "render", cancellable = true)
	public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_,
			AbstractClientPlayerEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_,
			float p_225628_8_, float p_225628_9_, float p_225628_10_, CallbackInfo cb) {
		
		ItemStack itemstack = p_225628_4_.getItemBySlot(EquipmentSlotType.CHEST);
        if (itemstack.getItem() instanceof AbstractWings) {
        	cb.cancel();
        }
	}
}

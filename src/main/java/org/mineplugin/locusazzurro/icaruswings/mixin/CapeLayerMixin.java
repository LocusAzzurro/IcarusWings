package org.mineplugin.locusazzurro.icaruswings.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {

    @Inject(
            method = "submit",
            at = @At("HEAD"),
            cancellable = true
    )
    private void icaruswings$hideCapeWithWings(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, AvatarRenderState state,
                          float yRot, float xRot, CallbackInfo ci){
        ItemStack chestEquipment = state.chestEquipment;
        if (chestEquipment.getItem() instanceof AbstractWings)
            ci.cancel();
    }


}

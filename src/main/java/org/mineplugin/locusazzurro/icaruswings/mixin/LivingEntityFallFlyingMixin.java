package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.WingsGlidingHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityFallFlyingMixin {

    @Inject(
            method = "updateFallFlying",
            at = @At("HEAD"),
            cancellable = true
    )
    private void icaruswings$customUpdateFallFlying(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        ItemStack chest = self.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.has(DataComponentRegistry.ICARUS_GLIDER)) {
            WingsGlidingHooks.icarusUpdateFallFlying(self);
            ci.cancel();
        }
    }
}

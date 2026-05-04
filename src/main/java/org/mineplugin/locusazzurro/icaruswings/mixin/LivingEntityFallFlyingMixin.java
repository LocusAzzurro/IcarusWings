package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.WingsGlidingHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityFallFlyingMixin {
    @Unique
    private boolean icaruswings$wasFallFlyingBeforeVanillaUpdate;

    @Inject(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;updateFallFlying()V"
            )
    )
    private void icaruswings$captureFallFlyingBeforeVanilla(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        this.icaruswings$wasFallFlyingBeforeVanillaUpdate = self.isFallFlying();
    }

    @Inject(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;updateFallFlying()V",
                    shift = At.Shift.AFTER
            )
    )
    private void icaruswings$appendCustomFallFlyingAfterVanilla(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        ItemStack chest = self.getItemBySlot(EquipmentSlot.CHEST);
        boolean hasCustomGlider = chest.has(DataComponentRegistry.ICARUS_GLIDER);
        if (hasCustomGlider && (self.isFallFlying() || this.icaruswings$wasFallFlyingBeforeVanillaUpdate)) {
            WingsGlidingHooks.icarusUpdateFallFlying(self);
        }
        this.icaruswings$wasFallFlyingBeforeVanillaUpdate = false;
    }
}

package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.WingsGlidingHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerFallFlyingMixin {
    @Inject(method = "tryToStartFallFlying", at = @At("HEAD"), cancellable = true)
    private void icaruswings$tryToStartCustomFallFlying(CallbackInfoReturnable<Boolean> cir) {
        Player self = (Player) (Object) this;
        ItemStack chest = self.getItemBySlot(EquipmentSlot.CHEST);
        if (!chest.has(DataComponentRegistry.ICARUS_GLIDER)) {
            return;
        }
        if (!self.isFallFlying() && !self.getAbilities().flying && WingsGlidingHooks.canIcarusGlide(self) && !self.isInWater()) {
            self.startFallFlying();
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }
}

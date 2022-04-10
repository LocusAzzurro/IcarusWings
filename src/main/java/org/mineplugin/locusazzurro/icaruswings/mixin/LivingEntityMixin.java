package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At ("HEAD"),
            method = {"equipEventAndSound"},
            cancellable = true)
    private void playEquipSound(ItemStack itemStack, CallbackInfo ci){
        Item item = itemStack.getItem();
        if (item instanceof AbstractWings){
            ((Entity)(Object) this).playSound(((AbstractWings)item).getType().getEquipSound(), 1.0F, 1.0F);
            ci.cancel();
        }
    }

}


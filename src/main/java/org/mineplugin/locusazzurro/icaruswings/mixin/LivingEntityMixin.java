package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At ("HEAD"),
            method = {"playEquipSound(Lnet/minecraft/item/ItemStack;)V"},
            cancellable = true)
    private void playEquipSound(ItemStack itemStack, CallbackInfo ci){
        Item item = itemStack.getItem();
        if (item instanceof AbstractWings){
            ((Entity)(Object) this).playSound(((AbstractWings)item).getType().getEquipSound(), 1.0F, 1.0F);
            System.out.println("Injection for Entity:" + this);
            ci.cancel();
        }
    }

}


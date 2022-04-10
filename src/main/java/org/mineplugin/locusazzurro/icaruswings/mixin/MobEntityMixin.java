package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MobEntityMixin {
	
	@Inject(at = @At ("HEAD"),
			method = {"getEquipmentSlotForItem"},
			cancellable = true)
	private static void getEquipmentSlotForItem(ItemStack item, CallbackInfoReturnable<EquipmentSlot> cir)
	{
		if (item.getItem() instanceof AbstractWings) {
			cir.setReturnValue(EquipmentSlot.CHEST);
		}
	}
}

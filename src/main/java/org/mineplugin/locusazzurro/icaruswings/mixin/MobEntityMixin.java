package org.mineplugin.locusazzurro.icaruswings.mixin;

import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

@Mixin(MobEntity.class)
public class MobEntityMixin {
	
	@Inject(at = @At ("HEAD"),
			method = {"getEquipmentSlotForItem(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/inventory/EquipmentSlotType;"},
			cancellable = true)
	private static void getEquipmentSlotForItem(ItemStack item, CallbackInfoReturnable<EquipmentSlotType> cir)
	{
		if (item.getItem() instanceof AbstractWings) {
		cir.setReturnValue(EquipmentSlotType.CHEST);
		}
	}
}

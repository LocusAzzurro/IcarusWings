package org.mineplugin.locusazzurro.icaruswings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.world.World;

@Mixin(ItemEntity.class)
public abstract class ItemMixinTest extends Entity{
	
	public ItemMixinTest(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
	}

	@Inject(at = @At("HEAD"), method = "fireImmune()Z", cancellable = true)
	private void fireInjectTest(CallbackInfoReturnable<Boolean> callback) {
		System.out.println("inject test");
		callback.setReturnValue(true);
	}
}

package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * @author Flechazo
 * <p>
 * 滑翔行为在某版本已改为数据组件
 * <p>
 * 在难以为我们的翅膀单独重写滑翔逻辑的情况下，本接口提供了一套解耦的滑翔行为控制方案
 */
public interface IcarusGlider {
    default boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return !stack.nextDamageWillBreak();
    }

    default void elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide() && (flightTicks + 1) % 20 == 0) {
            stack.hurtAndBreak(1, entity, EquipmentSlot.CHEST);
        }
    }
}

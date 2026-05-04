package org.mineplugin.locusazzurro.icaruswings.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.effect.MobEffects;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.IcarusGlider;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

public final class WingsGlidingHooks {
    private WingsGlidingHooks() {}

    public static boolean canUseIcarusGlider(ItemStack stack, EquipmentSlot slot) {
        if (!stack.has(DataComponentRegistry.ICARUS_GLIDER)) {
            return false;
        }
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        return equippable != null && slot == equippable.slot() && !stack.nextDamageWillBreak();
    }

    public static boolean canIcarusGlide(LivingEntity entity) {
        if (entity.onGround() || entity.isPassenger() || entity.hasEffect(MobEffects.LEVITATION)) {
            return false;
        }
        if (entity instanceof Player player && player.getAbilities().flying) {
            return false;
        }
        for (EquipmentSlot slot : EquipmentSlot.VALUES) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (canUseIcarusGlider(stack, slot)
                    && stack.getItem() instanceof IcarusGlider wings
                    && wings.canElytraFly(stack, entity)) {
                return true;
            }
        }
        return false;
    }

    public static void icarusUpdateFallFlying(LivingEntity entity) {
        entity.checkFallDistanceAccumulation();

        if (!entity.level().isClientSide()) {
            if (!canIcarusGlide(entity)) {
                entity.stopFallFlying();
                return;
            }

            int checkFallFlyTicks = entity.getFallFlyingTicks() + 1;
            if (checkFallFlyTicks % 10 == 0) {
                entity.gameEvent(GameEvent.ELYTRA_GLIDE);
            }
        }

        ItemStack chestStack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (!(chestStack.getItem() instanceof IcarusGlider wings)) {
            return;
        }
        if (!wings.canElytraFly(chestStack, entity)) {
            entity.stopFallFlying();
            return;
        }
        wings.elytraFlightTick(chestStack, entity, entity.getFallFlyingTicks());
    }

}

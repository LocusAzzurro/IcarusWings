package org.mineplugin.locusazzurro.icaruswings.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class InventoryHelper {
    private InventoryHelper() {}

    public static void giveToPlayer(Player player, ItemStack stack) {
        if (!stack.isEmpty()) {
            player.getInventory().placeItemBackInInventory(stack);
        }
    }
}

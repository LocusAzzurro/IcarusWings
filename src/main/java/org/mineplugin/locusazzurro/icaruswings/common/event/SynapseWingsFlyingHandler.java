package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModConfig;
import org.mineplugin.locusazzurro.icaruswings.common.item.SynapseWings;

@EventBusSubscriber
public class SynapseWingsFlyingHandler {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        if (player.isFallFlying() && item instanceof SynapseWings wings) {
            Vec3 lookAngle = player.getLookAngle();
            Vec3 flyAngle = player.getDeltaMovement();
            double d = wings.getDirectSpeedMod();
            double i = wings.getInertialSpeedMod();
            double t = wings.getTotalSpeedMod();
            double c = ModConfig.WINGS_SPEED_MOD.get();
            player.setDeltaMovement(flyAngle.add(
                    (lookAngle.x * d + (lookAngle.x * i - flyAngle.x) * t) * c,
                    (lookAngle.y * d + (lookAngle.y * i - flyAngle.y) * t) * c,
                    (lookAngle.z * d + (lookAngle.z * i - flyAngle.z) * t) * c));
        }
    }



}

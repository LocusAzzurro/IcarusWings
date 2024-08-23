package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

@EventBusSubscriber
public class FuelBurnTimeHandler {

    @SubscribeEvent
    public static void onFuelTime(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == ItemRegistry.BEESWAX.get())
            event.setBurnTime(200);
    }
}

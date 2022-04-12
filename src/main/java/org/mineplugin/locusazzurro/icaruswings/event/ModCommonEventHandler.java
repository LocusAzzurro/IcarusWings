package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.network.ModPacketHandler;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModCommonEventHandler {

    @SubscribeEvent
    public static void registerPacketHandlers(FMLCommonSetupEvent event) {
        event.enqueueWork(ModPacketHandler::register);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e){
        e.put(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamEntity.setCustomAttributes().build());
    }
}

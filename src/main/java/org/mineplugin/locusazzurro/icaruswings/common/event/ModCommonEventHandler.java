package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.common.network.ModPacketHandler;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

@Mod.EventBusSubscriber(modid = ModData.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModCommonEventHandler {

    @SubscribeEvent
    public static void registerPacketHandlers(FMLCommonSetupEvent event) {
        event.enqueueWork(ModPacketHandler::register);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e){
        e.put(EntityTypeRegistry.GOLDEN_RAM.get(), GoldenRamEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
    }
}

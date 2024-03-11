package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
        e.put(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
    }
}

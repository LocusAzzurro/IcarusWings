package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.common.network.ClientBoundSparklePacket;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

@EventBusSubscriber(modid = IcarusWings.MOD_ID, bus = EventBusSubscriber.Bus.MOD)

public class ModCommonEventHandler {

    @SubscribeEvent
    public static void onRegisterPayloadHandler(RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(IcarusWings.MOD_ID)
                .versioned("1")
                .optional();
        registrar.play(ClientBoundSparklePacket.ID, ClientBoundSparklePacket::create,
                handler -> handler.client(ClientBoundSparklePacket::handle));
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e){
        e.put(EntityTypeRegistry.GOLDEN_RAM.get(), GoldenRamEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
    }
}

package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.entity.*;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributesHandler {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent e){
        e.put(EntityTypeRegistry.goldenRamEntity.get(), GoldenRamEntity.setCustomAttributes().build());
    }
}

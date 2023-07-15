package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.network.ModPacketHandler;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

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
    public static void buildContents(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(ModData.MOD_ID, "icaruswings"), builder -> builder
                .icon(() -> ItemRegistry.iconBadge.get().getDefaultInstance())
                .title(Component.translatable("itemGroup.icaruswings_group"))
                .displayItems((displayParameters, output) -> ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept))
        );
    }
}

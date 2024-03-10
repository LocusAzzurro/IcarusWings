package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModData.MOD_ID);
    public static RegistryObject<CreativeModeTab> ICARUS_WINGS = CREATIVE_TABS.register("icaruswings",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.icaruswings_group"))
                    .icon(ItemRegistry.ICON_BADGE.get()::getDefaultInstance)
                    .withTabsBefore(CreativeModeTabs.OP_BLOCKS)
                    .displayItems((displayParameters, output) -> ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept))
                    .build());
}

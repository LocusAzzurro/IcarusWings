package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

import java.util.function.Supplier;

public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IcarusWings.MOD_ID);
    public static Supplier<CreativeModeTab> ICARUS_WINGS = CREATIVE_TABS.register("icaruswings",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.icaruswings_group"))
                    .icon(ItemRegistry.ICON_BADGE.get()::getDefaultInstance)
                    .withTabsBefore(CreativeModeTabs.OP_BLOCKS)
                    .displayItems((displayParameters, output) -> ItemRegistry.ITEMS.getEntries().stream().map(Supplier::get).forEach(output::accept))
                    .build());
}

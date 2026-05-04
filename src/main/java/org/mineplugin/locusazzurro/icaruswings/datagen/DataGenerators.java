package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModEnchantments;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModJukeboxSongs;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModVillagerTrades;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DataGenerators.MOD_ID)
public class DataGenerators {

    public static final String MOD_ID = IcarusWings.MOD_ID;

    public static final RegistrySetBuilder DATAPACK_BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.DAMAGE_TYPE, ModDamageSources::bootstrap)
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap)
            .add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new ModRecipesProvider.Runner(output, lookupProvider));
        event.addProvider(ModLootTableProvider.create(output, lookupProvider));

        event.createDatapackRegistryObjects(DATAPACK_BUILDER, Set.of(MOD_ID));
        CompletableFuture<HolderLookup.Provider> modLookupProvider = event.getLookupProvider();

        event.addProvider(new ModTagsProvider.ModBlockTagsProvider(output, lookupProvider));
        event.addProvider(new ModTagsProvider.ModItemTagsProvider(output, lookupProvider));
        event.addProvider(new ModTagsProvider.ModEnchantmentTagsProvider(output, modLookupProvider));
        event.addProvider(new ModTagsProvider.ModDamageTagsProvider(output, modLookupProvider));
        event.addProvider(new ModTagsProvider.ModFluidTagsProvider(output, modLookupProvider));
        event.addProvider(new ModTagsProvider.ModVillagerTradeTagsProvider(output, modLookupProvider));
        event.addProvider(new ModGlobalLootModifierProvider(output, lookupProvider));
        event.addProvider(new ModAdvancementProvider(output, lookupProvider));
        event.addProvider(new ModModelProvider(output));
        event.addProvider(new ModParticleProvider(output));
        event.addProvider(new ModSoundDefinitionsProvider(output));
    }
}

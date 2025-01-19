package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModEnchantments;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModJukeboxSongs;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author LocusAzzurro
 * @author DustW
 **/
@EventBusSubscriber(modid = DataGenerators.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    public static final String MOD_ID = IcarusWings.MOD_ID;

    public static final RegistrySetBuilder DATAPACK_BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.DAMAGE_TYPE, ModDamageSources::bootstrap)
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);

    public static HolderLookup.Provider createLookup() {
        RegistryAccess.Frozen registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        return DATAPACK_BUILDER.build(registryAccess);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fh = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        CompletableFuture<HolderLookup.Provider> modLookupProvider = CompletableFuture.supplyAsync(DataGenerators::createLookup, Util.backgroundExecutor());

        generator.addProvider(event.includeServer(), new ModRecipesProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output, lookupProvider));

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, modLookupProvider, DATAPACK_BUILDER, Set.of(MOD_ID)));
        ModTagsProvider.ModBlockTagsProvider blockTags = new ModTagsProvider.ModBlockTagsProvider(output, lookupProvider, fh);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModTagsProvider.ModItemTagsProvider(output, lookupProvider, blockTags, fh));
        generator.addProvider(event.includeServer(), new ModTagsProvider.ModEnchantmentTagsProvider(output, modLookupProvider, fh));
        generator.addProvider(event.includeServer(), new ModTagsProvider.ModDamageTagsProvider(output, modLookupProvider, fh));
        generator.addProvider(event.includeServer(), new ModTagsProvider.ModFluidTagsProvider(output, modLookupProvider, fh));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ModAdvancementProvider(output, lookupProvider, fh));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, fh));
        generator.addProvider(event.includeClient(), new ModItemModelGenerator(output, fh));
    }
}
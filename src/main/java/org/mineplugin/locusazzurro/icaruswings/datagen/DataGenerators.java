package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author DustW
 **/
@EventBusSubscriber(modid = DataGenerators.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    public static final String MOD_ID = ModData.MOD_ID;
    public static final String MOD_NAME = "Icarus Wings";

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, ModDamageSources::bootstrap);

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fh = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipesProvider(output));
        generator.addProvider(event.includeServer(), LootTableProvider.create(output));

        ModBlockTagsGenerator blockTags = new ModBlockTagsGenerator(output, lookupProvider, fh);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTags, fh));
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BUILDER, Set.of(ModData.MOD_ID)));
        //generator.addProvider(event.includeServer(), new ModDamageTagsGenerator(output, lookupProvider, fh));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, fh));
        generator.addProvider(event.includeClient(), new ModItemModelGenerator(output, fh));
    }
}
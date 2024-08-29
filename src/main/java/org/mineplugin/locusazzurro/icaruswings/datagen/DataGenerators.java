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

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author DustW
 **/
@EventBusSubscriber(modid = DataGenerators.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    public static final String MOD_ID = IcarusWings.MOD_ID;
    public static final String MOD_NAME = "Icarus Wings";


    public static final RegistrySetBuilder DATAPACK_BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.DAMAGE_TYPE, ModDamageSources::bootstrap);

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

        ModBlockTagsGenerator blockTags = new ModBlockTagsGenerator(output, lookupProvider, fh);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTags, fh));
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, modLookupProvider, DATAPACK_BUILDER, Set.of(MOD_ID)));
        generator.addProvider(event.includeServer(), new ModDamageTagsGenerator(output, modLookupProvider, fh));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(output, lookupProvider));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, fh));
        generator.addProvider(event.includeClient(), new ModItemModelGenerator(output, fh));
    }
}
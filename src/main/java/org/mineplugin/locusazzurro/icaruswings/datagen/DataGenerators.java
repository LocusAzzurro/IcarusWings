package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(modid = DataGenerators.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    public static final String MOD_ID = ModData.MOD_ID;
    public static final String MOD_NAME = "Icarus Wings";

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new RecipesGenerator(generator));
        generator.addProvider(event.includeServer(), new LootTablesGenerator(generator));
        BlockTagsGenerator blockTags = new BlockTagsGenerator(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ItemTagsGenerator(generator, blockTags, event.getExistingFileHelper()));

        generator.addProvider(event.includeClient(), new BlockStatesGenerator(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ItemModelsGenerator(generator, event.getExistingFileHelper()));
    }
}
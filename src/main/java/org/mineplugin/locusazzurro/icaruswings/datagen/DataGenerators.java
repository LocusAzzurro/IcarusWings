package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(modid = DataGenerators.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    public static final String MOD_ID = ModData.MOD_ID;

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new RecipesGenerator(generator));
            generator.addProvider(new LootTablesGenerator(generator));
            BlockTagsGenerator blockTags = new BlockTagsGenerator(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ItemTagsGenerator(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new BlockStatesGenerator(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModelsGenerator(generator, event.getExistingFileHelper()));
        }
    }
}
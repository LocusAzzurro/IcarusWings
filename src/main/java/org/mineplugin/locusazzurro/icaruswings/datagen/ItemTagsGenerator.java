package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

/**
 * @author DustW
 **/
public class ItemTagsGenerator extends ItemTagsProvider {

    public ItemTagsGenerator(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void addTags() {

        tag(ModTags.COLORED_FEATHERS)
                .add(ItemRegistry.redFeather.get())
                .add(ItemRegistry.blueFeather.get())
                .add(ItemRegistry.greenFeather.get())
                .add(ItemRegistry.cyanFeather.get())
                .add(ItemRegistry.grayFeather.get());
        // todo 工具的 tag 在这里加，你看看需要不需要吧
        //tag(AvarusItemTags.COLORFUL_DIRT)
        //        .add(AvarusItems.RED_DIRT.get())
        //        .add(AvarusItems.GREEN_DIRT.get())
        //        .add(AvarusItems.BLUE_DIRT.get())
        //        .add(AvarusItems.YELLOW_DIRT.get());
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}

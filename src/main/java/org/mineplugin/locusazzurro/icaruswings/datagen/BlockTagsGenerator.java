package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

/**
 * @author DustW
 **/
public class BlockTagsGenerator extends BlockTagsProvider {

    public BlockTagsGenerator(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        // todo 方块的 tag 在这里加
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.refinedBeeswaxBlock.get());
        //tag(BlockTags.MINEABLE_WITH_SHOVEL)
        //        .add(AvarusBlocks.RED_DIRT.get())
        //        .add(AvarusBlocks.GREEN_DIRT.get())
        //        .add(AvarusBlocks.YELLOW_DIRT.get())
        //        .add(AvarusBlocks.BLUE_DIRT.get());
        //tag(BlockTags.NEEDS_IRON_TOOL)
        //        .add(AvarusBlocks.RED_DIRT.get())
        //        .add(AvarusBlocks.GREEN_DIRT.get())
        //        .add(AvarusBlocks.YELLOW_DIRT.get())
        //        .add(AvarusBlocks.BLUE_DIRT.get());
    }

    @Override
    public String getName() {
        return DataGenerators.MOD_ID + " Tags";
    }
}
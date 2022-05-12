package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
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
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.refinedBeeswaxBlock.get())
                .add(BlockRegistry.refinedBeeswaxPillar.get())
                .add(BlockRegistry.refinedBeeswaxBlockChiseled.get())
                .add(BlockRegistry.refinedBeeswaxStairs.get())
                .add(BlockRegistry.refinedBeeswaxSlab.get())
                .add(BlockRegistry.smoothRefinedBeeswaxBlock.get())
                .add(BlockRegistry.amphora.get())
                .add(BlockRegistry.meadPot.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockRegistry.elysianSoil.get())
                .add(BlockRegistry.elysianGrassBlock.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockRegistry.refinedBeeswaxBlock.get())
                .add(BlockRegistry.refinedBeeswaxPillar.get())
                .add(BlockRegistry.refinedBeeswaxBlockChiseled.get())
                .add(BlockRegistry.refinedBeeswaxStairs.get())
                .add(BlockRegistry.refinedBeeswaxSlab.get())
                .add(BlockRegistry.smoothRefinedBeeswaxBlock.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegistry.meadPot.get());
    }

    @Override
    public String getName() {
        return DataGenerators.MOD_NAME + "Block Tags";
    }
}
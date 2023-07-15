package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import java.util.concurrent.CompletableFuture;

/**
 * @author DustW
 * @author LocusAzzurro
 **/
public class ModBlockTagsGenerator extends BlockTagsProvider {

    public ModBlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper helper) {
        super(output, lookupProvider, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
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
        tag(BlockTags.WOOL)
                .add(BlockRegistry.goldenWoolBlock.get());
        tag(BlockTags.WOOL_CARPETS)
                .add(BlockRegistry.goldenWoolCarpet.get());
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
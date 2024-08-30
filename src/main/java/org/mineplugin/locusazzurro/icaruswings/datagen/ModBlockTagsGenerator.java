package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
                .add(BlockRegistry.REFINED_BEESWAX_BLOCK.get())
                .add(BlockRegistry.REFINED_BEESWAX_PILLAR.get())
                .add(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get())
                .add(BlockRegistry.REFINED_BEESWAX_STAIRS.get())
                .add(BlockRegistry.REFINED_BEESWAX_SLAB.get())
                .add(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get())
                .add(BlockRegistry.AMPHORA.get())
                .add(BlockRegistry.MEAD_POT.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockRegistry.ELYSIAN_SOIL.get())
                .add(BlockRegistry.ELYSIAN_GRASS_BLOCK.get());
        tag(BlockTags.WOOL)
                .add(BlockRegistry.GOLDEN_WOOL_BLOCK.get());
        tag(BlockTags.WOOL_CARPETS)
                .add(BlockRegistry.GOLDEN_WOOL_CARPET.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockRegistry.REFINED_BEESWAX_BLOCK.get())
                .add(BlockRegistry.REFINED_BEESWAX_PILLAR.get())
                .add(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get())
                .add(BlockRegistry.REFINED_BEESWAX_STAIRS.get())
                .add(BlockRegistry.REFINED_BEESWAX_SLAB.get())
                .add(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegistry.MEAD_POT.get());
    }

}
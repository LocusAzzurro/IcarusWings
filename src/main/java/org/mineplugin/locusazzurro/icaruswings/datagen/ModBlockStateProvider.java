package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class ModBlockStateProvider extends BlockStateProvider {

    private static String BLOCK = "block/";

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DataGenerators.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegistry.BEESWAX_BLOCK.get());
        simpleBlock(BlockRegistry.REFINED_BEESWAX_BLOCK.get());
        slabBlock(BlockRegistry.REFINED_BEESWAX_SLAB.get(), BuiltInRegistries.BLOCK.getKey(BlockRegistry.REFINED_BEESWAX_BLOCK.get()), blockLoc(BlockRegistry.REFINED_BEESWAX_BLOCK.get()));
        axisBlock(BlockRegistry.REFINED_BEESWAX_PILLAR.get(), sideLoc(BlockRegistry.REFINED_BEESWAX_PILLAR.get()), endLoc(BlockRegistry.REFINED_BEESWAX_PILLAR.get()));
        stairsBlock(BlockRegistry.REFINED_BEESWAX_STAIRS.get(), blockLoc(BlockRegistry.REFINED_BEESWAX_BLOCK.get()));
        simpleBlock(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get(), models().cubeColumn(name(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get()), sideLoc(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get()), endLoc(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get())));
        simpleBlock(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get());
    }

    private void existingModelBlock(Block block){
        simpleBlock(block, models().getExistingFile(modLoc(BLOCK + name(block))));
    }

    private ResourceLocation blockLoc(Block block){
        return modLoc(BLOCK + name(block));
    }

    private ResourceLocation sideLoc(Block block){
        return modLoc(BLOCK + name(block) + "_side");
    }

    private ResourceLocation endLoc(Block block){
        return modLoc(BLOCK + name(block) + "_end");
    }

    private String name(Block block){
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

}
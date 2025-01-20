package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.common.block.Amphora;
import org.mineplugin.locusazzurro.icaruswings.common.block.MeadPot;
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
        getVariantBuilder(BlockRegistry.FLAX_CROP.get()).forAllStates(blockState -> {
            int age = blockState.getValue(CropBlock.AGE);
            return ConfiguredModel.builder().modelFile(
                    models().crop(name(BlockRegistry.FLAX_CROP.get()) + "_stage" + age, modLoc(BLOCK + name(BlockRegistry.FLAX_CROP.get()) + "_stage" + age)).renderType("cutout")).build();
        });
        simpleBlock(BlockRegistry.ELYSIAN_GRASS_BLOCK.get(), ConfiguredModel.allYRotations(models().cubeBottomTop(
                name(BlockRegistry.ELYSIAN_GRASS_BLOCK.get()), sideLoc(BlockRegistry.ELYSIAN_GRASS_BLOCK.get()),
                blockLoc(BlockRegistry.ELYSIAN_SOIL.get()), topLoc(BlockRegistry.ELYSIAN_GRASS_BLOCK.get())
                ), 0, false));
        simpleBlock(BlockRegistry.ELYSIAN_SOIL.get());
        simpleBlock(BlockRegistry.ELYSIAN_GRASS.get(), models().cross(name(BlockRegistry.ELYSIAN_GRASS.get()), blockLoc(BlockRegistry.ELYSIAN_GRASS.get())).renderType("cutout"));
        simpleBlock(BlockRegistry.GOLDEN_WOOL_BLOCK.get());
        simpleBlock(BlockRegistry.GOLDEN_WOOL_CARPET.get(), models().carpet(name(BlockRegistry.GOLDEN_WOOL_CARPET.get()), blockLoc(BlockRegistry.GOLDEN_WOOL_BLOCK.get())));
        getVariantBuilder(BlockRegistry.HONEY_CAKE.get()).forAllStates(blockState -> {
            int slice = blockState.getValue(CakeBlock.BITES);
            return ConfiguredModel.builder().modelFile(models().getExistingFile(modLoc(BLOCK + name(BlockRegistry.HONEY_CAKE.get()) + "_slice" + slice))).build();
        });
        getVariantBuilder(BlockRegistry.MEAD_POT.get()).forAllStates(blockState -> {
            var state = blockState.getValue(MeadPot.STATE);
            return ConfiguredModel.builder().modelFile(models().getExistingFile(modLoc(BLOCK + name(BlockRegistry.MEAD_POT.get()) + "_" + state.getSerializedName()))).build();
        });
        getVariantBuilder(BlockRegistry.AMPHORA.get()).forAllStates(blockState -> {
            DoubleBlockHalf half = blockState.getValue(Amphora.HALF);
            Direction.Axis axis = blockState.getValue(Amphora.FACING).getAxis();
            ModelFile.ExistingModelFile modelLower = models().getExistingFile(modLoc(BLOCK + "amphora_lower"));
            ModelFile.ExistingModelFile modelUpper = models().getExistingFile(modLoc(BLOCK + "amphora_upper"));
            return ConfiguredModel.builder().modelFile(half == DoubleBlockHalf.LOWER ? modelLower : modelUpper).rotationY(axis == Direction.Axis.X ? 0 : 90).build();
        });

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

    private ResourceLocation topLoc(Block block){
        return modLoc(BLOCK + name(block) + "_top");
    }

    private String name(Block block){
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

}
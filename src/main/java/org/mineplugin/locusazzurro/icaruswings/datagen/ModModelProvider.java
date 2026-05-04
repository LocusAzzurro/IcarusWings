package org.mineplugin.locusazzurro.icaruswings.datagen;

import com.google.gson.JsonObject;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.SpearBakedModel;
import org.mineplugin.locusazzurro.icaruswings.common.block.Amphora;
import org.mineplugin.locusazzurro.icaruswings.common.block.MeadPot;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.Optional;
import java.util.Set;

public class ModModelProvider extends ModelProvider {
    private static final ModelTemplate SPAWN_EGG_TEMPLATE = new ModelTemplate(
            Optional.of(Identifier.withDefaultNamespace("item/template_spawn_egg")),
            Optional.empty()
    );

    public ModModelProvider(PackOutput output) {
        super(output, DataGenerators.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        registerBlockModels(blockModels);
        registerItemModels(itemModels);
    }

    private void registerBlockModels(BlockModelGenerators blockModels) {
        Block beeswaxBlock = BlockRegistry.BEESWAX_BLOCK.get();
        Block refinedBeeswaxBlock = BlockRegistry.REFINED_BEESWAX_BLOCK.get();
        Block refinedBeeswaxSlab = BlockRegistry.REFINED_BEESWAX_SLAB.get();
        Block refinedBeeswaxPillar = BlockRegistry.REFINED_BEESWAX_PILLAR.get();
        Block refinedBeeswaxStairs = BlockRegistry.REFINED_BEESWAX_STAIRS.get();
        Block chiseledRefinedBeeswaxBlock = BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get();
        Block smoothRefinedBeeswaxBlock = BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get();
        Block flaxCrop = BlockRegistry.FLAX_CROP.get();
        Block elysianGrassBlock = BlockRegistry.ELYSIAN_GRASS_BLOCK.get();
        Block elysianSoil = BlockRegistry.ELYSIAN_SOIL.get();
        Block elysianGrass = BlockRegistry.ELYSIAN_GRASS.get();
        Block goldenWoolBlock = BlockRegistry.GOLDEN_WOOL_BLOCK.get();
        Block goldenWoolCarpet = BlockRegistry.GOLDEN_WOOL_CARPET.get();
        Block honeyCake = BlockRegistry.HONEY_CAKE.get();
        Block meadPot = BlockRegistry.MEAD_POT.get();
        Block amphora = BlockRegistry.AMPHORA.get();

        blockModels.createTrivialCube(beeswaxBlock);
        blockModels.createTrivialCube(refinedBeeswaxBlock);

        TextureMapping refinedBeeswaxTextures = TextureMapping.cube(refinedBeeswaxBlock);
        Identifier refinedSlabBottom = ModelTemplates.SLAB_BOTTOM.create(refinedBeeswaxSlab, refinedBeeswaxTextures, blockModels.modelOutput);
        Identifier refinedSlabTop = ModelTemplates.SLAB_TOP.create(refinedBeeswaxSlab, refinedBeeswaxTextures, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createSlab(
                        refinedBeeswaxSlab,
                        BlockModelGenerators.plainVariant(refinedSlabBottom),
                        BlockModelGenerators.plainVariant(refinedSlabTop),
                        BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(refinedBeeswaxBlock))
                )
        );

        TextureMapping refinedPillarTextures = TextureMapping.column(
                TextureMapping.getBlockTexture(refinedBeeswaxPillar, "_side"),
                TextureMapping.getBlockTexture(refinedBeeswaxPillar, "_end")
        );
        Identifier refinedPillarModel = ModelTemplates.CUBE_COLUMN.create(refinedBeeswaxPillar, refinedPillarTextures, blockModels.modelOutput);
        Identifier refinedPillarHorizontalModel = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(refinedBeeswaxPillar, refinedPillarTextures, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createRotatedPillarWithHorizontalVariant(
                        refinedBeeswaxPillar,
                        BlockModelGenerators.plainVariant(refinedPillarModel),
                        BlockModelGenerators.plainVariant(refinedPillarHorizontalModel)
                )
        );

        Identifier refinedStairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(refinedBeeswaxStairs, refinedBeeswaxTextures, blockModels.modelOutput);
        Identifier refinedStairsInner = ModelTemplates.STAIRS_INNER.create(refinedBeeswaxStairs, refinedBeeswaxTextures, blockModels.modelOutput);
        Identifier refinedStairsOuter = ModelTemplates.STAIRS_OUTER.create(refinedBeeswaxStairs, refinedBeeswaxTextures, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createStairs(
                        refinedBeeswaxStairs,
                        BlockModelGenerators.plainVariant(refinedStairsInner),
                        BlockModelGenerators.plainVariant(refinedStairsStraight),
                        BlockModelGenerators.plainVariant(refinedStairsOuter)
                )
        );

        TextureMapping chiseledRefinedBeeswaxTextures = TextureMapping.column(
                TextureMapping.getBlockTexture(chiseledRefinedBeeswaxBlock, "_side"),
                TextureMapping.getBlockTexture(chiseledRefinedBeeswaxBlock, "_end")
        );
        Identifier chiseledRefinedBeeswaxModel = ModelTemplates.CUBE_COLUMN.create(
                chiseledRefinedBeeswaxBlock,
                chiseledRefinedBeeswaxTextures,
                blockModels.modelOutput
        );
        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        chiseledRefinedBeeswaxBlock,
                        BlockModelGenerators.plainVariant(chiseledRefinedBeeswaxModel)
                )
        );

        blockModels.createTrivialCube(smoothRefinedBeeswaxBlock);
        blockModels.createCropBlock(flaxCrop, CropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);

        TextureMapping elysianGrassBlockTextures = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(elysianGrassBlock, "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(elysianSoil))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(elysianGrassBlock, "_top"));
        Identifier elysianGrassBlockModel = ModelTemplates.CUBE_BOTTOM_TOP.create(
                elysianGrassBlock,
                elysianGrassBlockTextures,
                blockModels.modelOutput
        );
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        elysianGrassBlock,
                        BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(elysianGrassBlockModel))
                )
        );

        blockModels.createTrivialCube(elysianSoil);
        blockModels.createCrossBlockWithDefaultItem(elysianGrass, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialCube(goldenWoolBlock);
        Identifier goldenWoolCarpetModel = ModelTemplates.CARPET.create(
                goldenWoolCarpet,
                TextureMapping.wool(goldenWoolBlock),
                blockModels.modelOutput
        );
        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        goldenWoolCarpet,
                        BlockModelGenerators.plainVariant(goldenWoolCarpetModel)
                )
        );

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(honeyCake)
                        .with(PropertyDispatch.initial(CakeBlock.BITES).generate(bites -> {
                            String suffix = bites > 0 ? "_slice" + bites : "";
                            return BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(honeyCake, suffix));
                        }))
        );

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(meadPot)
                        .with(PropertyDispatch.initial(MeadPot.STATE).generate(state ->
                                BlockModelGenerators.plainVariant(modLocation("block/mead_pot_" + state.getSerializedName()))
                        ))
        );

        Identifier amphoraLower = modLocation("block/amphora_lower");
        Identifier amphoraUpper = modLocation("block/amphora_upper");
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(amphora)
                        .with(PropertyDispatch.initial(Amphora.HALF, Amphora.FACING).generate((half, facing) -> {
                            MultiVariant variant = half == DoubleBlockHalf.LOWER
                                    ? BlockModelGenerators.plainVariant(amphoraLower)
                                    : BlockModelGenerators.plainVariant(amphoraUpper);
                            return facing.getAxis() == Direction.Axis.X ? variant : variant.with(BlockModelGenerators.Y_ROT_90);
                        }))
        );

        blockModels.createNonTemplateModelBlock(BlockRegistry.GREEK_FIRE.get());
        blockModels.createNonTemplateModelBlock(BlockRegistry.MELON.get());
    }

    private void registerItemModels(ItemModelGenerators itemModels) {
        Set<Item> flatItems = Set.of(
                ItemRegistry.AMPHORA.get(),
                ItemRegistry.MEAD_POT.get(),
                ItemRegistry.HONEY_CAKE.get()
        );
        Set<Item> handheldItems = Set.of(
                ItemRegistry.STEEL_PICKAXE.get(),
                ItemRegistry.SYNAPSE_ALLOY_SWORD.get()
        );
        Set<Item> spearItems = Set.of(
                ItemRegistry.WOODEN_SPEAR.get(),
                ItemRegistry.STONE_SPEAR.get(),
                ItemRegistry.IRON_SPEAR.get(),
                ItemRegistry.STEEL_SPEAR.get(),
                ItemRegistry.GOLDEN_SPEAR.get(),
                ItemRegistry.DIAMOND_SPEAR.get(),
                ItemRegistry.NETHERITE_SPEAR.get(),
                ItemRegistry.SYNAPSE_ALLOY_SPEAR.get()
        );
        Set<Item> blockItemsWithGeneratedCustomItemModel = Set.of(
                ItemRegistry.ELYSIAN_GRASS.get(),
                ItemRegistry.FLAX_SEEDS.get()
        );
        Item customSpawnEgg = ItemRegistry.GOLDEN_RAM_SPAWN_EGG.get();
        Item customModelItem = ItemRegistry.DEMETER.get();

        for (Item item : ItemRegistry.ITEMS.getEntries().stream().map(DeferredHolder::get).toList()) {
            if (item == customModelItem) {
                itemModels.declareCustomModelItem(item);
                continue;
            }

            if (item == customSpawnEgg) {
                SPAWN_EGG_TEMPLATE.create(item, new TextureMapping(), itemModels.modelOutput);
                itemModels.declareCustomModelItem(item);
                continue;
            }

            if (handheldItems.contains(item)) {
                itemModels.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
                continue;
            }

            if (spearItems.contains(item)) {
                generateSpearItemModel(itemModels, item);
                continue;
            }

            if (flatItems.contains(item)) {
                itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
                continue;
            }

            if (item instanceof BlockItem blockItem) {
                if (blockItemsWithGeneratedCustomItemModel.contains(item)) {
                    continue;
                }
                createLegacyBlockItemModel(itemModels, blockItem);
                continue;
            }

            itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }
    }

    private void createLegacyBlockItemModel(ItemModelGenerators itemModels, BlockItem blockItem) {
        Identifier itemModel = ModelLocationUtils.getModelLocation(blockItem);
        Identifier blockModel = ModelLocationUtils.getModelLocation(blockItem.getBlock());
        itemModels.modelOutput.accept(itemModel, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", blockModel.toString());
            return json;
        });
    }

    private void generateSpearItemModel(ItemModelGenerators itemModels, Item spear) {
        Identifier flatModel = itemModels.createFlatItemModel(spear, ModelTemplates.FLAT_ITEM);
        Identifier inHandModel = ModelTemplates.SPEAR_IN_HAND.create(
                ModelLocationUtils.getModelLocation(spear, "_in_hand"),
                TextureMapping.layer0(TextureMapping.getItemTexture(spear)),
                itemModels.modelOutput
        );
        Identifier throwingModel = ModelLocationUtils.getModelLocation(spear, "_throwing");
        itemModels.modelOutput.accept(throwingModel, () -> SpearBakedModel.createThrowingBaseModel(TextureMapping.getItemTexture(spear).sprite()));
        itemModels.itemModelOutput.accept(
                spear,
                SpearBakedModel.createItemModel(flatModel, inHandModel, throwingModel),
                new ClientItem.Properties(true, false, 1.95F)
        );
    }
}

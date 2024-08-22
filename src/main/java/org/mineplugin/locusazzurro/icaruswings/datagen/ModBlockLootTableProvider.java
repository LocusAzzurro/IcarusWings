package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.predicates.*;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(BlockRegistry.BEESWAX_BLOCK.get());
        dropSelf(BlockRegistry.REFINED_BEESWAX_BLOCK.get());
        dropSelf(BlockRegistry.REFINED_BEESWAX_SLAB.get());
        dropSelf(BlockRegistry.REFINED_BEESWAX_PILLAR.get());
        dropSelf(BlockRegistry.REFINED_BEESWAX_STAIRS.get());
        dropSelf(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get());
        dropSelf(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get());
        add(BlockRegistry.FLAX_CROP.get(), createCropDrops(BlockRegistry.FLAX_CROP.get(), ItemRegistry.FLAX.get(), ItemRegistry.FLAX_SEEDS.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockRegistry.FLAX_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7))));
        add(BlockRegistry.ELYSIAN_GRASS_BLOCK.get(), createSingleItemTableWithSilkTouch(BlockRegistry.ELYSIAN_GRASS_BLOCK.get(), BlockRegistry.ELYSIAN_SOIL.get()));
        dropSelf(BlockRegistry.ELYSIAN_SOIL.get());
        add(BlockRegistry.ELYSIAN_GRASS.get(), createShearsOnlyDrop(BlockRegistry.ELYSIAN_GRASS.get()));
        dropSelf(BlockRegistry.GOLDEN_WOOL_BLOCK.get());
        dropSelf(BlockRegistry.GOLDEN_WOOL_CARPET.get());
        dropSelf(BlockRegistry.MEAD_POT.get());
        add(BlockRegistry.AMPHORA.get(), createSinglePropConditionTable(BlockRegistry.AMPHORA.get(), BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER));
        dropSelf(BlockRegistry.MELON.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(b -> ((Block)b.get())).collect(Collectors.toList());
    }
}

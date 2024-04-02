package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.block.ElysianGrassBlock;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
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
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

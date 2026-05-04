package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.block.*;

public class BlockRegistry {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(IcarusWings.MOD_ID);

    public static final DeferredBlock<Block> BEESWAX_BLOCK = BLOCKS.registerBlock("beeswax_block",
            (bp) -> new Block(bp.strength(0.5f).speedFactor(1.1f).friction(0.9f).sound(SoundType.HONEY_BLOCK))
            {@Override public int getFlammability(BlockState s, BlockGetter w, BlockPos p, Direction d){return 200;}});
    public static final DeferredBlock<Block> REFINED_BEESWAX_BLOCK = BLOCKS.registerBlock("refined_beeswax_block", (bp) -> new Block(bp
            .strength(1.5f, 6.0f).speedFactor(1.2f).friction(0.8f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> REFINED_BEESWAX_SLAB = BLOCKS.registerBlock("refined_beeswax_slab",
            SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())
    );

    public static final DeferredBlock<RotatedPillarBlock> REFINED_BEESWAX_PILLAR = BLOCKS.registerBlock("refined_beeswax_pillar",
            RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())
    );

    public static final DeferredBlock<StairBlock> REFINED_BEESWAX_STAIRS = BLOCKS.registerBlock("refined_beeswax_stairs",
            (p) -> new StairBlock(REFINED_BEESWAX_BLOCK.get().defaultBlockState(), p),
            () -> BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())
    );

    public static final DeferredBlock<Block> CHISELED_REFINED_BEESWAX_BLOCK = BLOCKS.registerBlock("chiseled_refined_beeswax_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())
    );

    public static final DeferredBlock<Block> SMOOTH_REFINED_BEESWAX_BLOCK = BLOCKS.registerBlock("smooth_refined_beeswax_block",
            Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())
    );

    public static final DeferredBlock<CropBlock> FLAX_CROP = BLOCKS.registerBlock("flax",
            CropBlock::new,
            () -> BlockBehaviour.Properties.of().noCollision().randomTicks().instabreak().sound(SoundType.CROP)
    );
    public static final DeferredBlock<ElysianGrassBlock> ELYSIAN_GRASS_BLOCK = BLOCKS.registerBlock("elysian_grass_block", ElysianGrassBlock::new);
    public static final DeferredBlock<Block> ELYSIAN_SOIL = BLOCKS.registerBlock("elysian_soil",
            Block::new,
            () -> BlockBehaviour.Properties.of().strength(0.7f).sound(SoundType.GRAVEL).randomTicks()
    );
    public static final DeferredBlock<ElysianGrass> ELYSIAN_GRASS = BLOCKS.registerBlock("elysian_grass", ElysianGrass::new);
    public static final DeferredBlock<Block> GOLDEN_WOOL_BLOCK = BLOCKS.registerBlock("golden_wool_block",
            Block::new,
            () -> BlockBehaviour.Properties.of().strength(0.8f).lightLevel((p) -> 3).sound(SoundType.WOOL)
    );
    public static final DeferredBlock<CarpetBlock> GOLDEN_WOOL_CARPET = BLOCKS.registerBlock("golden_wool_carpet",
            CarpetBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(GOLDEN_WOOL_BLOCK.get())
    );

    public static final DeferredBlock<CakeBlock> HONEY_CAKE = BLOCKS.registerBlock("honey_cake",
            CakeBlock::new,
            () -> BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL).noLootTable()
    );
    public static final DeferredBlock<MeadPot> MEAD_POT = BLOCKS.registerBlock("mead_pot", MeadPot::new);
    public static final DeferredBlock<Amphora> AMPHORA = BLOCKS.registerBlock("amphora", Amphora::new);
    public static final DeferredBlock<GreekFireBlock> GREEK_FIRE = BLOCKS.registerBlock("greek_fire", GreekFireBlock::new);
    public static final DeferredBlock<MelonSphere> MELON = BLOCKS.registerBlock("melon", MelonSphere::new);
}


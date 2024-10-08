package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.block.*;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, IcarusWings.MOD_ID);

    public static final DeferredHolder<Block, Block> BEESWAX_BLOCK = BLOCKS.register("beeswax_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.5f).speedFactor(1.1f).friction(0.9f).sound(SoundType.HONEY_BLOCK))
            {@Override public int getFlammability(BlockState s, BlockGetter w, BlockPos p, Direction d){return 200;}});
    public static final DeferredHolder<Block, Block> REFINED_BEESWAX_BLOCK = BLOCKS.register("refined_beeswax_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(1.5f, 6.0f).speedFactor(1.2f).friction(0.8f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, SlabBlock> REFINED_BEESWAX_SLAB = BLOCKS.register("refined_beeswax_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())));
    public static final DeferredHolder<Block, RotatedPillarBlock> REFINED_BEESWAX_PILLAR = BLOCKS.register("refined_beeswax_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())));
    public static final DeferredHolder<Block, StairBlock> REFINED_BEESWAX_STAIRS = BLOCKS.register("refined_beeswax_stairs",
            () -> new StairBlock(REFINED_BEESWAX_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())));
    public static final DeferredHolder<Block, Block> CHISELED_REFINED_BEESWAX_BLOCK = BLOCKS.register("chiseled_refined_beeswax_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())));
    public static final DeferredHolder<Block, Block> SMOOTH_REFINED_BEESWAX_BLOCK = BLOCKS.register("smooth_refined_beeswax_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(REFINED_BEESWAX_BLOCK.get())));
    public static final DeferredHolder<Block, CropBlock> FLAX_CROP = BLOCKS.register("flax",
            () -> new CropBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    public static final DeferredHolder<Block, ElysianGrassBlock> ELYSIAN_GRASS_BLOCK = BLOCKS.register("elysian_grass_block", ElysianGrassBlock::new);
    public static final DeferredHolder<Block, Block> ELYSIAN_SOIL = BLOCKS.register("elysian_soil",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.7f).sound(SoundType.GRAVEL).randomTicks()));
    public static final DeferredHolder<Block, ElysianGrass> ELYSIAN_GRASS = BLOCKS.register("elysian_grass", ElysianGrass::new);
    public static final DeferredHolder<Block, Block> GOLDEN_WOOL_BLOCK = BLOCKS.register("golden_wool_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.8f).lightLevel((p) -> 3).sound(SoundType.WOOL)));
    public static final DeferredHolder<Block, CarpetBlock> GOLDEN_WOOL_CARPET = BLOCKS.register("golden_wool_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_WOOL_BLOCK.get())));
    public static final DeferredHolder<Block, CakeBlock> HONEY_CAKE = BLOCKS.register("honey_cake",
            () -> new CakeBlock(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL).noLootTable()));
    public static final DeferredHolder<Block, MeadPot> MEAD_POT = BLOCKS.register("mead_pot", MeadPot::new);
    public static final DeferredHolder<Block, Amphora> AMPHORA = BLOCKS.register("amphora", Amphora::new);
    public static final DeferredHolder<Block, GreekFireBlock> GREEK_FIRE = BLOCKS.register("greek_fire", GreekFireBlock::new);
    public static final DeferredHolder<Block, MelonSphere> MELON = BLOCKS.register("melon", MelonSphere::new);
}

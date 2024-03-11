package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.block.*;
import org.mineplugin.locusazzurro.icaruswings.common.block.*;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModData.MOD_ID);
    public static final RegistryObject<Block> BEESWAX_BLOCK = BLOCKS.register("beeswax_block", BeeswaxBlock::new);
    public static final RegistryObject<Block> REFINED_BEESWAX_BLOCK = BLOCKS.register("refined_beeswax_block", RefinedBeeswaxBlock::new);
    public static final RegistryObject<Block> REFINED_BEESWAX_SLAB = BLOCKS.register("refined_beeswax_slab", RefinedBeeswaxSlab::new);
    public static final RegistryObject<Block> REFINED_BEESWAX_PILLAR = BLOCKS.register("refined_beeswax_pillar", RefinedBeeswaxPillar::new);
    public static final RegistryObject<Block> REFINED_BEESWAX_STAIRS = BLOCKS.register("refined_beeswax_stairs", RefinedBeeswaxStairs::new);
    public static final RegistryObject<Block> CHISELED_REFINED_BEESWAX_BLOCK = BLOCKS.register("chiseled_refined_beeswax_block", RefinedBeeswaxBlock::new);
    public static final RegistryObject<Block> SMOOTH_REFINED_BEESWAX_BLOCK = BLOCKS.register("smooth_refined_beeswax_block", RefinedBeeswaxBlock::new);
    public static final RegistryObject<Block> FLAX_CROP = BLOCKS.register("flax", FlaxCrop::new);
    public static final RegistryObject<Block> ELYSIAN_GRASS_BLOCK = BLOCKS.register("elysian_grass_block", ElysianGrassBlock::new);
    public static final RegistryObject<Block> ELYSIAN_SOIL = BLOCKS.register("elysian_soil", ElysianSoil::new);
    public static final RegistryObject<Block> ELYSIAN_GRASS = BLOCKS.register("elysian_grass", ElysianGrass::new);
    public static final RegistryObject<Block> GOLDEN_WOOL_BLOCK = BLOCKS.register("golden_wool_block", GoldenWoolBlock::new);
    public static final RegistryObject<Block> GOLDEN_WOOL_CARPET = BLOCKS.register("golden_wool_carpet", GoldenWoolCarpet::new);
    public static final RegistryObject<Block> HONEY_CAKE = BLOCKS.register("honey_cake", HoneyCake::new);

    public static final RegistryObject<Block> MEAD_POT = BLOCKS.register("mead_pot", MeadPot::new);
    public static final RegistryObject<Block> AMPHORA = BLOCKS.register("amphora", Amphora::new);
    public static final RegistryObject<LiquidBlock> GREEK_FIRE = BLOCKS.register("greek_fire", GreekFireBlock::new);

    public static final RegistryObject<Block> MELON = BLOCKS.register("melon", MelonSphere::new);
}

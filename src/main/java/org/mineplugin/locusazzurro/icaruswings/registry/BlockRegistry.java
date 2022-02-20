package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.block.FlowingFluidBlock;
import org.mineplugin.locusazzurro.icaruswings.blocks.*;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModData.MOD_ID);
    public static final RegistryObject<Block> beeswaxBlock = BLOCKS.register("beeswax_block", BeeswaxBlock::new);
    public static final RegistryObject<Block> refinedBeeswaxBlock = BLOCKS.register("refined_beeswax_block", RefinedBeeswaxBlock::new);
    public static final RegistryObject<Block> refinedBeeswaxSlab = BLOCKS.register("refined_beeswax_slab", RefinedBeeswaxSlab::new);
    public static final RegistryObject<Block> refinedBeeswaxPillar = BLOCKS.register("refined_beeswax_pillar", RefinedBeeswaxPillar::new);
    public static final RegistryObject<Block> refinedBeeswaxStairs = BLOCKS.register("refined_beeswax_stairs", RefinedBeeswaxStairs::new);
    public static final RegistryObject<Block> refinedBeeswaxBlockChiseled = BLOCKS.register("chiseled_refined_beeswax_block", RefinedBeeswaxBlock::new);
    public static final RegistryObject<Block> smoothRefinedBeeswaxBlock = BLOCKS.register("smooth_refined_beeswax_block", RefinedBeeswaxBlock::new);
    public static final RegistryObject<Block> flaxCrop = BLOCKS.register("flax", FlaxCrop::new);
    public static final RegistryObject<Block> elysianGrassBlock = BLOCKS.register("elysian_grass_block", ElysianGrassBlock::new);
    public static final RegistryObject<Block> elysianSoil = BLOCKS.register("elysian_soil", ElysianSoil::new);
    public static final RegistryObject<Block> elysianGrass = BLOCKS.register("elysian_grass", ElysianGrass::new);

    public static final RegistryObject<Block> meadPot = BLOCKS.register("mead_pot", MeadPot::new);
    public static final RegistryObject<Block> amphora = BLOCKS.register("amphora", Amphora::new);
    public static final RegistryObject<FlowingFluidBlock> greekFire = BLOCKS.register("greek_fire", GreekFireBlock::new);

    public static final RegistryObject<Block> melon = BLOCKS.register("melon", MelonSphere::new);
}

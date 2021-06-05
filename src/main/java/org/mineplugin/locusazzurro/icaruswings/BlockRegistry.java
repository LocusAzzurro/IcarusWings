package org.mineplugin.locusazzurro.icaruswings;

import org.mineplugin.locusazzurro.icaruswings.blocks.*;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MOD_ID);
    public static final RegistryObject<Block> beeswaxBlock = BLOCKS.register("beeswax_block", BeeswaxBlock::new);
}

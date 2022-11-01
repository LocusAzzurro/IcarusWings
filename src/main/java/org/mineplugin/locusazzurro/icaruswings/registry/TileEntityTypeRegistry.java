package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.AmphoraTileEntity;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.MeadPotTileEntity;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class TileEntityTypeRegistry {
	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModData.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<MeadPotTileEntity>>
		meadPotTileEntity = TILE_ENTITIES.register(
		"mead_pot_tile_entity", () -> BlockEntityType.Builder
		.of(MeadPotTileEntity::new, BlockRegistry.meadPot.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<AmphoraTileEntity>>
		amphoraTileEntity = TILE_ENTITIES.register(
		"amphora_tile_entity", () -> BlockEntityType.Builder
		.of(AmphoraTileEntity::new, BlockRegistry.amphora.get()).build(null));
}

package org.mineplugin.locusazzurro.icaruswings.registry;

import org.mineplugin.locusazzurro.icaruswings.blocks.*;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class TileEntityTypeRegistry {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModData.MOD_ID);
	
	public static final RegistryObject<TileEntityType<MeadPotTileEntity>> 
		meadPotTileEntity = TILE_ENTITIES.register(
		"mead_pot_tile_entity", () -> TileEntityType.Builder
		.of(MeadPotTileEntity::new, BlockRegistry.meadPot.get()).build(null));
	
	public static final RegistryObject<TileEntityType<AmphoraTileEntity>> 
		amphoraTileEntity = TILE_ENTITIES.register(
		"amphora_tile_entity", () -> TileEntityType.Builder
		.of(AmphoraTileEntity::new, BlockRegistry.amphora.get()).build(null));
}

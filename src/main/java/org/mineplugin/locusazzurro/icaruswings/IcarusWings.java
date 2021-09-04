package org.mineplugin.locusazzurro.icaruswings;

import org.mineplugin.locusazzurro.icaruswings.data.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.TileEntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.Utils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class IcarusWings {
	public IcarusWings() {
	ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	TileEntityTypeRegistry.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
	SoundRegistry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
	
	}
}

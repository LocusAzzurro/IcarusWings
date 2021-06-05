package org.mineplugin.locusazzurro.icaruswings;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class IcarusWings {
	public IcarusWings() {
	ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	
	//TODO: wheat string cloth(ancient greek clothing)
	//TODO: silk clothing? in flight repair items
	//TODO: beeswax armor, floral armor, feather helmet
	
	}
}

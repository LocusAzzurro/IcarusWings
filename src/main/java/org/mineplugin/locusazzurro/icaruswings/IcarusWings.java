package org.mineplugin.locusazzurro.icaruswings;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mineplugin.locusazzurro.icaruswings.data.*;

@Mod(Utils.MOD_ID)
public class IcarusWings {
	public IcarusWings() {
		
	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
	ItemRegistry.ITEMS.register(bus);
	BlockRegistry.BLOCKS.register(bus);
	TileEntityTypeRegistry.TILE_ENTITIES.register(bus);
	SoundRegistry.SOUNDS.register(bus);
	EnchantmentRegistry.ENCHANTMENTS.register(bus);
	EffectRegistry.EFFECTS.register(bus);
	EntityTypeRegistry.ENTITIES.register(bus);

	ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.CONFIG);
	}
	
}

package org.mineplugin.locusazzurro.icaruswings;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mineplugin.locusazzurro.icaruswings.data.ModConfig;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.*;

@Mod(ModData.MOD_ID)
public class IcarusWings {
	public IcarusWings() {

		var bus = FMLJavaModLoadingContext.get().getModEventBus();
		FluidRegistry.FLUIDS.register(bus);
		FluidRegistry.FLUID_TYPES.register(bus);
		SoundRegistry.SOUNDS.register(bus);
		BlockRegistry.BLOCKS.register(bus);
		ItemRegistry.ITEMS.register(bus);
		EntityTypeRegistry.ENTITIES.register(bus);
		ParticleRegistry.PARTICLES.register(bus);
		TileEntityTypeRegistry.TILE_ENTITIES.register(bus);
		EnchantmentRegistry.ENCHANTMENTS.register(bus);
		EffectRegistry.EFFECTS.register(bus);

		ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.CONFIG);

		if (ModList.get().isLoaded("curios")) {
			isCuriosLoaded = true;
		}
	}

	public static boolean isOptifineLoaded;
	public static boolean isCuriosLoaded;

	static {
		try {
			Class.forName("optifine.Installer");
			isOptifineLoaded = true;
		}
		catch (ClassNotFoundException ignored) {}
	}
	
}

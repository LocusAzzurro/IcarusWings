package org.mineplugin.locusazzurro.icaruswings;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModConfig;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.*;

@Mod(ModData.MOD_ID)
public class IcarusWings {

	public IcarusWings(IEventBus bus) {

		FluidRegistry.FLUIDS.register(bus);
		FluidRegistry.FLUID_TYPES.register(bus);
		SoundRegistry.SOUNDS.register(bus);
		BlockRegistry.BLOCKS.register(bus);
		ItemRegistry.ITEMS.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);
		EntityTypeRegistry.ENTITIES.register(bus);
		ParticleRegistry.PARTICLES.register(bus);
		BlockEntityTypeRegistry.BLOCK_ENTITIES.register(bus);
		EnchantmentRegistry.ENCHANTMENTS.register(bus);
		EffectRegistry.EFFECTS.register(bus);

		ModLoadingContext.get().registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, ModConfig.CONFIG);

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

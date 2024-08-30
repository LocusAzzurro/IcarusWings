package org.mineplugin.locusazzurro.icaruswings;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.mineplugin.locusazzurro.icaruswings.common.data.IcarusWingsConfig;
import org.mineplugin.locusazzurro.icaruswings.datagen.DataGenerators;
import org.mineplugin.locusazzurro.icaruswings.registry.*;

@Mod(IcarusWings.MOD_ID)
public class IcarusWings {

	public static final String MOD_ID = "locusazzurro_icaruswings";

	public IcarusWings(ModContainer modContainer, IEventBus bus) {

		FluidRegistry.FLUIDS.register(bus);
		FluidRegistry.FLUID_TYPES.register(bus);
		SoundRegistry.SOUNDS.register(bus);
		BlockRegistry.BLOCKS.register(bus);
		ItemRegistry.ITEMS.register(bus);
		ArmorMaterialRegistry.ARMOR_MATERIALS.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);
		EntityTypeRegistry.ENTITIES.register(bus);
		ParticleRegistry.PARTICLES.register(bus);
		BlockEntityTypeRegistry.BLOCK_ENTITIES.register(bus);
		EffectRegistry.EFFECTS.register(bus);
		LootModifierRegistry.LOOT_MODIFIERS.register(bus);
		DataComponentRegistry.DATA_COMPONENT_TYPES.register(bus);

		bus.register(DataGenerators.class);

		modContainer.registerConfig(ModConfig.Type.COMMON, IcarusWingsConfig.CONFIG);

		CURIOS_LOADED = ModList.get().isLoaded("curios");

	}

	public static boolean OPTIFINE_LOADED;
	public static boolean CURIOS_LOADED;

	static {
		try {
			Class.forName("optifine.Installer");
			OPTIFINE_LOADED = true;
		}
		catch (ClassNotFoundException ignored) {}
	}
	
}

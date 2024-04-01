package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.entity.*;

import java.util.function.Supplier;

public class EntityTypeRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModData.MOD_ID);
	
	public static final RegistryObject<EntityType<GoldenRamEntity>> GOLDEN_RAM =
			register("golden_ram", () -> EntityType.Builder
					.of(GoldenRamEntity::new, MobCategory.CREATURE)
					.sized(0.9F, 1.3F)
					.clientTrackingRange(10)
					.build("golden_ram"));

	public static final RegistryObject<EntityType<ArtemisMissileEntity>> ARTEMIS_MISSILE =
			register("artemis_missile", () -> EntityType.Builder
					.<ArtemisMissileEntity>of(ArtemisMissileEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build("artemis_missile"));

	public static final RegistryObject<EntityType<TimeBombEntity>> TIME_BOMB =
			register("time_bomb", () -> EntityType.Builder
					.<TimeBombEntity>of(TimeBombEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build("time_bomb"));

	public static final RegistryObject<EntityType<TimeRiftParticleEntity>> TIME_RIFT_PARTICLE =
			register("time_rift_particle", () -> EntityType.Builder
					.<TimeRiftParticleEntity>of(TimeRiftParticleEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build("time_rift_particle"));

	public static final RegistryObject<EntityType<KayrosBlastEntity>> KAYROS_BLAST =
			register("kayros_energy_blast", () -> EntityType.Builder
					.<KayrosBlastEntity>of(KayrosBlastEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build("kayros_energy_blast"));

	public static final RegistryObject<EntityType<SpearEntity>> SPEAR =
			register("spear", () -> EntityType.Builder
					.<SpearEntity>of(SpearEntity::new, MobCategory.MISC)
					.sized(0.5F,0.5F)
					.build("spear"));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
		return ENTITIES.register(name, type);
	}

}

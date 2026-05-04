package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.entity.*;

import java.util.function.Supplier;

public class EntityTypeRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, IcarusWings.MOD_ID);
	
	public static final Supplier<EntityType<GoldenRamEntity>> GOLDEN_RAM =
			register("golden_ram", () -> EntityType.Builder
					.of(GoldenRamEntity::new, MobCategory.CREATURE)
					.sized(0.9F, 1.3F)
					.clientTrackingRange(10)
					.build(entityKey("golden_ram")));

	public static final Supplier<EntityType<ArtemisMissileEntity>> ARTEMIS_MISSILE =
			register("artemis_missile", () -> EntityType.Builder
					.<ArtemisMissileEntity>of(ArtemisMissileEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build(entityKey("artemis_missile")));

	public static final Supplier<EntityType<TimeBombEntity>> TIME_BOMB =
			register("time_bomb", () -> EntityType.Builder
					.<TimeBombEntity>of(TimeBombEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build(entityKey("time_bomb")));

	public static final Supplier<EntityType<TimeRiftParticleEntity>> TIME_RIFT_PARTICLE =
			register("time_rift_particle", () -> EntityType.Builder
					.<TimeRiftParticleEntity>of(TimeRiftParticleEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build(entityKey("time_rift_particle")));

	public static final Supplier<EntityType<KayrosBlastEntity>> KAYROS_BLAST =
			register("kayros_energy_blast", () -> EntityType.Builder
					.<KayrosBlastEntity>of(KayrosBlastEntity::new, MobCategory.MISC)
					.sized(0.1F, 0.1F)
					.build(entityKey("kayros_energy_blast")));

	public static final Supplier<EntityType<SpearEntity>> SPEAR =
			register("spear", () -> EntityType.Builder
					.<SpearEntity>of(SpearEntity::new, MobCategory.MISC)
					.sized(0.5F,0.5F)
					.build(entityKey("spear")));

	private static <T extends Entity> Supplier<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
		return ENTITIES.register(name, type);
	}

    private static ResourceKey<EntityType<?>> entityKey(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

}

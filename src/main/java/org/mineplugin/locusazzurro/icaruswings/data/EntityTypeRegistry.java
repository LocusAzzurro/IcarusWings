package org.mineplugin.locusazzurro.icaruswings.data;

import java.util.function.Supplier;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.entity.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Utils.MOD_ID);
	
	public static final RegistryObject<EntityType<GoldenRamEntity>> goldenRamEntity =
			register("golden_ram", () -> EntityType.Builder
					.of(GoldenRamEntity::new, EntityClassification.CREATURE)
					.sized(0.9F, 1.3F)
					.clientTrackingRange(10)
					.build("golden_ram"));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
		return ENTITIES.register(name, type);
	}

}

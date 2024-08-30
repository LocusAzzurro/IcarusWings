package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.AmphoraBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.MeadPotBlockEntity;

public class BlockEntityTypeRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, IcarusWings.MOD_ID);
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MeadPotBlockEntity>>
			MEAD_POT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"mead_pot_block_entity", () -> BlockEntityType.Builder
		.of(MeadPotBlockEntity::new, BlockRegistry.MEAD_POT.get()).build(null));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AmphoraBlockEntity>>
			AMPHORA_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"amphora_block_entity", () -> BlockEntityType.Builder
		.of(AmphoraBlockEntity::new, BlockRegistry.AMPHORA.get()).build(null));
}

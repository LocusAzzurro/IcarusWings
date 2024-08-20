package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.AmphoraBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.MeadPotBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.function.Supplier;

public class BlockEntityTypeRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ModData.MOD_ID);
	
	public static final Supplier<BlockEntityType<MeadPotBlockEntity>>
			MEAD_POT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"mead_pot_block_entity", () -> BlockEntityType.Builder
		.of(MeadPotBlockEntity::new, BlockRegistry.MEAD_POT.get()).build(null));
	
	public static final Supplier<BlockEntityType<AmphoraBlockEntity>>
			AMPHORA_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"amphora_block_entity", () -> BlockEntityType.Builder
		.of(AmphoraBlockEntity::new, BlockRegistry.AMPHORA.get()).build(null));
}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.AmphoraBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.MeadPotBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

public class BlockEntityTypeRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModData.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<MeadPotBlockEntity>>
			MEAD_POT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"mead_pot_block_entity", () -> BlockEntityType.Builder
		.of(MeadPotBlockEntity::new, BlockRegistry.MEAD_POT.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<AmphoraBlockEntity>>
			AMPHORA_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"amphora_block_entity", () -> BlockEntityType.Builder
		.of(AmphoraBlockEntity::new, BlockRegistry.AMPHORA.get()).build(null));
}

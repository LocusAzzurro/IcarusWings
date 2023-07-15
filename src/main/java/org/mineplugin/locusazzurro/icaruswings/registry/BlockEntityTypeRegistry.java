package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.AmphoraBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.MeadPotBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class BlockEntityTypeRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModData.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<MeadPotBlockEntity>>
			MEAD_POT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"mead_pot_block_entity", () -> BlockEntityType.Builder
		.of(MeadPotBlockEntity::new, BlockRegistry.meadPot.get()).build(null));
	
	public static final RegistryObject<BlockEntityType<AmphoraBlockEntity>>
			AMPHORA_BLOCK_ENTITY = BLOCK_ENTITIES.register(
		"amphora_block_entity", () -> BlockEntityType.Builder
		.of(AmphoraBlockEntity::new, BlockRegistry.amphora.get()).build(null));
}

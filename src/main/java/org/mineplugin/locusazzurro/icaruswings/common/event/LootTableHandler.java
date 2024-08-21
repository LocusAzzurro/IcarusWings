package org.mineplugin.locusazzurro.icaruswings.common.event;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.util.BindList;

import java.util.Set;

@EventBusSubscriber
public class LootTableHandler {
	
	@SubscribeEvent
	public static void onChestLoot(LootTableLoadEvent event) {
		ResourceLocation tableName = event.getName();
		LOOTREF.find(tableName).forEach((key, val) -> {event.getTable().addPool(poolFromFile(val));});
	}
	
	private static LootPool poolFromFile(ResourceLocation pool) {
		return LootPool.lootPool().add(LootTableReference.lootTableReference(pool)).build();
	}
	
	private static final BindList<ResourceLocation, ResourceLocation> LOOTREF = new BindList<>();
	
	static {
		
	LOOTREF.setPayload(ModLootTables.RELIC_CORE_CHEST).setInto(BuiltInLootTables.END_CITY_TREASURE);
	LOOTREF.setPayload(ModLootTables.RELIC_CORE_ALT).setInto(BuiltInLootTables.CAT_MORNING_GIFT);
	LOOTREF.setPayload(ModLootTables.RELIC_INTERFACE_CHEST).setInto(BuiltInLootTables.DESERT_PYRAMID, BuiltInLootTables.JUNGLE_TEMPLE);
	LOOTREF.setPayload(ModLootTables.RELIC_INTERFACE_ALT).setInto(BuiltInLootTables.ARMORER_GIFT, BuiltInLootTables.WEAPONSMITH_GIFT);
	LOOTREF.setPayload(ModLootTables.RELIC_OFFENSIVE_CHEST).setInto(BuiltInLootTables.BASTION_TREASURE, BuiltInLootTables.STRONGHOLD_LIBRARY);
	LOOTREF.setPayload(ModLootTables.RELIC_OFFENSIVE_ALT).setInto(BuiltInLootTables.PIGLIN_BARTERING);
	LOOTREF.setPayload(ModLootTables.RELIC_DEFENSIVE_CHEST).setInto(BuiltInLootTables.WOODLAND_MANSION, BuiltInLootTables.IGLOO_CHEST);
	LOOTREF.setPayload(ModLootTables.RELIC_DEFENSIVE_ALT).setInto(BuiltInLootTables.SIMPLE_DUNGEON, BuiltInLootTables.ABANDONED_MINESHAFT);
	LOOTREF.setPayload(ModLootTables.RELIC_PROPULSION_CHEST).setInto(BuiltInLootTables.SHIPWRECK_TREASURE, BuiltInLootTables.UNDERWATER_RUIN_BIG);
	LOOTREF.setPayload(ModLootTables.RELIC_PROPULSION_ALT).setInto(BuiltInLootTables.FISHERMAN_GIFT);

	}

	private static class ModLootTables{
	
		private static final Set<ResourceLocation> TABLES = Sets.newHashSet();

		private static final ResourceLocation RELIC_CORE_CHEST = register("relics/relic_core_chest"); 
		private static final ResourceLocation RELIC_CORE_ALT = register("relics/relic_core_alt"); 
		private static final ResourceLocation RELIC_INTERFACE_CHEST = register("relics/relic_interface_chest"); 
		private static final ResourceLocation RELIC_INTERFACE_ALT = register("relics/relic_interface_alt"); 
		private static final ResourceLocation RELIC_OFFENSIVE_CHEST = register("relics/relic_offensive_chest"); 
		private static final ResourceLocation RELIC_OFFENSIVE_ALT = register("relics/relic_offensive_alt"); 
		private static final ResourceLocation RELIC_DEFENSIVE_CHEST = register("relics/relic_defensive_chest"); 
		private static final ResourceLocation RELIC_DEFENSIVE_ALT = register("relics/relic_defensive_alt");
		private static final ResourceLocation RELIC_PROPULSION_CHEST = register("relics/relic_propulsion_chest");
		private static final ResourceLocation RELIC_PROPULSION_ALT = register("relics/relic_propulsion_alt"); 
		
		private static ResourceLocation register(String tableName) {
		
			ResourceLocation tableEntry = new ResourceLocation(ModData.MOD_ID, tableName);
			if (TABLES.add(tableEntry)) { return tableEntry; }
			else { throw new IllegalArgumentException(tableEntry + " is already registered."); }
		}
	}
}

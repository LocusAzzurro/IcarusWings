package org.mineplugin.locusazzurro.icaruswings.event;

import java.util.Set;

import org.mineplugin.locusazzurro.icaruswings.utils.BindList;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

import com.google.common.collect.Sets;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class LootTableHandler {
	
	@SubscribeEvent
	public static void onChestLoot(LootTableLoadEvent event) {
		ResourceLocation tableName = event.getName();
		LOOTREF.find(tableName).forEach((key, val) -> {event.getTable().addPool(poolFromFile(val));});
	}
	
	private static LootPool poolFromFile(ResourceLocation pool) {
		return LootPool.lootPool().add(TableLootEntry.lootTableReference(pool)).build();
	}
	
	private static final BindList<ResourceLocation, ResourceLocation> LOOTREF = new BindList<>();
	
	static {
		
	LOOTREF.setPayload(ModLootTables.RELIC_CORE_CHEST).setInto(LootTables.END_CITY_TREASURE);
	LOOTREF.setPayload(ModLootTables.RELIC_CORE_ALT).setInto(LootTables.CAT_MORNING_GIFT);
	LOOTREF.setPayload(ModLootTables.RELIC_INTERFACE_CHEST).setInto(LootTables.DESERT_PYRAMID, LootTables.JUNGLE_TEMPLE);
	LOOTREF.setPayload(ModLootTables.RELIC_INTERFACE_ALT).setInto(LootTables.ARMORER_GIFT, LootTables.WEAPONSMITH_GIFT);
	LOOTREF.setPayload(ModLootTables.RELIC_OFFENSIVE_CHEST).setInto(LootTables.BASTION_TREASURE, LootTables.STRONGHOLD_LIBRARY);
	LOOTREF.setPayload(ModLootTables.RELIC_OFFENSIVE_ALT).setInto(LootTables.PIGLIN_BARTERING);
	LOOTREF.setPayload(ModLootTables.RELIC_DEFENSIVE_CHEST).setInto(LootTables.WOODLAND_MANSION, LootTables.IGLOO_CHEST);
	LOOTREF.setPayload(ModLootTables.RELIC_DEFENSIVE_ALT).setInto(LootTables.SIMPLE_DUNGEON, LootTables.ABANDONED_MINESHAFT);
	LOOTREF.setPayload(ModLootTables.RELIC_PROPULSION_CHEST).setInto(LootTables.SHIPWRECK_TREASURE, LootTables.UNDERWATER_RUIN_BIG);
	LOOTREF.setPayload(ModLootTables.RELIC_PROPULSION_ALT).setInto(LootTables.FISHERMAN_GIFT);

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

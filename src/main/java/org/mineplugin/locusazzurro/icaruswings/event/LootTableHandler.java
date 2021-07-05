package org.mineplugin.locusazzurro.icaruswings.event;

import java.util.Set;

import org.mineplugin.locusazzurro.icaruswings.data.BindList;
import org.mineplugin.locusazzurro.icaruswings.data.Utils;

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

	LOOTREF.setPayload(ModLootTables.RELIC_INTERFACE_CHEST).setInto(LootTables.DESERT_PYRAMID, LootTables.JUNGLE_TEMPLE);

	}
	
	/*TODO: relic acquiring:

	 core - cat gift
	 interface - desert temple, jungle temple,armorer_gift
	 offensive - bastion,stronghold,piglin barter
	 defensive - dungeon,mineshaft,trader
	 propulsion - end city,fishing treasure
	 https://forums.minecraftforge.net/topic/76946-1144-custom-vanilla-block-drops/
	 https://mcforge.readthedocs.io/en/1.12.x/items/loot_tables/
	*/

	
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
		
			ResourceLocation tableEntry = new ResourceLocation(Utils.MOD_ID, tableName);
			if (TABLES.add(tableEntry)) { return tableEntry; }
			else { throw new IllegalArgumentException(tableEntry + " is already registered."); }
		}
	}
}

package org.mineplugin.locusazzurro.icaruswings.event;

import java.util.Set;

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
		if (tableName.equals(LootTables.SPAWN_BONUS_CHEST)) {
			event.getTable().addPool(poolFromFile(ModLootTables.STAR_TEST));
		}
	}
	
	private static LootPool poolFromFile(ResourceLocation pool) {
		return LootPool.lootPool().add(TableLootEntry.lootTableReference(pool)).build();
	}
	
	private static class ModLootTables{
	
		private static final Set<ResourceLocation> TABLES = Sets.newHashSet();
		private static final ResourceLocation STAR_TEST = register("relics/stone_test"); 
		
		private static ResourceLocation register(String tableName) {
		
			ResourceLocation tableEntry = new ResourceLocation(Utils.MOD_ID, tableName);
			if (TABLES.add(tableEntry)) { return tableEntry; }
			else { throw new IllegalArgumentException(tableEntry + " is already registered."); }
		}
	}
}

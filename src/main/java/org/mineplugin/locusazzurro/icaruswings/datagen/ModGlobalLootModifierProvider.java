package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.loot.ModAddItemLootModifier;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, IcarusWings.MOD_ID);
    }

    @Override
    protected void start() {
        add("relic_core_main", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.END_CITY_TREASURE.identifier()).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_CORE_CHEST));
        add("relic_core_alt", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.CAT_MORNING_GIFT.identifier()).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_CORE_ALT));
        add("relic_interface_main", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID.identifier()).or(LootTableIdCondition.builder(BuiltInLootTables.JUNGLE_TEMPLE.identifier())).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_INTERFACE_CHEST));
        add("relic_interface_alt", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.ARMORER_GIFT.identifier()).or(LootTableIdCondition.builder(BuiltInLootTables.WEAPONSMITH_GIFT.identifier())).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_INTERFACE_ALT));
        add("relic_offensive_main", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.BASTION_TREASURE.identifier()).or(LootTableIdCondition.builder(BuiltInLootTables.STRONGHOLD_LIBRARY.identifier())).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_OFFENSIVE_CHEST));
        add("relic_offensive_alt", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.PIGLIN_BARTERING.identifier()).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_OFFENSIVE_ALT));
        add("relic_defensive_main", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.WOODLAND_MANSION.identifier()).or(LootTableIdCondition.builder(BuiltInLootTables.IGLOO_CHEST.identifier())).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_DEFENSIVE_CHEST));
        add("relic_defensive_alt", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON.identifier()).or(LootTableIdCondition.builder(BuiltInLootTables.ABANDONED_MINESHAFT.identifier())).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_DEFENSIVE_ALT));
        add("relic_propulsion_main", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE.identifier()).or(LootTableIdCondition.builder(BuiltInLootTables.UNDERWATER_RUIN_BIG.identifier())).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_PROPULSION_CHEST));
        add("relic_propulsion_alt", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.FISHERMAN_GIFT.identifier()).build()}, IGlobalLootModifier.DEFAULT_PRIORITY, ModChestLootSubProvider.RELIC_PROPULSION_ALT));

        add("zephir_essence_bat", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/bat")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()},
                IGlobalLootModifier.DEFAULT_PRIORITY, ItemRegistry.ZEPHIR_ESSENCE.get()));
        add("zephir_essence_phantom", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/phantom")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()},
                IGlobalLootModifier.DEFAULT_PRIORITY, ItemRegistry.ZEPHIR_ESSENCE.get()));

        add("nether_essence_blaze", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/blaze")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()},
                IGlobalLootModifier.DEFAULT_PRIORITY, ItemRegistry.NETHER_ESSENCE.get()));
        add("nether_essence_wither_skeleton", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/wither_skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()},
                IGlobalLootModifier.DEFAULT_PRIORITY, ItemRegistry.NETHER_ESSENCE.get()));

        add("void_essence_shulker", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/shulker")).build(),
                LootItemRandomChanceCondition.randomChance(1.0f).build()},
                IGlobalLootModifier.DEFAULT_PRIORITY, ItemRegistry.VOID_ESSENCE.get()));
        add("void_essence_enderman", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/enderman")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()},
                IGlobalLootModifier.DEFAULT_PRIORITY, ItemRegistry.VOID_ESSENCE.get()));

    }

}


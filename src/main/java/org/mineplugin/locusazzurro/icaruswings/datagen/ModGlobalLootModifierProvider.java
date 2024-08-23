package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
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
        add("relic_core_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.END_CITY_TREASURE.location()).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.FALLEN_RELIC_CORE.get()));
        add("relic_core_alt", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.CAT_MORNING_GIFT.location()).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()},
                ItemRegistry.FALLEN_RELIC_CORE.get()));

        add("relic_interface_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID.location())
                        .or(LootTableIdCondition.builder(BuiltInLootTables.JUNGLE_TEMPLE.location())).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.FALLEN_RELIC_INTERFACE.get()));
        add("relic_interface_alt", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.ARMORER_GIFT.location())
                        .or(LootTableIdCondition.builder(BuiltInLootTables.WEAPONSMITH_GIFT.location())).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()},
                ItemRegistry.FALLEN_RELIC_INTERFACE.get()));

        add("relic_offensive_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.BASTION_TREASURE.location())
                        .or(LootTableIdCondition.builder(BuiltInLootTables.STRONGHOLD_LIBRARY.location())).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.FALLEN_RELIC_OFFENSIVE.get()));
        add("relic_offensive_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.PIGLIN_BARTERING.location()).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()},
                ItemRegistry.FALLEN_RELIC_OFFENSIVE.get()));

        add("relic_defensive_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.WOODLAND_MANSION.location())
                        .or(LootTableIdCondition.builder(BuiltInLootTables.IGLOO_CHEST.location())).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.FALLEN_RELIC_DEFENSIVE.get()));
        add("relic_defensive_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON.location())
                        .or(LootTableIdCondition.builder(BuiltInLootTables.ABANDONED_MINESHAFT.location())).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()},
                ItemRegistry.FALLEN_RELIC_DEFENSIVE.get()));

        add("relic_propulsion_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE.location())
                        .or(LootTableIdCondition.builder(BuiltInLootTables.UNDERWATER_RUIN_BIG.location())).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.FALLEN_RELIC_PROPULSION.get()));
        add("relic_propulsion_main", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.FISHERMAN_GIFT.location()).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()},
                ItemRegistry.FALLEN_RELIC_PROPULSION.get()));

        add("zephir_essence_bat", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/bat")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.ZEPHIR_ESSENCE.get()));
        add("zephir_essence_phantom", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/phantom")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.ZEPHIR_ESSENCE.get()));

        add("nether_essence_blaze", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/blaze")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()},
                ItemRegistry.NETHER_ESSENCE.get()));
        add("nether_essence_wither_skeleton", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/wither_skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemRegistry.NETHER_ESSENCE.get()));

        add("void_essence_shulker", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/shulker")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()},
                ItemRegistry.VOID_ESSENCE.get()));
        add("void_essence_enderman", new ModAddItemLootModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/enderman")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()},
                ItemRegistry.VOID_ESSENCE.get()));

    }
}

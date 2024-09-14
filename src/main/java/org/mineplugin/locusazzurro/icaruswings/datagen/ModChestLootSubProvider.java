package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.Set;
import java.util.function.BiConsumer;

public class ModChestLootSubProvider implements LootTableSubProvider {

    public static final ResourceKey<LootTable> RELIC_CORE_CHEST = relic("relic_core_chest");
    public static final ResourceKey<LootTable> RELIC_CORE_ALT = relic("relic_core_alt");
    public static final ResourceKey<LootTable> RELIC_INTERFACE_CHEST = relic("relic_interface_chest");
    public static final ResourceKey<LootTable> RELIC_INTERFACE_ALT = relic("relic_interface_alt");
    public static final ResourceKey<LootTable> RELIC_OFFENSIVE_CHEST = relic("relic_offensive_chest");
    public static final ResourceKey<LootTable> RELIC_OFFENSIVE_ALT = relic("relic_offensive_alt");
    public static final ResourceKey<LootTable> RELIC_DEFENSIVE_CHEST = relic("relic_defensive_chest");
    public static final ResourceKey<LootTable> RELIC_DEFENSIVE_ALT = relic("relic_defensive_alt");
    public static final ResourceKey<LootTable> RELIC_PROPULSION_CHEST = relic("relic_propulsion_chest");
    public static final ResourceKey<LootTable> RELIC_PROPULSION_ALT = relic("relic_propulsion_alt");

    public ModChestLootSubProvider(HolderLookup.Provider registries) {

    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        relicTables(RELIC_CORE_CHEST, RELIC_CORE_ALT, ItemRegistry.FALLEN_RELIC_CORE.get(), output);
        relicTables(RELIC_INTERFACE_CHEST, RELIC_INTERFACE_ALT, ItemRegistry.FALLEN_RELIC_INTERFACE.get(), output);
        relicTables(RELIC_OFFENSIVE_CHEST, RELIC_OFFENSIVE_ALT, ItemRegistry.FALLEN_RELIC_OFFENSIVE.get(), output);
        relicTables(RELIC_DEFENSIVE_CHEST, RELIC_DEFENSIVE_ALT, ItemRegistry.FALLEN_RELIC_DEFENSIVE.get(), output);
        relicTables(RELIC_PROPULSION_CHEST, RELIC_PROPULSION_ALT, ItemRegistry.FALLEN_RELIC_PROPULSION.get(), output);
    }

    private static void relicTables(ResourceKey<LootTable> main, ResourceKey<LootTable> alt, ItemLike relicItem, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output){
        output.accept(main, LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(relicItem).setWeight(10))
                        .add(LootItem.lootTableItem(ItemStack.EMPTY.getItem()).setWeight(90))
        ));
        output.accept(alt, LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(relicItem).setWeight(1))
                        .add(LootItem.lootTableItem(ItemStack.EMPTY.getItem()).setWeight(99))
        ));
    }

    public static ResourceKey<LootTable> relic(String name) {
        return key("relic/" + name);
    }

    public static ResourceKey<LootTable> key(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

}

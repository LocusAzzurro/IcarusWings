package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.List;
import java.util.stream.Stream;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DataGenerators.MOD_ID, existingFileHelper);
    }

    private static final List<Item> NON_SIMPLE_ITEMS = List.of(
            ItemRegistry.AMPHORA.get(),
            ItemRegistry.MEAD_POT.get(),
            ItemRegistry.FLAX_SEEDS.get(),
            ItemRegistry.HONEY_CAKE.get(),
            ItemRegistry.ELYSIAN_GRASS.get(),
            ItemRegistry.GOLDEN_RAM_SPAWN_EGG.get(),
            ItemRegistry.DEMETER.get()
    );
    private static final List<Item> HANDHELD = List.of(
            ItemRegistry.STEEL_PICKAXE.get(),
            ItemRegistry.SYNAPSE_ALLOY_SWORD.get()
    );
    private static final List<Item> BLOCK_ITEMS = getKnownItems().filter(item -> item instanceof BlockItem && !NON_SIMPLE_ITEMS.contains(item)).toList();

    @Override
    protected void registerModels() {

        getKnownItems().filter(
                item -> !BLOCK_ITEMS.contains(item) &&
                        !NON_SIMPLE_ITEMS.contains(item) &&
                        !HANDHELD.contains(item))
                .forEach(this::basicItem);
        BLOCK_ITEMS.forEach(blockItem -> withExistingParent(itemName(blockItem), modLoc("block/" + itemName(blockItem))));
        HANDHELD.forEach(item -> withExistingParent(itemName(item), mcLoc("item/handheld")).texture("layer0", modLoc("item/" + itemName(item))));

        basicItem(ItemRegistry.AMPHORA.get());
        basicItem(ItemRegistry.MEAD_POT.get());
        basicItem(ItemRegistry.FLAX_SEEDS.get());
        basicItem(ItemRegistry.HONEY_CAKE.get());
        singleTexture(itemName(ItemRegistry.ELYSIAN_GRASS.get()), mcLoc("item/generated"), "layer0", modLoc("block/elysian_grass"));
        withExistingParent(itemName(ItemRegistry.GOLDEN_RAM_SPAWN_EGG.get()), mcLoc("item/template_spawn_egg"));

    }

    private static Stream<Item> getKnownItems() {
        return ItemRegistry.ITEMS.getEntries().stream().map(DeferredHolder::get);
    }

    public String itemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

}
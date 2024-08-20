package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;

/**
 * @author DustW
 **/
public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, TagsProvider<Block> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider.contentsGetter(), DataGenerators.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {

        tag(ModTags.COLORED_FEATHERS)
                .add(ItemRegistry.RED_FEATHER.get())
                .add(ItemRegistry.BLUE_FEATHER.get())
                .add(ItemRegistry.GREEN_FEATHER.get())
                .add(ItemRegistry.CYAN_FEATHER.get())
                .add(ItemRegistry.GRAY_FEATHER.get());
        tag(ModTags.HERBS)
                .addTag(ItemTags.SMALL_FLOWERS)
                .remove(Items.WITHER_ROSE)
                .addTag(ItemTags.TALL_FLOWERS)
                .addTag(ItemTags.SAPLINGS)
                .add(Items.FERN)
                .add(Items.SEA_PICKLE)
                .add(Items.SWEET_BERRIES);
        tag(ModTags.CROP_STRINGS)
                .add(ItemRegistry.WHEAT_STRING.get())
                .add(ItemRegistry.LINEN_STRING.get());
        tag(ModTags.CROP_GRAINS)
                .add(ItemRegistry.WHEAT_GRAINS.get())
                .add(ItemRegistry.FLAX_SEEDS.get());
        tag(ModTags.WORLD_ESSENCES)
                .add(ItemRegistry.ZEPHIR_ESSENCE.get())
                .add(ItemRegistry.NETHER_ESSENCE.get())
                .add(ItemRegistry.VOID_ESSENCE.get());
        tag(ModTags.WORLD_INGOTS)
                .add(ItemRegistry.STEEL_INGOT.get())
                .add(ItemRegistry.MAGMA_INGOT.get())
                .add(ItemRegistry.PURPUR_INGOT.get());
        tag(ModTags.FALLEN_RELICS)
                .add(ItemRegistry.FALLEN_RELIC_CORE.get())
                .add(ItemRegistry.FALLEN_RELIC_OFFENSIVE.get())
                .add(ItemRegistry.FALLEN_RELIC_DEFENSIVE.get())
                .add(ItemRegistry.FALLEN_RELIC_INTERFACE.get())
                .add(ItemRegistry.FALLEN_RELIC_PROPULSION.get());
        tag(ModTags.RESTORED_FALLEN_RELICS)
                .add(ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get())
                .add(ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get())
                .add(ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get())
                .add(ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get())
                .add(ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get());
        tag(ModTags.SYNAPSE_ARMOR)
                .add(ItemRegistry.SYNAPSE_HELMET.get())
                .add(ItemRegistry.SYNAPSE_CHESTPLATE.get())
                .add(ItemRegistry.SYNAPSE_LEGGINGS.get())
                .add(ItemRegistry.SYNAPSE_BOOTS.get());
        tag(ItemTags.MUSIC_DISCS)
                .add(ItemRegistry.DISC_FALLEN_DOWN.get())
                .add(ItemRegistry.DISC_RING_MY_BELL.get());
    }

    @Override
    public String getName() {
        return DataGenerators.MOD_NAME + "Item Tags";
    }
}

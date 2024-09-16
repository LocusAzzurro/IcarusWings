package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
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

        tag(ModTags.C_STEEL_INGOT)
                .add(ItemRegistry.STEEL_INGOT.get());
        tag(Tags.Items.STRINGS)
                .add(ItemRegistry.WHEAT_STRING.get())
                .add(ItemRegistry.LINEN_STRING.get());
        tag(Tags.Items.FEATHERS)
                .addTag(ModTags.COLORED_FEATHERS);

        tag(ModTags.ELYTRA_ENCHANTABLE)
                .add(Items.ELYTRA)
                .add(ItemRegistry.FEATHER_WINGS.get())
                .add(ItemRegistry.COLORED_FEATHER_WINGS.get())
                .add(ItemRegistry.GOLDEN_FEATHER_WINGS.get())
                .add(ItemRegistry.PAPER_WINGS.get())
                .add(ItemRegistry.MAGIC_WINGS.get())
                .add(ItemRegistry.FLANDRE_MAGIC_WINGS.get())
                .add(ItemRegistry.IKAROS_WINGS.get())
                .add(ItemRegistry.NYMPH_WINGS.get())
                .add(ItemRegistry.ASTRAEA_WINGS.get())
                .add(ItemRegistry.CHAOS_WINGS.get())
                .add(ItemRegistry.HIYORI_WINGS.get())
                .add(ItemRegistry.MELAN_WINGS.get());
        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(ItemRegistry.WOODEN_SPEAR.get())
                .add(ItemRegistry.STONE_SPEAR.get())
                .add(ItemRegistry.IRON_SPEAR.get())
                .add(ItemRegistry.GOLDEN_SPEAR.get())
                .add(ItemRegistry.DIAMOND_SPEAR.get())
                .add(ItemRegistry.NETHERITE_SPEAR.get())
                .add(ItemRegistry.SYNAPSE_ALLOY_SPEAR.get());
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ItemRegistry.WOODEN_SPEAR.get())
                .add(ItemRegistry.STONE_SPEAR.get())
                .add(ItemRegistry.IRON_SPEAR.get())
                .add(ItemRegistry.GOLDEN_SPEAR.get())
                .add(ItemRegistry.DIAMOND_SPEAR.get())
                .add(ItemRegistry.NETHERITE_SPEAR.get())
                .add(ItemRegistry.SYNAPSE_ALLOY_SPEAR.get());

    }

}

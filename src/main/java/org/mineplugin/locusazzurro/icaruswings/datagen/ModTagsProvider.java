package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModEnchantments;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.DamageTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModTagsProvider {

    public static class ModItemTagsProvider extends ItemTagsProvider {

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

    public static class ModBlockTagsProvider extends BlockTagsProvider {

        public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @org.jetbrains.annotations.Nullable ExistingFileHelper helper) {
            super(output, lookupProvider, DataGenerators.MOD_ID, helper);
        }

        @Override
        protected void addTags(HolderLookup.Provider p_256380_) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(BlockRegistry.REFINED_BEESWAX_BLOCK.get())
                    .add(BlockRegistry.REFINED_BEESWAX_PILLAR.get())
                    .add(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get())
                    .add(BlockRegistry.REFINED_BEESWAX_STAIRS.get())
                    .add(BlockRegistry.REFINED_BEESWAX_SLAB.get())
                    .add(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get())
                    .add(BlockRegistry.AMPHORA.get())
                    .add(BlockRegistry.MEAD_POT.get());
            tag(BlockTags.MINEABLE_WITH_SHOVEL)
                    .add(BlockRegistry.ELYSIAN_SOIL.get())
                    .add(BlockRegistry.ELYSIAN_GRASS_BLOCK.get());
            tag(BlockTags.WOOL)
                    .add(BlockRegistry.GOLDEN_WOOL_BLOCK.get());
            tag(BlockTags.WOOL_CARPETS)
                    .add(BlockRegistry.GOLDEN_WOOL_CARPET.get());
            tag(BlockTags.NEEDS_STONE_TOOL)
                    .add(BlockRegistry.REFINED_BEESWAX_BLOCK.get())
                    .add(BlockRegistry.REFINED_BEESWAX_PILLAR.get())
                    .add(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get())
                    .add(BlockRegistry.REFINED_BEESWAX_STAIRS.get())
                    .add(BlockRegistry.REFINED_BEESWAX_SLAB.get())
                    .add(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get());
            tag(BlockTags.NEEDS_IRON_TOOL)
                    .add(BlockRegistry.MEAD_POT.get());
        }

    }

    public static class ModFluidTagsProvider extends FluidTagsProvider{


        public ModFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, provider, IcarusWings.MOD_ID, existingFileHelper);
        }
        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(FluidTags.LAVA).add(FluidRegistry.GREEK_FIRE.get(), FluidRegistry.GREEK_FIRE_FLOWING.get());
        }

    }

    public static class ModDamageTagsProvider extends DamageTypeTagsProvider {

        public ModDamageTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper fh) {
            super(output, provider, IcarusWings.MOD_ID, fh);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(DamageTypeTags.BYPASSES_ARMOR).add(DamageTypeRegistry.TIME_RIFT);
            tag(ModTags.IS_COLLISION).add(DamageTypes.FLY_INTO_WALL);
        }
    }

    public static class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {

        public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper fh) {
            super(output, provider, IcarusWings.MOD_ID, fh);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(EnchantmentTags.NON_TREASURE)
                    .add(ModEnchantments.COLLISION_PROTECTION)
                    .add(ModEnchantments.PYROTECHNIC_AFFINITY);
        }
    }
}

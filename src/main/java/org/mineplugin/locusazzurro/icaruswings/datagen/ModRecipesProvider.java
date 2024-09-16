package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(ItemRegistry.GOLD_UPGRADE_SMITHING_TEMPLATE.get()),
                Ingredient.of(Tags.Items.FEATHERS), Ingredient.of(Items.GOLD_INGOT), RecipeCategory.MISC, ItemRegistry.GOLDEN_FEATHER.get())
                .unlocks("has_template", has(ItemRegistry.GOLD_UPGRADE_SMITHING_TEMPLATE.get())).save(recipeOutput, wrapModNamespace("golden_feather_upgrade"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.FEATHER_BUNCH.get())
                .requires(Ingredient.of(Tags.Items.FEATHERS), 4).requires(Ingredient.of(Tags.Items.STRINGS))
                .unlockedBy(getHasName(Items.FEATHER), has(Tags.Items.FEATHERS)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.COLORED_FEATHER_BUNCH.get())
                .requires(Ingredient.of(ModTags.COLORED_FEATHERS), 4).requires(Ingredient.of(ModTags.CROP_STRINGS))
                .unlockedBy(getHasName(Items.FEATHER), has(ModTags.COLORED_FEATHERS)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.GOLDEN_FEATHER_BUNCH.get())
                .requires(Ingredient.of(ItemRegistry.GOLDEN_FEATHER.get()), 4).requires(Ingredient.of(ItemRegistry.GOLDEN_STRING.get()))
                .unlockedBy(getHasName(Items.FEATHER), has(ItemRegistry.GOLDEN_FEATHER.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.HERB_BUNCH.get())
                .requires(Ingredient.of(ModTags.HERBS), 4).requires(Ingredient.of(ModTags.CROP_STRINGS))
                .unlockedBy(getHasName(Items.STRING), has(ModTags.HERBS)).save(recipeOutput);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemRegistry.STEEL_MIXTURE.get()), RecipeCategory.MISC, ItemRegistry.STEEL_INGOT.get(), 0.1f, 400)
                .unlockedBy(getHasName(ItemRegistry.STEEL_MIXTURE.get()), has(ItemRegistry.STEEL_MIXTURE.get())).save(recipeOutput);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemRegistry.MAGMA_MIXTURE.get()), RecipeCategory.MISC, ItemRegistry.MAGMA_INGOT.get(), 0.2f, 400)
                .unlockedBy(getHasName(ItemRegistry.STEEL_MIXTURE.get()), has(ItemRegistry.STEEL_MIXTURE.get())).save(recipeOutput);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemRegistry.PURPUR_MIXTURE.get()), RecipeCategory.MISC, ItemRegistry.PURPUR_INGOT.get(), 0.2f, 400)
                .unlockedBy(getHasName(ItemRegistry.STEEL_MIXTURE.get()), has(ItemRegistry.STEEL_MIXTURE.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.STEEL_MIXTURE.get())
                .requires(Items.IRON_INGOT).requires(Items.COAL).requires(ItemTags.SAND).requires(Items.GUNPOWDER)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MAGMA_MIXTURE.get())
                .requires(Items.GOLD_INGOT).requires(Items.NETHER_WART).requires(Items.BASALT).requires(Items.MAGMA_CREAM)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.PURPUR_MIXTURE.get())
                .requires(Items.POPPED_CHORUS_FRUIT).requires(Items.DRAGON_BREATH).requires(Items.PHANTOM_MEMBRANE).requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.POPPED_CHORUS_FRUIT), has(Items.POPPED_CHORUS_FRUIT)).save(recipeOutput);

        essenceConverting(ItemRegistry.ZEPHIR_AIR_JAR.get(), ItemRegistry.ZEPHIR_ESSENCE.get(), recipeOutput);
        essenceConverting(ItemRegistry.NETHER_AIR_JAR.get(), ItemRegistry.NETHER_ESSENCE.get(), recipeOutput);
        essenceConverting(ItemRegistry.VOID_AIR_JAR.get(), ItemRegistry.VOID_ESSENCE.get(), recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.GOLD_UPGRADE_SMITHING_TEMPLATE.get())
                .define('G', ItemRegistry.GLISTERING_REFINED_BEESWAX_BAR.get()).define('N', Items.NETHERRACK)
                .pattern("NNN").pattern("NGN").pattern("NNN")
                .unlockedBy(getHasName(ItemRegistry.GLISTERING_REFINED_BEESWAX_BAR.get()), has(ItemRegistry.GLISTERING_REFINED_BEESWAX_BAR.get())).save(recipeOutput);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, ItemRegistry.REFORGED_NETHERITE_INGOT.get(), 0.5f, 400)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.SYNAPSE_ALLOY_INGOT.get())
                .requires(ItemRegistry.REFORGED_NETHERITE_INGOT.get()).requires(ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .requires(ItemRegistry.ZEPHIR_ESSENCE.get()).requires(ItemRegistry.NETHER_ESSENCE.get()).requires(ItemRegistry.VOID_ESSENCE.get())
                .unlockedBy(getHasName(ItemRegistry.REFORGED_NETHERITE_INGOT.get()), has(ItemRegistry.REFORGED_NETHERITE_INGOT.get())).save(recipeOutput);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.HONEYCOMB), RecipeCategory.MISC, ItemRegistry.BEESWAX.get(), 0.1f, 400)
                .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB)).save(recipeOutput, wrapModNamespace(getItemName(ItemRegistry.BEESWAX.get()) + "_from_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.HONEYCOMB), RecipeCategory.MISC, ItemRegistry.BEESWAX.get(), 0.1f, 200)
                .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB)).save(recipeOutput, wrapModNamespace(getItemName(ItemRegistry.BEESWAX.get()) + "_from_blasting"));

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegistry.BEESWAX_BLOCK.get(), ItemRegistry.BEESWAX.get());
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemRegistry.BEESWAX_BLOCK.get()), RecipeCategory.MISC, ItemRegistry.REFINED_BEESWAX_BAR.get(), 0.1f, 200)
                .unlockedBy(getHasName(ItemRegistry.BEESWAX_BLOCK.get()), has(ItemRegistry.BEESWAX_BLOCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.GLISTERING_REFINED_BEESWAX_BAR.get())
                .define('G', Items.GOLD_NUGGET).define('B', ItemRegistry.REFINED_BEESWAX_BAR.get())
                .pattern("GGG").pattern("GBG").pattern("GGG")
                .unlockedBy(getHasName(ItemRegistry.REFINED_BEESWAX_BAR.get()), has(ItemRegistry.REFINED_BEESWAX_BAR.get())).save(recipeOutput);
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegistry.REFINED_BEESWAX_BLOCK.get(), ItemRegistry.REFINED_BEESWAX_BAR.get());
        slabRecipes(ItemRegistry.REFINED_BEESWAX_BLOCK.get(), RecipeCategory.BUILDING_BLOCKS, ItemRegistry.REFINED_BEESWAX_SLAB.get(), recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.REFINED_BEESWAX_PILLAR.get(), 2)
                .define('#', ItemRegistry.REFINED_BEESWAX_BLOCK.get()).pattern("#").pattern("#")
                .unlockedBy(getHasName(ItemRegistry.REFINED_BEESWAX_BLOCK.get()), has(ItemRegistry.REFINED_BEESWAX_BLOCK.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.REFINED_BEESWAX_STAIRS.get(), 3)
                .define('#', ItemRegistry.REFINED_BEESWAX_BLOCK.get()).pattern("# ").pattern("##")
                .unlockedBy(getHasName(ItemRegistry.REFINED_BEESWAX_BLOCK.get()), has(ItemRegistry.REFINED_BEESWAX_BLOCK.get())).save(recipeOutput);
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get(), ItemRegistry.REFINED_BEESWAX_SLAB.get());
        polished(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get(), ItemRegistry.REFINED_BEESWAX_BLOCK.get());
        refinedBeeswaxRecycle(ItemRegistry.REFINED_BEESWAX_BLOCK.get(), 4, recipeOutput);
        refinedBeeswaxRecycle(ItemRegistry.REFINED_BEESWAX_SLAB.get(), 2, recipeOutput);
        refinedBeeswaxRecycle(ItemRegistry.REFINED_BEESWAX_PILLAR.get(), 4, recipeOutput);
        refinedBeeswaxRecycle(ItemRegistry.REFINED_BEESWAX_STAIRS.get(), 3, recipeOutput);
        refinedBeeswaxRecycle(ItemRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get(), 4, recipeOutput);
        refinedBeeswaxRecycle(ItemRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get(), 4, recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.GOLDEN_WOOL_BLOCK.get())
                .define('W', ItemRegistry.GOLDEN_FLEECE.get()).define('S', ItemRegistry.GOLDEN_STRING.get()).define('G', Items.GLOWSTONE_DUST)
                .pattern("SWS").pattern("WGW").pattern("SWS")
                .unlockedBy(getHasName(ItemRegistry.GOLDEN_FLEECE.get()), has(ItemRegistry.GOLDEN_FLEECE.get())).save(recipeOutput);
        carpet(recipeOutput, ItemRegistry.GOLDEN_WOOL_CARPET.get(), ItemRegistry.GOLDEN_WOOL_BLOCK.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ItemRegistry.HONEY_CAKE.get())
                .define('W', ModTags.CROP_GRAINS).define('E', Tags.Items.EGGS).define('H', Items.HONEY_BOTTLE).define('M', Items.MILK_BUCKET)
                .pattern("MMM").pattern("HEH").pattern("WWW")
                .unlockedBy(getHasName(Items.HONEY_BOTTLE), has(Items.HONEY_BOTTLE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ItemRegistry.MEAD_POT.get())
                .define('T', Items.TERRACOTTA).pattern("T T").pattern("TTT")
                .unlockedBy(getHasName(Items.TERRACOTTA), has(Items.TERRACOTTA)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ItemRegistry.AMPHORA.get())
                .define('T', Items.TERRACOTTA).pattern("T T").pattern("T T").pattern(" T ")
                .unlockedBy(getHasName(Items.TERRACOTTA), has(Items.TERRACOTTA)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ItemRegistry.GLASS_JAR.get(), 2)
                .define('P', ItemTags.PLANKS).define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                .pattern("PP").pattern("GG").pattern("GG")
                .unlockedBy(getHasName(Items.GLASS), has(Tags.Items.GLASS_BLOCKS_COLORLESS)).save(recipeOutput);
        meadInfusion(ItemRegistry.ZEPHIR_ESSENCE.get(), ItemRegistry.ZEPHIR_INFUSED_MEAD.get(), recipeOutput);
        meadInfusion(ItemRegistry.NETHER_ESSENCE.get(), ItemRegistry.NETHER_INFUSED_MEAD.get(), recipeOutput);
        meadInfusion(ItemRegistry.VOID_ESSENCE.get(), ItemRegistry.VOID_INFUSED_MEAD.get(), recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ItemRegistry.GOLDEN_APPLE_INFUSED_MEAD.get())
                .requires(ItemRegistry.MEAD.get()).requires(Items.GOLDEN_APPLE).requires(Items.GLISTERING_MELON_SLICE)
                .unlockedBy(getHasName(Items.GOLDEN_APPLE), has(Items.GOLDEN_APPLE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ItemRegistry.GOLDEN_APPLE_GROWTH_INFUSED_MEAD.get())
                .requires(ItemRegistry.GOLDEN_APPLE_INFUSED_MEAD.get()).requires(Ingredient.of(ModTags.CROP_GRAINS), 4)
                .unlockedBy(getHasName(ItemRegistry.GOLDEN_APPLE_INFUSED_MEAD.get()), has(ItemRegistry.GOLDEN_APPLE_INFUSED_MEAD.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, ItemRegistry.HERBS_INFUSED_MEAD.get())
                .requires(ItemRegistry.MEAD.get()).requires(ItemRegistry.HERB_BUNCH.get(), 2)
                .unlockedBy(getHasName(ItemRegistry.HERB_BUNCH.get()), has(ItemRegistry.HERB_BUNCH.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemRegistry.MAGICAL_ANEMONE.get())
                .requires(ItemRegistry.ANEMONE.get()).requires(ModTags.WORLD_ESSENCES)
                .unlockedBy(getHasName(ItemRegistry.ANEMONE.get()), has(ItemRegistry.ANEMONE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.WIND_WAND.get())
                .define('A', ItemRegistry.ANEMONE.get()).define('S', Items.STICK)
                .define('Z', ItemRegistry.ZEPHIR_ESSENCE.get()).define('N', ItemRegistry.NETHER_ESSENCE.get()).define('V', ItemRegistry.VOID_ESSENCE.get())
                .pattern("  A").pattern("NS ").pattern("ZV ")
                .unlockedBy(getHasName(ItemRegistry.ANEMONE.get()), has(ItemRegistry.ANEMONE.get())).save(recipeOutput);

        stoneCutter(recipeOutput, RecipeCategory.MISC, ItemRegistry.WHEAT_GRAINS.get(), Items.WHEAT, 2);
        stoneCutter(recipeOutput, RecipeCategory.MISC, ItemRegistry.WHEAT_STRING.get(), Items.WHEAT, 2);
        stoneCutter(recipeOutput, RecipeCategory.MISC, ItemRegistry.FLAX_SEEDS.get(), ItemRegistry.FLAX.get(), 2);
        stoneCutter(recipeOutput, RecipeCategory.MISC, ItemRegistry.LINEN_STRING.get(), ItemRegistry.FLAX.get(), 2);
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemRegistry.GOLD_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Tags.Items.STRINGS), Ingredient.of(Items.GOLD_INGOT), RecipeCategory.MISC, ItemRegistry.GOLDEN_STRING.get())
                .unlocks("has_template", has(ItemRegistry.GOLD_UPGRADE_SMITHING_TEMPLATE.get())).save(recipeOutput, wrapModNamespace("golden_string_upgrade"));
        threeByThreePacker(recipeOutput, RecipeCategory.MISC, ItemRegistry.LINEN.get(), ItemRegistry.LINEN_STRING.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ItemRegistry.GRAINS_MEAL.get())
                .requires(Items.BOWL).requires(Ingredient.of(ModTags.CROP_GRAINS), 2)
                .unlockedBy("has_grains", has(ModTags.CROP_GRAINS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.HERBAL_CROWN.get())
                .define('H', ModTags.HERBS).pattern("HHH").pattern("H H")
                .unlockedBy("has_herb", has(ModTags.HERBS)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.FEATHER_HELMET.get())
                .define('F', ItemRegistry.FEATHER_BUNCH.get()).define('L', Items.LEATHER).pattern("F").pattern("L")
                .unlockedBy(getHasName(ItemRegistry.FEATHER_BUNCH.get()), has(ItemRegistry.FEATHER_BUNCH.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLDEN_FEATHER_HELMET.get())
                .define('F', ItemRegistry.GOLDEN_FEATHER_BUNCH.get()).define('L', Items.LEATHER).pattern("F").pattern("L")
                .unlockedBy(getHasName(ItemRegistry.GOLDEN_FEATHER_BUNCH.get()), has(ItemRegistry.GOLDEN_FEATHER_BUNCH.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.LINEN_CLOAK.get())
                .define('L', ItemRegistry.LINEN.get()).pattern("L L").pattern("LLL").pattern("LLL")
                .unlockedBy(getHasName(ItemRegistry.LINEN.get()), has(ItemRegistry.LINEN.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.LINEN_UNDERGARMENT.get())
                .define('L', ItemRegistry.LINEN.get()).pattern("LLL").pattern("L L").pattern("L L")
                .unlockedBy(getHasName(ItemRegistry.LINEN.get()), has(ItemRegistry.LINEN.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLDEN_FLEECE_MANTLE.get())
                .define('F', ItemRegistry.GOLDEN_FLEECE.get()).define('S', ItemRegistry.GOLDEN_STRING.get())
                .pattern("F F").pattern("SFS")
                .unlockedBy(getHasName(ItemRegistry.GOLDEN_FLEECE.get()), has(ItemRegistry.GOLDEN_FLEECE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.BEESWAX_BOOTS.get())
                .define('W', ItemRegistry.REFINED_BEESWAX_BAR.get()).pattern("W W").pattern("W W")
                .unlockedBy(getHasName(ItemRegistry.REFINED_BEESWAX_BAR.get()), has(ItemRegistry.REFINED_BEESWAX_BAR.get())).save(recipeOutput);

        wings(ItemRegistry.FEATHER_WINGS.get(), ItemRegistry.FEATHER_BUNCH.get(), ItemRegistry.BEESWAX.get(), recipeOutput);
        wings(ItemRegistry.COLORED_FEATHER_WINGS.get(), ItemRegistry.COLORED_FEATHER_BUNCH.get(), ItemRegistry.REFINED_BEESWAX_BAR.get(), recipeOutput);
        wings(ItemRegistry.GOLDEN_FEATHER_WINGS.get(), ItemRegistry.GOLDEN_FEATHER_BUNCH.get(), ItemRegistry.GLISTERING_REFINED_BEESWAX_BAR.get(), recipeOutput);
        wings(ItemRegistry.PAPER_WINGS.get(), Items.PAPER, Items.STICK, recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MAGIC_MEMBRANE.get())
                .requires(Items.PHANTOM_MEMBRANE).requires(ModTags.WORLD_ESSENCES).requires(ItemRegistry.PHILOSOPHER_STONE.get())
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.PHILOSOPHER_STONE.get())
                .define('#', ModTags.WORLD_ESSENCES).define('D', Items.DIAMOND)
                .pattern("###").pattern("#D#").pattern("###")
                .unlockedBy("has_essence", has(ModTags.WORLD_ESSENCES)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemRegistry.MAGIC_WINGS.get())
                .requires(Items.ELYTRA).requires(ModTags.WORLD_ESSENCES).requires(ItemRegistry.PHILOSOPHER_STONE.get())
                .unlockedBy(getHasName(Items.ELYTRA), has(Items.ELYTRA)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.FLANDRE_MAGIC_WINGS.get())
                .define('W', ItemRegistry.MAGIC_WINGS.get())
                .define('1', Items.REDSTONE_BLOCK)
                .define('2', Items.COPPER_BLOCK)
                .define('3', Items.GOLD_BLOCK)
                .define('4', Items.EMERALD_BLOCK)
                .define('5', Items.DIAMOND_BLOCK)
                .define('6', Items.LAPIS_BLOCK)
                .define('7', Items.AMETHYST_BLOCK)
                .define('S', ItemRegistry.PHILOSOPHER_STONE.get())
                .pattern("345").pattern("2W6").pattern("1S7")
                .unlockedBy(getHasName(ItemRegistry.MAGIC_WINGS.get()), has(ItemRegistry.MAGIC_WINGS.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemRegistry.DAEDALUS_MANUSCRIPT.get())
                .requires(Items.PAPER).requires(Tags.Items.FEATHERS).requires(Items.INK_SAC)
                .unlockedBy(getHasName(Items.FEATHER), has(Tags.Items.FEATHERS)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .requires(ItemRegistry.DAEDALUS_MANUSCRIPT.get()).requires(ModTags.FALLEN_RELICS)
                .unlockedBy("has_relic", has(ModTags.FALLEN_RELICS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get())
                .define('R', ItemRegistry.FALLEN_RELIC_CORE.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('M', ItemRegistry.MAGMA_INGOT.get()).define('P', ItemRegistry.PURPUR_INGOT.get())
                .define('N', ItemRegistry.REFORGED_NETHERITE_INGOT.get()).define('F', ItemRegistry.GOLDEN_FEATHER.get())
                .pattern("FPF").pattern("SRM").pattern("NDN")
                .unlockedBy(getHasName(ItemRegistry.FALLEN_RELIC_CORE.get()), has(ItemRegistry.FALLEN_RELIC_CORE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get())
                .define('R', ItemRegistry.FALLEN_RELIC_INTERFACE.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('P', ItemRegistry.PURPUR_INGOT.get())
                .pattern(" P ").pattern("SRS").pattern(" D ")
                .unlockedBy(getHasName(ItemRegistry.FALLEN_RELIC_INTERFACE.get()), has(ItemRegistry.FALLEN_RELIC_INTERFACE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get())
                .define('R', ItemRegistry.FALLEN_RELIC_OFFENSIVE.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('M', ItemRegistry.MAGMA_INGOT.get())
                .pattern(" S ").pattern("MRM").pattern(" D ")
                .unlockedBy(getHasName(ItemRegistry.FALLEN_RELIC_OFFENSIVE.get()), has(ItemRegistry.FALLEN_RELIC_OFFENSIVE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get())
                .define('R', ItemRegistry.FALLEN_RELIC_DEFENSIVE.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('M', ItemRegistry.MAGMA_INGOT.get())
                .pattern(" M ").pattern("SRS").pattern(" D ")
                .unlockedBy(getHasName(ItemRegistry.FALLEN_RELIC_DEFENSIVE.get()), has(ItemRegistry.FALLEN_RELIC_DEFENSIVE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get())
                .define('R', ItemRegistry.FALLEN_RELIC_PROPULSION.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('M', ItemRegistry.MAGMA_INGOT.get()).define('P', ItemRegistry.PURPUR_INGOT.get())
                .pattern(" M ").pattern("PRP").pattern(" D ")
                .unlockedBy(getHasName(ItemRegistry.FALLEN_RELIC_PROPULSION.get()), has(ItemRegistry.FALLEN_RELIC_PROPULSION.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.UPGRADED_FALLEN_RELIC_CORE.get())
                .define('R', ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get()).define('F', ItemRegistry.QUANTUM_FUEL.get())
                .pattern("FAF").pattern("ARA").pattern(" D ")
                .unlockedBy(getHasName(ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get()), has(ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemRegistry.SYNAPSE_REPAIR_KIT.get())
                .requires(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(ModTags.FALLEN_RELICS), new Ingredient.ItemValue(ItemRegistry.REFORGED_NETHERITE_INGOT.get().getDefaultInstance()))))
                .requires(Ingredient.of(ModTags.WORLD_ESSENCES)).requires(ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .unlockedBy("has_synapse_armor", has(ModTags.SYNAPSE_ARMOR)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SYNAPSE_WINGS_RECHARGER.get())
                .define('R', ItemRegistry.SYNAPSE_REPAIR_KIT.get()).define('F', ItemRegistry.QUANTUM_FUEL.get())
                .pattern("FRF")
                .unlockedBy(getHasName(ItemRegistry.SYNAPSE_REPAIR_KIT.get()), has(ItemRegistry.SYNAPSE_REPAIR_KIT.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.QUANTUM_FUEL.get())
                .define('S', Ingredient.fromValues(Stream.of(new Ingredient.TagValue(ModTags.C_STEEL_INGOT), new Ingredient.ItemValue(ItemRegistry.STEEL_INGOT.get().getDefaultInstance()))))
                .define('N', Items.NETHER_STAR)
                .pattern(" S ").pattern("SNS").pattern(" S ")
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.SYNAPSE_HELMET.get())
                .define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get()).define('R', ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get())
                .pattern("ARA").pattern("A A")
                .unlockedBy(getHasName(ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get()), has(ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.SYNAPSE_CHESTPLATE.get())
                .define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get()).define('R', ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get())
                .pattern("A A").pattern("ARA").pattern("AAA")
                .unlockedBy(getHasName(ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get()), has(ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.SYNAPSE_LEGGINGS.get())
                .define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get()).define('R', ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get())
                .pattern("ARA").pattern("A A").pattern("A A")
                .unlockedBy(getHasName(ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get()), has(ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.SYNAPSE_BOOTS.get())
                .define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get()).define('R', ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get())
                .pattern("A A").pattern("ARA")
                .unlockedBy(getHasName(ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get()), has(ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.SYNAPSE_ALLOY_SWORD.get())
                .define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get()).define('R', Items.BREEZE_ROD)
                .pattern("  A").pattern(" A ").pattern("R  ")
                .unlockedBy(getHasName(ItemRegistry.SYNAPSE_ALLOY_INGOT.get()), has(ItemRegistry.SYNAPSE_ALLOY_INGOT.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.STEEL_PICKAXE.get())
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('s', Items.STICK)
                .pattern("SSS").pattern(" s ").pattern(" s ")
                .unlockedBy(getHasName(ItemRegistry.STEEL_INGOT.get()), has(ItemRegistry.STEEL_INGOT.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.WOODEN_SPEAR.get())
                .define('P', ItemTags.LOGS).define('T', ItemTags.PLANKS)
                .pattern("  T").pattern(" P ").pattern("P  ")
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_SPEAR.get())
                .define('P', ItemTags.LOGS).define('T', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("  T").pattern(" P ").pattern("P  ")
                .unlockedBy("has_stone", has(ItemTags.STONE_TOOL_MATERIALS)).save(recipeOutput);
        spear(Items.IRON_INGOT, ItemRegistry.IRON_SPEAR.get(), recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STEEL_SPEAR.get())
                .define('P', ItemTags.LOGS).define('T', Ingredient.fromValues(Stream.of(new Ingredient.TagValue(ModTags.C_STEEL_INGOT), new Ingredient.ItemValue(ItemRegistry.STEEL_INGOT.get().getDefaultInstance()))))
                .pattern("  T").pattern(" P ").pattern("P  ")
                .unlockedBy("has_steel", has(ModTags.C_STEEL_INGOT)).save(recipeOutput);
        spear(Items.GOLD_INGOT, ItemRegistry.GOLDEN_SPEAR.get(), recipeOutput);
        spear(Items.DIAMOND, ItemRegistry.DIAMOND_SPEAR.get(), recipeOutput);
        spear(Items.NETHERITE_INGOT, ItemRegistry.NETHERITE_SPEAR.get(), recipeOutput);
        spear(ItemRegistry.SYNAPSE_ALLOY_INGOT.get(), ItemRegistry.SYNAPSE_ALLOY_SPEAR.get(), recipeOutput);

        synapseWings(ItemRegistry.IKAROS_WINGS.get(), ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get(),
                recipeOutput);
        synapseWings(ItemRegistry.NYMPH_WINGS.get(), ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get(),
                recipeOutput);
        synapseWings(ItemRegistry.ASTRAEA_WINGS.get(), ItemRegistry.RESTORED_FALLEN_RELIC_CORE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get(), ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get(),
                recipeOutput);

        synapseWings(ItemRegistry.MELAN_WINGS.get(), ItemRegistry.UPGRADED_FALLEN_RELIC_CORE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get(),
                recipeOutput);
        synapseWings(ItemRegistry.CHAOS_WINGS.get(), ItemRegistry.UPGRADED_FALLEN_RELIC_CORE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_PROPULSION.get(),
                recipeOutput);
        synapseWings(ItemRegistry.HIYORI_WINGS.get(), ItemRegistry.UPGRADED_FALLEN_RELIC_CORE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get(),
                ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get(), ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get(),
                recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.ARTEMIS_LAUNCHER.get())
                .define('R', ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get()).define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('C', Items.CROSSBOW)
                .pattern(" R ").pattern("SCS").pattern(" D ")
                .unlockedBy(getHasName(ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get()), has(ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.ARTEMIS_MISSILE.get(), 4)
                .define('S', ItemRegistry.STEEL_INGOT.get()).define('T', Items.TNT)
                .pattern(" S ").pattern("STS").pattern(" S ")
                .unlockedBy(getHasName(ItemRegistry.ARTEMIS_LAUNCHER.get()), has(ItemRegistry.ARTEMIS_LAUNCHER.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.TIME_RIFT_GENERATOR.get())
                .define('C', Items.CLOCK).define('S', Items.BREEZE_ROD).define('I', ItemRegistry.PURPUR_INGOT.get())
                .pattern("  C").pattern(" S ").pattern("I  ")
                .unlockedBy(getHasName(ItemRegistry.HIYORI_WINGS.get()), has(ItemRegistry.HIYORI_WINGS.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.TIME_RIFT_CHARGE.get(), 4)
                .define('I', ItemRegistry.PURPUR_INGOT.get()).define('Q', ItemRegistry.QUANTUM_FUEL.get())
                .pattern(" I ").pattern("IQI").pattern(" I ")
                .unlockedBy(getHasName(ItemRegistry.TIME_RIFT_GENERATOR.get()), has(ItemRegistry.TIME_RIFT_GENERATOR.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DEMETER.get())
                .define('G', ItemRegistry.TIME_RIFT_GENERATOR.get()).define('R', ItemRegistry.SYNAPSE_REPAIR_KIT.get()).define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get())
                .define('I', ItemRegistry.RESTORED_FALLEN_RELIC_INTERFACE.get()).define('O', ItemRegistry.RESTORED_FALLEN_RELIC_OFFENSIVE.get()).define('D', ItemRegistry.RESTORED_FALLEN_RELIC_DEFENSIVE.get())
                .pattern(" IA").pattern("OGD").pattern("AR ")
                .unlockedBy(getHasName(ItemRegistry.TIME_RIFT_GENERATOR.get()), has(ItemRegistry.TIME_RIFT_GENERATOR.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DEMETER_CHARGE.get(), 2)
                .define('C', ItemRegistry.TIME_RIFT_CHARGE.get()).define('V', ItemRegistry.VOID_ESSENCE.get())
                .pattern(" C ").pattern("CVC").pattern(" C ")
                .unlockedBy(getHasName(ItemRegistry.DEMETER.get()), has(ItemRegistry.DEMETER.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_BASE.get(), 16)
                .define('D', ItemRegistry.DAEDALUS_DECRYPTOR.get()).define('Q', ItemRegistry.QUANTUM_FUEL.get())
                .define('P', Items.PAPER).define('R', Items.REDSTONE_BLOCK)
                .pattern("PRP").pattern("PQP").pattern("PDP")
                .unlockedBy(getHasName(ItemRegistry.QUANTUM_FUEL.get()), has(ItemRegistry.QUANTUM_FUEL.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_ARTEMIS_HOMING.get(), 4)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get()).define('A', ItemRegistry.ARTEMIS_MISSILE.get())
                .define('T', Items.REDSTONE_TORCH).define('M', Items.COMPARATOR)
                .pattern("AMA").pattern("ACA").pattern("ATA")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_ARTEMIS_SCATTER.get(), 4)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get()).define('A', ItemRegistry.ARTEMIS_MISSILE.get())
                .define('T', Items.REDSTONE_TORCH).define('M', Items.REPEATER)
                .pattern("AMA").pattern("ACA").pattern("ATA")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_CHRONO_EXPLOSION.get(), 2)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get()).define('P', ItemRegistry.TIME_RIFT_CHARGE.get())
                .define('Q', ItemRegistry.QUANTUM_FUEL.get())
                .pattern("PQP").pattern("PCP").pattern("PQP")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_CHRONO_TELEPORT.get(), 4)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get()).define('E', Items.ENDER_PEARL)
                .define('Q', ItemRegistry.QUANTUM_FUEL.get())
                .pattern("EQE").pattern("ECE").pattern("EQE")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_MASKING_FIELD.get(), 1)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get())
                .define('G', Items.GOLDEN_CARROT).define('W', Items.NETHER_WART).define('E', Items.SPIDER_EYE).define('R', Items.COMPARATOR)
                .pattern("ERE").pattern("GCG").pattern("WRW")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_INTERDICTION_FIELD.get(), 1)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get())
                .define('W', Items.COBWEB).define('R', Items.COMPARATOR)
                .pattern("WRW").pattern("WCW").pattern("WRW")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.TRANSPORT_CARD_TREASURE.get(), 1)
                .define('C', ItemRegistry.TRANSPORT_CARD_BASE.get())
                .define('G', Items.GOLD_NUGGET).define('X', Items.CHEST)
                .pattern(" X ").pattern("GCG").pattern(" X ")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_BASE.get()), has(ItemRegistry.TRANSPORT_CARD_BASE.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, new ItemStack(
                ItemRegistry.TRANSPORT_CARD_TREASURE, 1, DataComponentPatch.builder().set(
                        DataComponents.CONTAINER_LOOT, new SeededContainerLoot(ModChestLootSubProvider.TREASURE_CARD_RELICS, 0L)).build()))
                .define('C', ItemRegistry.TRANSPORT_CARD_TREASURE.get()).define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get())
                .define('G', Items.GOLD_INGOT).define('X', Items.CHEST)
                .pattern("GXG").pattern("ACA").pattern("GXG")
                .unlockedBy(getHasName(ItemRegistry.TRANSPORT_CARD_TREASURE.get()), has(ItemRegistry.TRANSPORT_CARD_TREASURE.get()))
                .save(recipeOutput, wrapModNamespace(getItemName(ItemRegistry.TRANSPORT_CARD_TREASURE.get()) + "_synapse_relics"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.MUSIC_DISC_13), RecipeCategory.MISC, ItemRegistry.DISC_FALLEN_DOWN.get(), 0.1f, 200)
                .unlockedBy(getHasName(Items.MUSIC_DISC_13), has(Items.MUSIC_DISC_13)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.MUSIC_DISC_CAT), RecipeCategory.MISC, ItemRegistry.DISC_RING_MY_BELL.get(), 0.1f, 200)
                .unlockedBy(getHasName(Items.MUSIC_DISC_CAT), has(Items.MUSIC_DISC_CAT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ItemRegistry.MELON.get())
                .requires(Items.MELON).requires(ItemRegistry.DAEDALUS_DECRYPTOR.get())
                .unlockedBy(getHasName(ItemRegistry.DAEDALUS_DECRYPTOR.get()), has(ItemRegistry.DAEDALUS_DECRYPTOR.get()))
                .save(recipeOutput);
    }

    private static void essenceConverting(ItemLike air, ItemLike essence, RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, essence).define('A', air).define('S', ItemRegistry.PHILOSOPHER_STONE.get())
                .pattern("AAA").pattern("ASA").pattern("AAA")
                .unlockedBy(getHasName(air), has(air)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, air, 4).define('E', essence).define('B', ItemRegistry.GLASS_JAR.get())
                .pattern(" B ").pattern("BEB").pattern(" B ")
                .unlockedBy(getHasName(essence), has(essence)).save(recipeOutput);
    }

    private static void slabRecipes(ItemLike block, RecipeCategory recipeCategory, ItemLike slab, RecipeOutput recipeOutput){
        slabBuilder(recipeCategory, slab, Ingredient.of(block)).unlockedBy(getHasName(block), has(block)).save(recipeOutput);
        stoneCutter(recipeOutput, recipeCategory, slab, block, 2);
    }

    private static void refinedBeeswaxRecycle(ItemLike block, int bars, RecipeOutput recipeOutput){
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.MISC, ItemRegistry.REFINED_BEESWAX_BAR.get(), bars)
                .unlockedBy(getHasName(block), has(block)).save(recipeOutput, wrapModNamespace("refined_beeswax_bar_recycle_from_" + getItemName(block)));
    }

    private static void meadInfusion(ItemLike essence, ItemLike infused, RecipeOutput recipeOutput){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, infused).requires(ItemRegistry.MEAD.get()).requires(essence, 2)
                .unlockedBy(getHasName(essence), has(essence)).save(recipeOutput);
    }

    private static void wings(ItemLike result, ItemLike wing, ItemLike bind, RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('F', wing).define('W', bind).define('P', ItemTags.PLANKS)
                .pattern("WPW").pattern("F F").pattern("F F")
                .unlockedBy(getHasName(wing), has(wing)).save(recipeOutput);
    }

    private static void synapseWings(ItemLike result, ItemLike core, ItemLike l1, ItemLike r1, ItemLike l2, ItemLike r2, RecipeOutput output){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('E', Items.ELYTRA).define('A', ItemRegistry.SYNAPSE_ALLOY_INGOT.get())
                .define('C', core).define('R', ItemRegistry.SYNAPSE_WINGS_RECHARGER.get())
                .define('1', l1).define('2', r1).define('3', l2).define('4', r2)
                .pattern("ACA").pattern("1E2").pattern("3R4")
                .unlockedBy(getHasName(core), has(core)).save(output);
    }

    private static void stoneCutter(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int count){
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, count).unlockedBy(getHasName(material), has(material)).save(recipeOutput, wrapModNamespace(getItemName(result) + "_stonecutting"));
    }

    private static void spear(ItemLike material, ItemLike spear, RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, spear)
                .define('P', ItemTags.LOGS).define('T', material)
                .pattern("  T").pattern(" P ").pattern("P  ")
                .unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }

    private static ResourceLocation wrapModNamespace(String name){
        return ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name);
    }


}

package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class RecipesGenerator extends RecipeProvider {

    public RecipesGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //合成表，你自己看看，unlockedBy 是必填项
        //ShapedRecipeBuilder
        //        .shaped(result item)
        //        .define('R', )
        //        .define('B', )
        //        .define('G', )
        //        .define('Y', )
        //        .define('C', )
        //        .pattern("RRG")
        //        .pattern("YCG")
        //        .pattern("YBB")
        //        .unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);
    }
}

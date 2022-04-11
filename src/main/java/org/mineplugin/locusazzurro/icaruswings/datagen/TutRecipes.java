package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // todo 合成表，你自己看看，unlockedBy 是必填项
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

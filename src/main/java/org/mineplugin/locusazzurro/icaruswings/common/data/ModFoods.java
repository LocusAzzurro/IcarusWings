package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BEESWAX = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.0F).build();
    public static final FoodProperties MEAD = (new FoodProperties.Builder()).nutrition(5).saturationModifier(1.0F).build();
    public static final FoodProperties WHEAT_GRAINS = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.0F).build();
    public static final FoodProperties GRAINS_MEAL =  (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).build();
    public static final FoodProperties PHILOSOPHER_STONE =  (new FoodProperties.Builder()).nutrition(10).saturationModifier(1F).alwaysEdible().build();

}

package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;

public class GrainsMeal extends BowlFoodItem {

    public GrainsMeal() {
        super(new Properties().food(food));
    }

    private static final FoodProperties food = (new FoodProperties.Builder())
            .saturationMod(3)
            .nutrition(5)
            .build();
}

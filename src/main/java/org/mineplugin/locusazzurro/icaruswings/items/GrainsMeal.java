package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.world.item.Item.Properties;

public class GrainsMeal extends BowlFoodItem {

    public GrainsMeal() {
        super(new Properties().tab(ModGroup.itemGroup).food(food));
    }

    private static final FoodProperties food = (new FoodProperties.Builder())
            .saturationMod(3)
            .nutrition(5)
            .build();
}

package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class GrainsMeal extends Item {

    public GrainsMeal() {
        super(new Item.Properties().stacksTo(1).food(FOOD_PROPERTIES));
    }
    private static final FoodProperties FOOD_PROPERTIES = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).usingConvertsTo(Items.BOWL).build();

}

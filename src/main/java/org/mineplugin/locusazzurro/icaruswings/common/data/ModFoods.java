package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoods {

    public static final FoodProperties BEESWAX = (new FoodProperties.Builder()).nutrition(1).saturationModifier(0.0F).build();
    public static final FoodProperties MEAD = (new FoodProperties.Builder()).nutrition(5).saturationModifier(1.0F).build();
    public static final FoodProperties WHEAT_GRAINS = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.0F).build();
    public static final FoodProperties GRAINS_MEAL =  (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.6F).usingConvertsTo(Items.BOWL).build();
    public static final FoodProperties PHILOSOPHER_STONE =  (new FoodProperties.Builder()).nutrition(10).saturationModifier(1F).alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2), 1)
            .build();

}

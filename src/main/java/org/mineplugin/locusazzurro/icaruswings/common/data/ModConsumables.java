package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {
    public static Consumable PHILOSOPHER_STONE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(MobEffects.REGENERATION, 400, 1),
                            new MobEffectInstance(MobEffects.RESISTANCE, 1200, 1),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2)
                    )
            )).build();
}

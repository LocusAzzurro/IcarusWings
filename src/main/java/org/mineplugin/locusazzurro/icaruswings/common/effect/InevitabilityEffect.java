package org.mineplugin.locusazzurro.icaruswings.common.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModDamageSources;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

public class InevitabilityEffect extends MobEffect {

    private static final int MAX_LEVEL = 9;
    private static final int TIMEOUT_DURATION = 400;
    private static final int OVERFLOW_DAMAGE_PER_LEVEL = 50;

    public InevitabilityEffect() {
        super(MobEffectCategory.HARMFUL, 0x550066);
    }

    @SuppressWarnings("ConstantConditions")
    public static void addEffect(LivingEntity livingEntity, int amplifier, int duration){
        if (!livingEntity.hasEffect(EffectRegistry.INEVITABILITY)){
            livingEntity.addEffect(new MobEffectInstance(EffectRegistry.INEVITABILITY, duration, amplifier));
        }
        else {
            int existingAmplifier = livingEntity.getEffect(EffectRegistry.INEVITABILITY).getAmplifier();
            int amplifierSum = existingAmplifier + (amplifier == 0 ? 1 : amplifier);
            if (amplifierSum <= MAX_LEVEL)
                livingEntity.addEffect(new MobEffectInstance(EffectRegistry.INEVITABILITY, duration, amplifierSum));
            else {
                livingEntity.hurt(ModDamageSources.timeRift(livingEntity.level()), (amplifierSum - MAX_LEVEL) * OVERFLOW_DAMAGE_PER_LEVEL);
                livingEntity.addEffect(new MobEffectInstance(EffectRegistry.INEVITABILITY, duration, MAX_LEVEL));
            }
        }
    }

    public static void addEffect(LivingEntity livingEntity, int amplifier){
        addEffect(livingEntity, amplifier, TIMEOUT_DURATION);
    }

}

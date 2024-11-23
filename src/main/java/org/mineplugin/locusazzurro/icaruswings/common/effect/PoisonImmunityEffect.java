package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

@EventBusSubscriber
public class PoisonImmunityEffect extends MobEffect {

    public PoisonImmunityEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe4fbbd);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);
        livingEntity.removeEffect(MobEffects.POISON);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onPotionApplication(MobEffectEvent.Applicable event) {
        if (!event.getEntity().level().isClientSide()){
            MobEffectInstance effectInstance = event.getEffectInstance();
            if (effectInstance != null && effectInstance.is(MobEffects.POISON) &&
                    event.getEntity().hasEffect(EffectRegistry.POISON_IMMUNITY)){
                event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
            }
        }
    }



}

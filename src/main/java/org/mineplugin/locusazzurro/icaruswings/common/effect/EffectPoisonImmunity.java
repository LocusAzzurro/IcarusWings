package org.mineplugin.locusazzurro.icaruswings.common.effect;


import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event.Result;

import javax.annotation.Nullable;

public class EffectPoisonImmunity extends AbstractEffect{

	public EffectPoisonImmunity() {
		super(MobEffectCategory.BENEFICIAL, 0xe4fbbd);
	}
	
	@Override
	public void onAdded(LivingEntity entity, MobEffectInstance effect, @Nullable MobEffectInstance oldEffect) {
		entity.removeEffect(MobEffects.POISON);
	}
	
	
}

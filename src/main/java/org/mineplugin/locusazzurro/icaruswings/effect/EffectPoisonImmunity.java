package org.mineplugin.locusazzurro.icaruswings.effect;


import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EffectPoisonImmunity extends AbstractEffect{

	public EffectPoisonImmunity() {
		super(MobEffectCategory.BENEFICIAL, 0xe4fbbd);
	}
	
	@SubscribeEvent
	public static void onPotionApplication(PotionEvent.PotionApplicableEvent e) {
		if (e.getPotionEffect().getEffect().equals(MobEffects.POISON)
				&& (e.getEntityLiving().hasEffect(EffectRegistry.poisonImmunity.get()))) {
			e.setResult(Result.DENY);
		}
	}
	
	@Override
	public void onAdded(LivingEntity entity, MobEffectInstance effect, @Nullable MobEffectInstance oldEffect) {
		entity.removeEffect(MobEffects.POISON);
	}
	
	
}

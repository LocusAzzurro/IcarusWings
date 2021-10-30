package org.mineplugin.locusazzurro.icaruswings.magic;


import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.EffectRegistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EffectPoisonImmunity extends AbstractEffect{

	public EffectPoisonImmunity() {
		super(EffectType.BENEFICIAL, ((228 << 16) + (251 << 8) + 189));
	}
	
	/*
	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent e) {
		if (e.getSource() == DamageSource.MAGIC) {
			if(e.getEntityLiving().hasEffect(EffectRegistry.poisonImmunity.get())) {
				e.setCanceled(true);
			}
		}
	}
	*/
	
	@SubscribeEvent
	public static void onPotionApplication(PotionEvent.PotionApplicableEvent e) {
		if (e.getPotionEffect().getEffect().equals(Effects.POISON) 
				&& (e.getEntityLiving().hasEffect(EffectRegistry.poisonImmunity.get()))) {
			e.setResult(Result.DENY);
		}
	}
	
	public void onAdded(LivingEntity entity, EffectInstance effect, @Nullable EffectInstance oldEffect) {
		entity.removeEffect(Effects.POISON);
	}
	
	
}

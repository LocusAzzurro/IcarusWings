package org.mineplugin.locusazzurro.icaruswings.magic;


import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.awt.*;

@EventBusSubscriber
public class EffectPoisonImmunity extends AbstractEffect{

	private static final Color color = new Color(228, 251, 189);

	public EffectPoisonImmunity() {
		super(EffectType.BENEFICIAL, color.getRGB());
	}
	
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

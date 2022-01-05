package org.mineplugin.locusazzurro.icaruswings.magic;

import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.awt.*;

@EventBusSubscriber
public class EffectSensoryMasking extends AbstractEffect{

	private static final Color color = new Color(240, 240, 255);

	public EffectSensoryMasking() {
		super(EffectType.BENEFICIAL, color.getRGB());

	}
	
	@SubscribeEvent
	public static void onSetTarget(LivingSetAttackTargetEvent e) {
		if (e.getTarget() != null) {
			LivingEntity targeter = e.getEntityLiving();
			if (e.getTarget().hasEffect(EffectRegistry.sensoryMasking.get()) && targeter instanceof MobEntity) {
				((MobEntity) targeter).setTarget(null);
			}
		}
	}
	
}

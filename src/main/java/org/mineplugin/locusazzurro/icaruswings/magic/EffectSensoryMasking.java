package org.mineplugin.locusazzurro.icaruswings.magic;

import org.mineplugin.locusazzurro.icaruswings.data.EffectRegistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EffectSensoryMasking extends AbstractEffect{

	public EffectSensoryMasking() {
		super(EffectType.BENEFICIAL, ((240 << 16) + (240 << 8) + 255));

	}
	
	@SubscribeEvent
	public static void onSetTarget(LivingSetAttackTargetEvent e) {
		if (e.getTarget() != null) {
			LivingEntity targeter = e.getEntityLiving();
			if (e.getTarget().hasEffect(EffectRegistry.ionicField.get()) && targeter instanceof MobEntity) {
				((MobEntity) targeter).setTarget(null);
			}
		}
	}
	
}

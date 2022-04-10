package org.mineplugin.locusazzurro.icaruswings.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

@EventBusSubscriber
public class EffectSensoryMasking extends AbstractEffect{

	public EffectSensoryMasking() {
		super(MobEffectCategory.BENEFICIAL, 0xf0f0ff);

	}
	
	@SubscribeEvent
	public static void onSetTarget(LivingSetAttackTargetEvent e) {
		if (e.getTarget() != null) {
			LivingEntity targeter = e.getEntityLiving();
			if (e.getTarget().hasEffect(EffectRegistry.sensoryMasking.get()) && targeter instanceof Mob) {
				((Mob) targeter).setTarget(null);
			}
		}
	}
	
}

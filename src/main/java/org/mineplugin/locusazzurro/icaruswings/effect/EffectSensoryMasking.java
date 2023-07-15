package org.mineplugin.locusazzurro.icaruswings.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

@EventBusSubscriber
public class EffectSensoryMasking extends AbstractEffect{

	public EffectSensoryMasking() {
		super(MobEffectCategory.BENEFICIAL, 0xf0f0ff);

	}
	
	@SubscribeEvent
	public static void onSetTarget(LivingChangeTargetEvent e) {
		if (e.getNewTarget() != null) {
			LivingEntity targeter = e.getEntity();
			if (e.getNewTarget().hasEffect(EffectRegistry.sensoryMasking.get()) && targeter instanceof Mob) {
				((Mob) targeter).setTarget(e.getOriginalTarget());
			}
		}
	}
	
}

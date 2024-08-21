package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

public class EffectSensoryMasking extends AbstractEffect{

	public EffectSensoryMasking() {
		super(MobEffectCategory.BENEFICIAL, 0xf0f0ff);

	}
}

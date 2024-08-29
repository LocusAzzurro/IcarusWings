package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

public class EffectSensoryMasking extends AbstractEffect{

	public EffectSensoryMasking() {
		super(EffectRegistry.SENSORY_MASKING, MobEffectCategory.BENEFICIAL, 0xf0f0ff);

	}
}

package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

@EventBusSubscriber
public class SensoryMaskingEffect extends MobEffect {

    public SensoryMaskingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xf0f0ff);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onSetTarget(LivingChangeTargetEvent e) {
        LivingEntity targeter = e.getEntity();
        LivingEntity newTarget = e.getNewAboutToBeSetTarget();
        LivingEntity originalTarget = e.getOriginalAboutToBeSetTarget();
        if (targeter instanceof Mob mob) {
            if (originalTarget != null && originalTarget.hasEffect(EffectRegistry.SENSORY_MASKING)){
                mob.setTarget(null);
                mob.getNavigation().stop();
            }
            if (newTarget != null && newTarget.hasEffect(EffectRegistry.SENSORY_MASKING)) {
                e.setCanceled(true);
            }
        }
    }
}

package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

public class EffectInterdiction extends AbstractEffect{

    public EffectInterdiction() {
        super(EffectRegistry.INTERDICTION, MobEffectCategory.HARMFUL, 0x688282);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int level) {
        if (entity instanceof Player && entity.isFallFlying()){
            ((Player) entity).stopFallFlying();
            entity.setDeltaMovement(entity.getDeltaMovement().add(new Vec3(0.0, -0.5 * level, 0.0)));
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickRemaining, int level) {
        int interval = 20 >> level;
        if (interval > 0){
            return tickRemaining % interval == 0;
        }
        else return true;
    } ;
}

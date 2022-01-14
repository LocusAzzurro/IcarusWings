package org.mineplugin.locusazzurro.icaruswings.magic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.vector.Vector3d;

public class EffectInterdiction extends AbstractEffect{

    public EffectInterdiction() {
        super(EffectType.HARMFUL, 0x688282);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int level) {
        if (entity instanceof PlayerEntity && entity.isFallFlying()){
            ((PlayerEntity) entity).stopFallFlying();
            entity.setDeltaMovement(entity.getDeltaMovement().add(new Vector3d(0.0, -0.5 * level, 0.0)));
        }
    }

    @Override
    public boolean isDurationEffectTick (int tickRemaining, int level) {
        int interval = 20 >> level;
        if (interval > 0){
            return tickRemaining % interval == 0;
        }
        else return true;
    } ;
}

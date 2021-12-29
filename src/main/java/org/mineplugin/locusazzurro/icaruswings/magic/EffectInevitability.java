package org.mineplugin.locusazzurro.icaruswings.magic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

public class EffectInevitability extends AbstractEffect{

    public EffectInevitability() {
        super(EffectType.HARMFUL, ((85 << 16) + (0 << 8) + 102));
    }

    public int getDuration(int level) {
        return 400;
    };

    public int getMaxLevel() {
        return 9;
    };

    public void overflow(LivingEntity entity, int overflowLevel) {
        entity.hurt(new DamageTimeRift(entity), overflowLevel * 50);
    };

    //todo fix stack overflow
}

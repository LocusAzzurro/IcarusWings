package org.mineplugin.locusazzurro.icaruswings.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;

public class EffectInevitability extends AbstractEffect{

    public EffectInevitability() {
        super(EffectType.HARMFUL, 0x550066);
    }

    public int getDuration(int level) {
        return 400;
    };

    public int getMaxLevel() {
        return 9;
    };

    public void overflow(LivingEntity entity, int overflowLevel) {
        entity.hurt(ModDamageSources.timeRift(entity), overflowLevel * 50);
    };
}

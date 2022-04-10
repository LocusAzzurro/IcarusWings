package org.mineplugin.locusazzurro.icaruswings.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;

public class EffectInevitability extends AbstractEffect{

    public EffectInevitability() {
        super(MobEffectCategory.HARMFUL, 0x550066);
    }

    @Override
    public int getDuration(int level) {
        return 400;
    };

    @Override
    public int getMaxLevel() {
        return 9;
    };

    @Override
    public void overflow(LivingEntity entity, int overflowLevel) {
        entity.hurt(ModDamageSources.timeRift(entity), overflowLevel * 50);
    };
}

package org.mineplugin.locusazzurro.icaruswings.magic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import org.mineplugin.locusazzurro.icaruswings.data.ModDamageSources;

import javax.annotation.Nullable;
import java.awt.*;

public class EffectInevitability extends AbstractEffect{

    private static final Color color = new Color(85, 0, 102);

    public EffectInevitability() {
        super(EffectType.HARMFUL, color.getRGB());
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

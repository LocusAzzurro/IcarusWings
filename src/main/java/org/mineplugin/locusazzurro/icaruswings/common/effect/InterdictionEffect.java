package org.mineplugin.locusazzurro.icaruswings.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class InterdictionEffect extends MobEffect {

    public InterdictionEffect() {
        super(MobEffectCategory.HARMFUL, 0x688282);
    }

    @Override
    public boolean applyEffectTick(@Nullable LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player player && player.isFallFlying()){
            player.stopFallFlying();
            player.setDeltaMovement(player.getDeltaMovement().add(new Vec3(0.0, -0.5 * (amplifier + 1), 0.0)));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = Math.max(1, 20 / (amplifier + 1));
        return duration % interval == 0;
    }
}

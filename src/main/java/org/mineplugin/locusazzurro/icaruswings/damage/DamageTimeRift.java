package org.mineplugin.locusazzurro.icaruswings.damage;

import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class DamageTimeRift extends EntityDamageSource {

    public DamageTimeRift(@Nullable Entity source){
        super("timeRift", source);
    }

    @Override
    public boolean isBypassArmor(){
        return true;
    }

}

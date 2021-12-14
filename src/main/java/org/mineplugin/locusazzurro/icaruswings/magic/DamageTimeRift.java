package org.mineplugin.locusazzurro.icaruswings.magic;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;

public class DamageTimeRift extends EntityDamageSource {

    public DamageTimeRift(@Nullable Entity source){
        super("timeRift", source);
    }

    public boolean isBypassArmor(){
        return true;
    }

}

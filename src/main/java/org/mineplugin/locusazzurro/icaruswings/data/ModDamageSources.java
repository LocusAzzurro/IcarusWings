package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.mineplugin.locusazzurro.icaruswings.damage.DamageTimeRift;

public class ModDamageSources {

    public static DamageSource timeRift(Entity source){
        return new DamageTimeRift(source);
    }

}

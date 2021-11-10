package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import org.mineplugin.locusazzurro.icaruswings.magic.DamageTimeRift;

public class ModDamageSources {

    public static DamageSource timeRift(Entity source){
        return new DamageTimeRift(source);
    }

}

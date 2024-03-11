package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.registry.DamageTypeRegistry;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.core.Holder.Reference;

public class ModDamageSources {

    public static void bootstrap(BootstapContext<DamageType> context)
    {
        context.register(DamageTypeRegistry.SPEAR, new DamageType("spear", 0.1F));
        context.register(DamageTypeRegistry.TIME_RIFT, new DamageType("time_rift", 0.1F));
    }

    public static Reference<DamageType> damageTypeReference(Level pLevel, ResourceKey<DamageType> type){
        return pLevel.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type);
    }

    public static DamageSource spear(Level level, Entity projectile, Entity owner){
        return new DamageSource(damageTypeReference(level, DamageTypeRegistry.SPEAR), projectile, owner);
    }

    public static DamageSource timeRift(Level level, Entity projectile, Entity owner){
        return new DamageSource(damageTypeReference(level, DamageTypeRegistry.TIME_RIFT), projectile, owner);
    }

    public static DamageSource timeRift(Level level, Entity source){
        return new DamageSource(damageTypeReference(level, DamageTypeRegistry.TIME_RIFT), source);
    }

    public static DamageSource timeRift(Level level){
        return new DamageSource(damageTypeReference(level, DamageTypeRegistry.TIME_RIFT));
    }

}

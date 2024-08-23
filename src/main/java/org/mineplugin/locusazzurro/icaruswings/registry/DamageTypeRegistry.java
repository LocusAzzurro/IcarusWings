package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

public class DamageTypeRegistry {

    public static final ResourceKey<DamageType> TIME_RIFT = register("time_rift");
    public static final ResourceKey<DamageType> SPEAR = register("spear");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

}

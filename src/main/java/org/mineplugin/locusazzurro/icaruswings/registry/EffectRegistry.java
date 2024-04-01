package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectInterdiction;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectPoisonImmunity;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectSensoryMasking;

public class EffectRegistry {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModData.MOD_ID);
	
	public static final RegistryObject<MobEffect> POISON_IMMUNITY = EFFECTS.register("poison_immunity", EffectPoisonImmunity::new);
	public static final RegistryObject<MobEffect> SENSORY_MASKING = EFFECTS.register("sensory_masking", EffectSensoryMasking::new);
	public static final RegistryObject<MobEffect> INEVITABILITY = EFFECTS.register("inevitability", EffectInevitability::new);
	public static final RegistryObject<MobEffect> INTERDICTION = EFFECTS.register("interdiction", EffectInterdiction::new);

}
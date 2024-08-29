package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectInterdiction;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectPoisonImmunity;
import org.mineplugin.locusazzurro.icaruswings.common.effect.EffectSensoryMasking;

import java.util.function.Supplier;

public class EffectRegistry {
	
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, IcarusWings.MOD_ID);
	
	public static final DeferredHolder<MobEffect, EffectPoisonImmunity> POISON_IMMUNITY = EFFECTS.register("poison_immunity", EffectPoisonImmunity::new);
	public static final DeferredHolder<MobEffect, EffectSensoryMasking> SENSORY_MASKING = EFFECTS.register("sensory_masking", EffectSensoryMasking::new);
	public static final DeferredHolder<MobEffect, EffectInevitability> INEVITABILITY = EFFECTS.register("inevitability", EffectInevitability::new);
	public static final DeferredHolder<MobEffect, EffectInterdiction> INTERDICTION = EFFECTS.register("interdiction", EffectInterdiction::new);


}
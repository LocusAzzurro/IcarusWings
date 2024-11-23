package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.effect.InevitabilityEffect;
import org.mineplugin.locusazzurro.icaruswings.common.effect.InterdictionEffect;
import org.mineplugin.locusazzurro.icaruswings.common.effect.PoisonImmunityEffect;
import org.mineplugin.locusazzurro.icaruswings.common.effect.SensoryMaskingEffect;

public class EffectRegistry {
	
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, IcarusWings.MOD_ID);
	
	public static final DeferredHolder<MobEffect, PoisonImmunityEffect> POISON_IMMUNITY = EFFECTS.register("poison_immunity", PoisonImmunityEffect::new);
	public static final DeferredHolder<MobEffect, SensoryMaskingEffect> SENSORY_MASKING = EFFECTS.register("sensory_masking", SensoryMaskingEffect::new);
	public static final DeferredHolder<MobEffect, InevitabilityEffect> INEVITABILITY = EFFECTS.register("inevitability", InevitabilityEffect::new);
	public static final DeferredHolder<MobEffect, InterdictionEffect> INTERDICTION = EFFECTS.register("interdiction", InterdictionEffect::new);


}
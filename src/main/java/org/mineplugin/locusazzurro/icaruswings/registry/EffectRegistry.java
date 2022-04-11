package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInterdiction;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectPoisonImmunity;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectSensoryMasking;

public class EffectRegistry {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModData.MOD_ID);
	
	public static final RegistryObject<MobEffect> poisonImmunity = EFFECTS.register("poison_immunity", EffectPoisonImmunity::new);
	public static final RegistryObject<MobEffect> sensoryMasking = EFFECTS.register("sensory_masking", EffectSensoryMasking::new);
	public static final RegistryObject<MobEffect> inevitability = EFFECTS.register("inevitability", EffectInevitability::new);
	public static final RegistryObject<MobEffect> interdiction = EFFECTS.register("interdiction", EffectInterdiction::new);

}
package org.mineplugin.locusazzurro.icaruswings.registry;

import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.magic.*;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ModData.MOD_ID);
	
	public static final RegistryObject<Effect> poisonImmunity = EFFECTS.register("poison_immunity", EffectPoisonImmunity::new);
	public static final RegistryObject<Effect> sensoryMasking = EFFECTS.register("sensory_masking", EffectSensoryMasking::new);
	public static final RegistryObject<Effect> inevitability = EFFECTS.register("inevitability", EffectInevitability::new);
	public static final RegistryObject<Effect> interdiction = EFFECTS.register("interdiction", EffectInterdiction::new);

}
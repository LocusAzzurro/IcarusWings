package org.mineplugin.locusazzurro.icaruswings.data;

import org.mineplugin.locusazzurro.icaruswings.magic.*;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Utils.MOD_ID);
	
	public static final RegistryObject<Effect> poisonImmunity = EFFECTS.register("poison_immunity", EffectPoisonImmunity::new);
	public static final RegistryObject<Effect> ionicField = EFFECTS.register("sensory_masking", EffectSensoryMasking::new);
}
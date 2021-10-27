package org.mineplugin.locusazzurro.icaruswings.data;

import org.mineplugin.locusazzurro.icaruswings.magic.*;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Utils.MOD_ID);
	
	public static final RegistryObject<Enchantment> collisionProtection = ENCHANTMENTS.register("collision_protection", EnchantmentCollisionProtection::new);
}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.ench.EnchantmentBlessingOfTheSky;
import org.mineplugin.locusazzurro.icaruswings.common.ench.EnchantmentCollisionProtection;
import org.mineplugin.locusazzurro.icaruswings.common.ench.EnchantmentPyrotechnicAffinity;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ModData.MOD_ID);
	
	public static final RegistryObject<Enchantment> collisionProtection = ENCHANTMENTS.register("collision_protection", EnchantmentCollisionProtection::new);
	public static final RegistryObject<Enchantment> pyrotechnicAffinity = ENCHANTMENTS.register("pyrotechnic_affinity", EnchantmentPyrotechnicAffinity::new);
	public static final RegistryObject<Enchantment> blessingOfTheSky = ENCHANTMENTS.register("blessing_of_the_sky", EnchantmentBlessingOfTheSky::new);
}

package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.ench.EnchantmentBlessingOfTheSky;
import org.mineplugin.locusazzurro.icaruswings.common.ench.EnchantmentCollisionProtection;
import org.mineplugin.locusazzurro.icaruswings.common.ench.EnchantmentPyrotechnicAffinity;

import java.util.function.Supplier;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT, ModData.MOD_ID);
	
	public static final Supplier<Enchantment> COLLISION_PROTECTION = ENCHANTMENTS.register("collision_protection", EnchantmentCollisionProtection::new);
	public static final Supplier<Enchantment> PYROTECHNIC_AFFINITY = ENCHANTMENTS.register("pyrotechnic_affinity", EnchantmentPyrotechnicAffinity::new);
	public static final Supplier<Enchantment> BLESSING_OF_THE_SKY = ENCHANTMENTS.register("blessing_of_the_sky", EnchantmentBlessingOfTheSky::new);
}

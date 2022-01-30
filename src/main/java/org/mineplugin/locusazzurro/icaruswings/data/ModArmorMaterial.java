package org.mineplugin.locusazzurro.icaruswings.data;

import java.util.function.Supplier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.util.Lazy;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public enum ModArmorMaterial implements IArmorMaterial{
	// name, duraMulti, slotProt[4], enchValue, sound, toughness, KBRes, repIng
	FEATHER("feather", 4, new int[] {1,2,3,1}, 15, SoundRegistry.armorEquipFeather, //chain
			0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.featherBunch.get())),
	GOLDEN_FEATHER("golden_feather", 7, new int[] {1,3,5,2}, 20, SoundRegistry.armorEquipFeather,
			0.5F, 0.0F, () -> Ingredient.of(ItemRegistry.goldenFeatherBunch.get())),
	BEESWAX("beeswax", 5, new int[] {1,2,3,1}, 10, SoundRegistry.armorEquipBeeswax, //honey
			0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.refinedBeeswaxBar.get())),
	LINEN("linen", 4, new int[] {1,2,2,1}, 12, SoundRegistry.armorEquipLinen, //leather
			0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.linen.get())),
	HERBAL("herbal", 1, new int[] {1,1,1,1}, 20, SoundRegistry.armorEquipHerbal, //leather
			0.0F, 0.0F, () -> Ingredient.of(ItemTags.bind("locusazzurro_icaruswings:herbs"))),
	SYNAPSE("synapse_tech", 40, new int[] {3,6,8,3}, 12, SoundRegistry.armorEquipSynapse, //diamond
			4.0F, 0.0F, () -> Ingredient.of(ItemRegistry.synapseRepairKit.get()))
	;
	
	//fuck mojang
	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	
	//material params
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final LazyValue<SoundEvent> sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairIngredient;
	
	ModArmorMaterial (
		String name, 
		int durabilityMultiplier, 
		int[] slotProtections, 
		int enchantmentValue,
		Supplier<SoundEvent> sound,
		float toughness,
		float knockbackResistance,
		Supplier<Ingredient> repairIngredient
		)
	{
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = new LazyValue<>(sound);
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = new LazyValue<>(repairIngredient);
	}
	
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}
	
	public int getDurabilityForSlot(EquipmentSlotType slot) {
	    return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
	}
	
	public int getDefenseForSlot(EquipmentSlotType slot) {
	    return this.slotProtections[slot.getIndex()];
	}
	
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}
	
	public SoundEvent getEquipSound() {
		return this.sound.get();
	}
	
	public float getToughness() {
		return this.toughness;
	}
	
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

	public Ingredient getRepairIngredient() {
	    return this.repairIngredient.get();
	} 

}

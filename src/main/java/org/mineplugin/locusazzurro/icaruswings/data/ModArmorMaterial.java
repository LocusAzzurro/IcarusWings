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

public enum ModArmorMaterial implements IArmorMaterial{
	// name, duraMulti, slotProt[4], enchValue, sound, toughness, KBRes, repIng
	FEATHER("feather", 4, new int[] {1,2,3,1}, 15, SoundEvents.ARMOR_EQUIP_CHAIN,
			0.0F, 0.0F, () -> {return Ingredient.of(ItemRegistry.featherBunch.get());}),
	GOLDEN_FEATHER("golden_feather", 7, new int[] {1,3,5,2}, 20, SoundEvents.ARMOR_EQUIP_GOLD,
			0.5F, 0.0F, () -> {return Ingredient.of(ItemRegistry.goldenFeatherBunch.get());}), 
	WAX("wax", 5, new int[] {1,2,3,1}, 10, SoundEvents.HONEY_BLOCK_SLIDE,
			0.0F, 0.0F, () -> {return Ingredient.of(ItemRegistry.refinedBeeswaxBar.get());}),
	SYNAPSE("synapse_tech", 40, new int[] {3,6,8,3}, 12, SoundEvents.ARMOR_EQUIP_DIAMOND,
			4.0F, 0.0F, () -> {return Ingredient.of(ItemRegistry.synapseRepairKit.get());}) 
	;
	
	//fuck mojang
	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	
	//material params
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairIngredient;
	
	private ModArmorMaterial (
		String name, 
		int durabilityMultiplier, 
		int[] slotProtections, 
		int enchantmentValue,
		SoundEvent sound,
		float toughness,
		float knockbackResistance,
		Supplier<Ingredient> repairIngredient
		)
	{
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
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
		return this.sound;
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

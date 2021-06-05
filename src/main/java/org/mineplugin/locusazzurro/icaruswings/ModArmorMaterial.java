package org.mineplugin.locusazzurro.icaruswings;

import java.util.function.Supplier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmorMaterial implements IArmorMaterial{
	// name, duraMulti, slotProt[4], enchValue, sound, toughness, KBRes, repIng
	FEATHER("feather", 6, new int[] {1,2,3,1}, 10, SoundEvents.ARMOR_EQUIP_CHAIN,
			0.0F, 0.0F, () -> {return Ingredient.of(Items.FEATHER);}),
	GOLDEN_FEATHER("golden_feather", 6, new int[] {1,2,3,1}, 10, SoundEvents.ARMOR_EQUIP_GOLD,
			0.0F, 0.0F, () -> {return Ingredient.of(Items.FEATHER);}),
	WAX("wax", 6, new int[] {1,2,3,1}, 10, SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F, 0.0F, () -> {return Ingredient.of(ItemRegistry.beeswax.get());});
	
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

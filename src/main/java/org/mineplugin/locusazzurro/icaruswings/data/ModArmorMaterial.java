package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.IWLazy;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
	// name, duraMulti, slotProt[4], enchValue, sound, toughness, KBRes, repIng
	FEATHER("feather", 4, new int[] {1,2,3,1}, 15, SoundRegistry.armorEquipFeather, //chain
			0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.featherBunch.get())),
	GOLDEN_FEATHER("golden_feather", 7, new int[] {1,3,5,2}, 20, SoundRegistry.armorEquipFeather,
			0.5F, 0.0F, () -> Ingredient.of(ItemRegistry.goldenFeatherBunch.get())),
	BEESWAX("beeswax", 5, new int[] {1,2,3,1}, 10, SoundRegistry.armorEquipBeeswax, //honey
			0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.refinedBeeswaxBar.get())),
	LINEN("linen", 4, new int[] {1,2,2,1}, 12, SoundRegistry.armorEquipLinen, //leather
			0.0F, 0.0F, () -> Ingredient.of(ItemRegistry.linen.get())),
	GOLDEN_FLEECE("golden_fleece", 7, new int[] {1,3,5,2}, 20, SoundRegistry.armorEquipGoldenFleece,
			0.5F, 0.0F, () -> Ingredient.of(ItemRegistry.goldenFleece.get())),
	HERBAL("herbal", 1, new int[] {1,1,1,1}, 20, SoundRegistry.armorEquipHerbal, //leather
			0.0F, 0.0F, () -> Ingredient.of(ItemTags.create(new ResourceLocation("locusazzurro_icaruswings:herbs")))),
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
	private final IWLazy<SoundEvent> sound;
	private final float toughness;
	private final float knockbackResistance;
	private final IWLazy<Ingredient> repairIngredient;
	
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
		this.sound = IWLazy.of(sound);
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = IWLazy.of(repairIngredient);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	@Override
	public int getDurabilityForType(ArmorItem.Type slot) {
		return HEALTH_PER_SLOT[slot.getSlot().getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForType(ArmorItem.Type type) {
		return this.slotProtections[type.getSlot().getIndex()];
	}

	public int getDefenseForSlot(EquipmentSlot slot) {
		return this.slotProtections[slot.getIndex()];
	}
	
	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return this.sound.get();
	}
	
	@Override
	public float getToughness() {
		return this.toughness;
	}
	
	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

	@Override
	public Ingredient getRepairIngredient() {
	    return this.repairIngredient.get();
	} 

}

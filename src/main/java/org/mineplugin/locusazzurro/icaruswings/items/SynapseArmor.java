package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.UUID;

import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.Utils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class SynapseArmor extends ArmorItem implements IArmorVanishable{
	
	private static final ModArmorMaterial material = ModArmorMaterial.SYNAPSE;
	
	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = Utils.ARMOR_MODIFIER_UUID_PER_SLOT;
	
	public SynapseArmor(EquipmentSlotType slot) {
		super(material, slot, 
			new Item.Properties().tab(ModGroup.itemGroup).defaultDurability(material.getDurabilityForSlot(slot)).rarity(Rarity.RARE));
	}
	
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
		return slot == this.slot ? this.getModifiersPerSlot(slot) : super.getDefaultAttributeModifiers(slot);
	}
	
	private Multimap<Attribute, AttributeModifier> getModifiersPerSlot(EquipmentSlotType slot)
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
	    builder.put(Attributes.ARMOR, new AttributeModifier(
	    		uuid, "Armor modifier", 
	    		(double)material.getDefenseForSlot(slot), 
	    		AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
	    		uuid, "Armor toughness", 
	    		(double)material.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
		
	    if (slot == EquipmentSlotType.HEAD) {
	    	builder.put(Attributes.LUCK, new AttributeModifier(uuid, "luck", 2.0f, AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlotType.CHEST) {
	    	builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "attack_damage", 2.0f, AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlotType.LEGS) {
	    	builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "knockback_resistance", 0.2f, AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlotType.FEET) {
	    	builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "movement_speed", 0.05f, AttributeModifier.Operation.ADDITION));}

		return builder.build();
	}
	
	public boolean isEnderMask(ItemStack stack, PlayerEntity player, EndermanEntity endermanEntity) {
		return (this.slot == EquipmentSlotType.HEAD);
	}
	
}

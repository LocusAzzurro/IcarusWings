package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.UUID;

import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

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

public class SynapseArmor extends ArmorItem implements IArmorVanishable{
	
	private static final ModArmorMaterial material = ModArmorMaterial.SYNAPSE;
	
	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{
			UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), 
			UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), 
			UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), 
			UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	
	public SynapseArmor(EquipmentSlotType slot) {
		super(material, slot, 
			new Item.Properties().tab(ModGroup.itemGroup).defaultDurability(material.getDurabilityForSlot(slot)));
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
	    // head - visor - luck
	    // chest - offense - atk dmg (shared on ikaros wings)
	    // leg - defense - kb resist
	    // boots - propulsion - move speed
		//slot check and attr builder
		return builder.build();
	}
	
	public boolean isEnderMask(ItemStack stack, PlayerEntity player, EndermanEntity endermanEntity) {
		return (this.slot == EquipmentSlotType.HEAD);
	}
}

package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import java.util.UUID;

public class SynapseArmor extends ArmorItem implements Vanishable {
	
	private static final ModArmorMaterial material = ModArmorMaterial.SYNAPSE;
	
	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = ModData.ARMOR_MODIFIER_UUID_PER_SLOT;
	
	public SynapseArmor(EquipmentSlot slot) {
		super(material, slot, 
			new Item.Properties().tab(ModGroup.itemGroup).defaultDurability(material.getDurabilityForSlot(slot)).rarity(Rarity.RARE));
	}
	
	public Multimap<Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == this.slot ? this.getModifiersPerSlot(slot) : super.getDefaultAttributeModifiers(slot);
	}
	
	private Multimap<Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> getModifiersPerSlot(EquipmentSlot slot)
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
	    builder.put(Attributes.ARMOR, new AttributeModifier(
	    		uuid, "Armor modifier", 
	    		(double)material.getDefenseForSlot(slot), 
	    		net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
	    builder.put(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS, new net.minecraft.world.entity.ai.attributes.AttributeModifier(
	    		uuid, "Armor toughness", 
	    		(double)material.getToughness(), 
	    		net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
		
	    if (slot == EquipmentSlot.HEAD) {
	    	builder.put(Attributes.LUCK, new net.minecraft.world.entity.ai.attributes.AttributeModifier(uuid, "luck", 2.0f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlot.CHEST) {
	    	builder.put(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE, new net.minecraft.world.entity.ai.attributes.AttributeModifier(uuid, "attack_damage", 2.0f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlot.LEGS) {
	    	builder.put(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "knockback_resistance", 0.2f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlot.FEET) {
	    	builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "movement_speed", 0.05f, AttributeModifier.Operation.ADDITION));}

		return builder.build();
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
		return (this.slot == EquipmentSlot.HEAD);
	}
	
}

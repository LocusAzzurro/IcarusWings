package org.mineplugin.locusazzurro.icaruswings.common.item;

import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.UUID;

public class SynapseArmor extends ArmorItem implements Vanishable {
	
	private static final ModArmorMaterial material = ModArmorMaterial.SYNAPSE;
	
	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = ModData.ARMOR_MODIFIER_UUID_PER_SLOT;
	
	public SynapseArmor(ArmorItem.Type type) {
		super(material, type,
			new Item.Properties().defaultDurability(material.getDurabilityForType(type)).rarity(Rarity.RARE));
	}
	
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == this.type.getSlot() ? this.getModifiersPerSlot(slot) : super.getDefaultAttributeModifiers(slot);
	}
	
	private Multimap<Attribute, AttributeModifier> getModifiersPerSlot(EquipmentSlot slot)
	{
		Builder<Attribute, AttributeModifier> builder = ModData.createArmorModifierBuilder(slot, material);
	    UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
		
	    if (slot == EquipmentSlot.HEAD) {
	    	builder.put(Attributes.LUCK, new AttributeModifier(uuid, "luck", 2.0f, AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlot.CHEST) {
	    	builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "attack_damage", 2.0f, AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlot.LEGS) {
	    	builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "knockback_resistance", 0.2f, AttributeModifier.Operation.ADDITION));}
	    if (slot == EquipmentSlot.FEET) {
	    	builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "movement_speed", 0.05f, AttributeModifier.Operation.ADDITION));}

		return builder.build();
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
		return (this.type == Type.HELMET);
	}
	
}

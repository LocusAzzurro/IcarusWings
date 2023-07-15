package org.mineplugin.locusazzurro.icaruswings.data;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;

import java.util.UUID;

public class ModData {
	public static final String MOD_ID = "locusazzurro_icaruswings";
	
	public static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[] {
			UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
			UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
			UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
			UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

	public static ImmutableMultimap.Builder<Attribute, AttributeModifier> createArmorModifierBuilder(ArmorItem.Type type, ModArmorMaterial material){
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[type.getSlot().getIndex()];
		builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier",
				(double) material.getDefenseForType(type), AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness",
				(double) material.getToughness(), AttributeModifier.Operation.ADDITION));
		return builder;
	}

	public static ImmutableMultimap.Builder<Attribute, AttributeModifier> createArmorModifierBuilder(EquipmentSlot slot, ModArmorMaterial material){
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
		builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier",
				(double) material.getDefenseForSlot(slot), AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness",
				(double) material.getToughness(), AttributeModifier.Operation.ADDITION));
		return builder;
	}

	public static final UUID WEAPON_ATTACK_DAMAGE_UUID = UUID.fromString("4CB6FA08-DEA3-4C31-802B-F50AB460B043");
	public static final UUID WEAPON_ATTACK_SPEED_UUID = UUID.fromString("472ED099-9950-4469-BACD-CFCF90BE9A1E");
	public static final UUID WEAPON_ATTACK_RANGE_UUID = UUID.fromString("74ECDC27-7460-4340-A381-47B5084B51E1");

}

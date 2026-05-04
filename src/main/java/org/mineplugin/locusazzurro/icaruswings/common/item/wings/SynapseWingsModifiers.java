package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

import java.util.function.UnaryOperator;

public class SynapseWingsModifiers {

    private static final Identifier ARMOR = registerSynapseWingsIdentifier("armor_modifier");
    private static final Identifier ARMOR_TOUGHNESS = registerSynapseWingsIdentifier("armor_toughness");
    private static final Identifier ATTACK_DAMAGE = registerSynapseWingsIdentifier("attack_damage");
    private static final Identifier MAX_HEALTH = registerSynapseWingsIdentifier("max_health");
    private static final Identifier KNOCKBACK_RESISTANCE = registerSynapseWingsIdentifier("knockback_resistance");
    private static final Identifier MOVEMENT_SPEED = registerSynapseWingsIdentifier("movement_speed");

    public static final UnaryOperator<ItemAttributeModifiers> ALPHA_MODIFIERS = attributes -> attributes
            .withModifierAdded(Attributes.ARMOR, new AttributeModifier(ARMOR, 5.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_TOUGHNESS, 3.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE, 2.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH, 10.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(KNOCKBACK_RESISTANCE, 0.2f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED, 0.05f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

    public static final UnaryOperator<ItemAttributeModifiers> BETA_MODIFIERS = attributes -> attributes
            .withModifierAdded(Attributes.ARMOR, new AttributeModifier(ARMOR, 2.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_TOUGHNESS, 1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.CHEST);

    public static final UnaryOperator<ItemAttributeModifiers> DELTA_MODIFIERS = attributes -> attributes
            .withModifierAdded(Attributes.ARMOR, new AttributeModifier(ARMOR, 3.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_TOUGHNESS, 1.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED, 0.03f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

    public static final UnaryOperator<ItemAttributeModifiers> EPSILON_MODIFIERS = attributes -> attributes
            .withModifierAdded(Attributes.ARMOR, new AttributeModifier(ARMOR, 2.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE, 2.0f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED, 0.02f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

    public static final UnaryOperator<ItemAttributeModifiers> ZETA_MODIFIERS = attributes -> attributes
            .withModifierAdded(Attributes.ARMOR, new AttributeModifier(ARMOR, 8.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_TOUGHNESS, 6.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH, 10.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(KNOCKBACK_RESISTANCE, 0.6f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

    public static final UnaryOperator<ItemAttributeModifiers> THETA_MODIFIERS = attributes -> attributes
            .withModifierAdded(Attributes.ARMOR, new AttributeModifier(ARMOR, 6.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_TOUGHNESS, 4.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE, 4.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH, 16.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(KNOCKBACK_RESISTANCE, 0.4f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
            .withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED, 0.05f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);


    private static Identifier registerSynapseWingsIdentifier(String modifierName){
        return Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "synapse_wings_" + modifierName);
    }

}

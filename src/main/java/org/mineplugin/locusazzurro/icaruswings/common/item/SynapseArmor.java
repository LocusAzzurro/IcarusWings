package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import org.mineplugin.locusazzurro.icaruswings.registry.ArmorMaterialRegistry;

public class SynapseArmor extends Item {

    private final ArmorType type;

    public SynapseArmor(ArmorType type) {
        this(type, new Properties());
    }

    public SynapseArmor(ArmorType type, Properties properties) {
        super(properties.durability(type.getDurability(40)).rarity(Rarity.RARE).humanoidArmor(ArmorMaterialRegistry.SYNAPSE, type));
        this.type = type;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        ItemAttributeModifiers defaultAttributeModifiers = super.getDefaultAttributeModifiers(stack);
        return switch (type) {
            case HELMET -> defaultAttributeModifiers.withModifierAdded(
                Attributes.LUCK,
                new AttributeModifier(Identifier.withDefaultNamespace("luck"), 2.0f, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.HEAD
            );
            case CHESTPLATE -> defaultAttributeModifiers.withModifierAdded(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Identifier.withDefaultNamespace("attack_damage"), 2.0f, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.CHEST
            );
            case LEGGINGS -> defaultAttributeModifiers.withModifierAdded(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(Identifier.withDefaultNamespace("knockback_resistance"), 0.2f, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.LEGS
            );
            case BOOTS -> defaultAttributeModifiers.withModifierAdded(
                Attributes.MOVEMENT_SPEED,
                new AttributeModifier(Identifier.withDefaultNamespace("movement_speed"), 0.05f, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.FEET
            );
            default -> defaultAttributeModifiers;
        };
    }
}

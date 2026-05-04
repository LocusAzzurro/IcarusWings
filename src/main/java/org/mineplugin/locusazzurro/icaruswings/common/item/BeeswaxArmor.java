package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import org.mineplugin.locusazzurro.icaruswings.registry.ArmorMaterialRegistry;

public class BeeswaxArmor extends Item {

    public BeeswaxArmor(ArmorType type) {
        this(type, new Properties());
    }

    public BeeswaxArmor(ArmorType type, Properties properties) {
        super(properties.durability(type.getDurability(5)).humanoidArmor(ArmorMaterialRegistry.BEESWAX, type));
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        ItemAttributeModifiers defaultAttributeModifiers = super.getDefaultAttributeModifiers(stack);
        return defaultAttributeModifiers.withModifierAdded
            (Attributes.MOVEMENT_SPEED,
            new AttributeModifier(Identifier.withDefaultNamespace("movement_speed"), 0.01f, AttributeModifier.Operation.ADD_VALUE),
            EquipmentSlotGroup.FEET);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
    {
        return stack.getItem() instanceof BeeswaxArmor;
    }
}

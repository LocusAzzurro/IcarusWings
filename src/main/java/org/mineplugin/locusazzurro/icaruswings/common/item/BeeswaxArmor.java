package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.mineplugin.locusazzurro.icaruswings.registry.ArmorMaterialRegistry;

public class BeeswaxArmor extends ArmorItem implements Equipable {

	public BeeswaxArmor(ArmorItem.Type type) {
		super(ArmorMaterialRegistry.BEESWAX, type, new Properties().durability(type.getDurability(5)));
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		ItemAttributeModifiers defaultAttributeModifiers = super.getDefaultAttributeModifiers();
		return defaultAttributeModifiers.withModifierAdded
				(Attributes.MOVEMENT_SPEED,
				new AttributeModifier(ResourceLocation.withDefaultNamespace("movement_speed"), 0.01f, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.FEET);
	}
	
	@Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
    {
        return stack.getItem() instanceof BeeswaxArmor;
    }
}

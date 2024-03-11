package org.mineplugin.locusazzurro.icaruswings.common.item;

import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.UUID;

public class BeeswaxArmor extends ArmorItem implements Equipable {
	
	private static final ModArmorMaterial material = ModArmorMaterial.BEESWAX;

	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = ModData.ARMOR_MODIFIER_UUID_PER_SLOT;

	public BeeswaxArmor(ArmorItem.Type type) {
		super(material, type, new Item.Properties().defaultDurability(material.getDurabilityForType(type)));
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == this.type.getSlot() ? this.getModifiersPerSlot(slot) : super.getDefaultAttributeModifiers(slot);
	}

	private Multimap<Attribute, AttributeModifier> getModifiersPerSlot(EquipmentSlot slot) {

		Builder<Attribute, AttributeModifier> builder = ModData.createArmorModifierBuilder(slot, material);
		if (slot == Type.BOOTS.getSlot()) {
	    	builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()], "movement_speed", 0.01f, AttributeModifier.Operation.ADDITION));}
		return builder.build();
	}
	
	@Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
    {
        return stack.getItem() instanceof BeeswaxArmor && ((ArmorItem) stack.getItem()).getMaterial() == ModArmorMaterial.BEESWAX;
    }
}

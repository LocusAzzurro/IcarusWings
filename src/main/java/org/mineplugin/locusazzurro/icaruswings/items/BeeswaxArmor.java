package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import java.util.UUID;

public class BeeswaxArmor extends ArmorItem implements Vanishable {
	
	private static final ModArmorMaterial material = ModArmorMaterial.BEESWAX;

	private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = ModData.ARMOR_MODIFIER_UUID_PER_SLOT;

	public BeeswaxArmor(EquipmentSlot slot) {
		super(material, slot, new Item.Properties().tab(ModGroup.itemGroup).defaultDurability(material.getDurabilityForSlot(slot)));
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == this.slot ? this.getModifiersPerSlot(slot) : super.getDefaultAttributeModifiers(slot);
	}

	private Multimap<Attribute, AttributeModifier> getModifiersPerSlot(EquipmentSlot slot) {
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
		builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier",
				(double) material.getDefenseForSlot(slot), AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness",
				(double) material.getToughness(), AttributeModifier.Operation.ADDITION));
		if (slot == EquipmentSlot.FEET) {
	    	builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "movement_speed", 0.01f, AttributeModifier.Operation.ADDITION));}
		return builder.build();
	}
	
	@Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
    {
        return stack.getItem() instanceof BeeswaxArmor && ((ArmorItem) stack.getItem()).getMaterial() == ModArmorMaterial.BEESWAX;
    }
}

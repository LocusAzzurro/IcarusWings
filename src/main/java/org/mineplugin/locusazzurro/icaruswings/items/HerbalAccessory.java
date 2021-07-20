package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class HerbalAccessory extends ArmorItem implements IArmorVanishable{
	
	private static final ModArmorMaterial material = ModArmorMaterial.HERBAL;

	public HerbalAccessory(EquipmentSlotType slot) {
		super(material, slot, new Item.Properties().tab(ModGroup.itemGroup).defaultDurability(material.getDurabilityForSlot(slot)));
	}

}

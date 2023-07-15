package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Vanishable;
import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;

public class HerbalAccessory extends ArmorItem implements Vanishable {
	
	private static final ModArmorMaterial material = ModArmorMaterial.HERBAL;

	public HerbalAccessory(ArmorItem.Type type) {
		super(material, type, new Item.Properties().defaultDurability(material.getDurabilityForType(type)));
	}

}

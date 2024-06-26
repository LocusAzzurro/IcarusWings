package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Vanishable;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModArmorMaterial;

public class LinenCloth extends ArmorItem implements Vanishable {

	private static final ModArmorMaterial MATERIAL = ModArmorMaterial.LINEN;

	public LinenCloth(ArmorItem.Type type) {
		super(MATERIAL, type, new Item.Properties().defaultDurability(MATERIAL.getDurabilityForType(type)));
	}



}

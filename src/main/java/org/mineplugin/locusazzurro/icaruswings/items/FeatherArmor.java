package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class FeatherArmor extends ArmorItem implements IArmorVanishable {
	
	private static final ModArmorMaterial materialFeather = ModArmorMaterial.FEATHER;
	private static final ModArmorMaterial materialGoldenFeather = ModArmorMaterial.GOLDEN_FEATHER;

	public FeatherArmor(EquipmentSlotType slot, boolean isGolden) {
		super((isGolden ? materialGoldenFeather : materialFeather),
				slot, new Item.Properties().tab(ModGroup.itemGroup)
				.defaultDurability((isGolden ? materialGoldenFeather : materialFeather).getDurabilityForSlot(slot)));
	}

}

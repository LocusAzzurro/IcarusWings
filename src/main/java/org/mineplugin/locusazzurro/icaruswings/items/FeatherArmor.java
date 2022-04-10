package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Vanishable;
import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class FeatherArmor extends ArmorItem implements Vanishable {
	
	private static final ModArmorMaterial materialFeather = ModArmorMaterial.FEATHER;
	private static final ModArmorMaterial materialGoldenFeather = ModArmorMaterial.GOLDEN_FEATHER;

	public FeatherArmor(EquipmentSlot slot, boolean isGolden) {
		super((isGolden ? materialGoldenFeather : materialFeather),
				slot, new Item.Properties().tab(ModGroup.itemGroup)
				.defaultDurability((isGolden ? materialGoldenFeather : materialFeather).getDurabilityForSlot(slot)));
	}

}

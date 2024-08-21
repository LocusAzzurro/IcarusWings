package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModArmorMaterial;

public class FeatherArmor extends ArmorItem implements{
	
	private static final ModArmorMaterial materialFeather = ModArmorMaterial.FEATHER;
	private static final ModArmorMaterial materialGoldenFeather = ModArmorMaterial.GOLDEN_FEATHER;

	public FeatherArmor(ArmorItem.Type type, boolean isGolden) {
		super((isGolden ? materialGoldenFeather : materialFeather),
				type, new Item.Properties()
				.durability((isGolden ? materialGoldenFeather : materialFeather).getDurabilityForType(type)));
	}

}

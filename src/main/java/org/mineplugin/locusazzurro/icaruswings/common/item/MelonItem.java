package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.Equippable;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class MelonItem extends BlockItem {

	public MelonItem(Item.Properties properties) {
		super(BlockRegistry.MELON.get(), properties
            .rarity(Rarity.RARE)
            .component(DataComponents.EQUIPPABLE,
                Equippable.builder(EquipmentSlot.HEAD)
                    .setDamageOnHurt(false)
                    .build()));
	}
}

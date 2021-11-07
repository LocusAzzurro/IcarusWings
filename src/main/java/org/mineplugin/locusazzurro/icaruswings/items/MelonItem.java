package org.mineplugin.locusazzurro.icaruswings.items;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class MelonItem extends BlockItem{
	
	public MelonItem() {
		super(BlockRegistry.melon.get(), new Item.Properties().tab(ModGroup.itemGroup).rarity(Rarity.RARE));
	}
	
	@Nullable
	@Override
	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return EquipmentSlotType.HEAD;
	}
}

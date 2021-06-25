package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SimpleFoiledItem;

public class FallenRelic extends Item{
	
	protected RelicType relicType;
	private boolean isRestored;
	
	public FallenRelic(RelicType type, boolean restored){
		super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1).fireResistant());
		this.relicType = type;
		this.isRestored = restored;
	}
	
	public FallenRelic(RelicType type) {
		this(type, false);
	}
	
	public boolean isRestored() {
		return this.isRestored;
	}
	
	public RelicType getType() {
		return this.relicType;
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return this.isRestored;
	}
	
	public static enum RelicType {
		CORE,
		INTERFACE,
		OFFENSIVE,
		DEFENSIVE,
		PROPULSION;
	}
}

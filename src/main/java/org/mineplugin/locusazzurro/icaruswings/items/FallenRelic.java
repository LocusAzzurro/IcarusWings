package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Item;
import net.minecraft.item.SimpleFoiledItem;

public class FallenRelic extends SimpleFoiledItem{
	
	public RelicType relicType;
	public boolean isRestored;
	
	public FallenRelic(RelicType type, boolean restored){
		super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1));
		this.relicType = type;
		this.isRestored = restored;
	}
	
	public FallenRelic(RelicType type) {
		this(type, false);
	}
	
	public static enum RelicType {
		CORE,
		INTERFACE,
		OFFENSIVE,
		DEFENSIVE,
		PROPULSION;
	}
}

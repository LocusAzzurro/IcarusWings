package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface IWingsType {

	public String getName();
	
	public int getDurability();
	
	public Item getRepairItem();
	
	public ResourceLocation getTexture();
	
	public ResourceLocation getTextureReversed();
}

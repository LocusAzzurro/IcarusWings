package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface IWingsType {

	String getName();
	int getDurability();
	Item getRepairItem();
	ResourceLocation getTexture();
	ResourceLocation getTextureReversed();
}

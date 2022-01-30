package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public interface IWingsType {

	String getName();
	int getDurability();
	Item getRepairItem();
	ResourceLocation getTexture();
	ResourceLocation getTextureReversed();
	SoundEvent getEquipSound();
}

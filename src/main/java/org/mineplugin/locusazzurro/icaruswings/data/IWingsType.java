package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public interface IWingsType {

	String getName();
	int getDurability();
	Item getRepairItem();
	ResourceLocation getTexture();
	ResourceLocation getTextureReversed();
	SoundEvent getEquipSound();
}

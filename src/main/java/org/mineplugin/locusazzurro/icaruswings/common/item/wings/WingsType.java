package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public interface WingsType {

	String getName();
	int getDurability();
	Item getRepairItem();
	ResourceLocation getTexture();
	ResourceLocation getTextureReversed();
	Holder<SoundEvent> getEquipSound();
}

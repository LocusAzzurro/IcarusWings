package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public interface WingsType {

	String getName();
	int getDurability();
	Item getRepairItem();
	Identifier getTexture();
	Identifier getTextureReversed();
	Holder<SoundEvent> getEquipSound();
}

package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.SoundRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;

public class SkyMusicDisc extends MusicDiscItem{
	
	private final int analogOutput;
	
	public SkyMusicDisc(int comparatorValue){
		super(comparatorValue, () -> (SoundRegistry.trackFallenDown.get()), new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1));
		this.analogOutput = comparatorValue;
	}
	
	
}

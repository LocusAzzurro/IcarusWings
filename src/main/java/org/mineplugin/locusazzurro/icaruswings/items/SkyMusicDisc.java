package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.function.Supplier;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;

public class SkyMusicDisc extends MusicDiscItem{
	
	private Track track;
	
	public SkyMusicDisc(Track track){
		super(track.getTrackNum(), track.getMusic(), new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1).rarity(Rarity.RARE));
	}
	
	public Track getTrack() {
		return this.track;
	}
	
	public int getAnalogOutput() {
		return this.getTrack().getTrackNum();
	}
	
	@SuppressWarnings("unused")
	public enum Track{
		FALLEN_DOWN(1, () -> (SoundRegistry.trackFallenDown.get()), "Fallen Down", "Hayami Saori"),
		RING_MY_BELL(2, () -> (SoundRegistry.trackRingMyBell.get()), "Ring My Bell", "Blue Drops");
		
		private int trackNum;
		private Supplier<SoundEvent> trackMusic;
		private String trackName;
		private String author;
		
		private Track(int trackNum, Supplier<SoundEvent> trackMusic, String trackName, String author) {
			this.trackNum = trackNum;
			this.trackMusic = trackMusic;
			this.trackName = trackName;
			this.author = author;
		}
		
		private int getTrackNum() {return this.trackNum;}
		private Supplier<SoundEvent> getMusic() {return this.trackMusic;}
		private String getTrackName() {return this.trackName;}
		private String getAuthor() {return this.author;}
	}
	
	
}

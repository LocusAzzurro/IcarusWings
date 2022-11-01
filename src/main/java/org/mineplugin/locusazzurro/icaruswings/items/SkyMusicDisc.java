package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Supplier;


public class SkyMusicDisc extends RecordItem {
	
	private Track track;
	
	public SkyMusicDisc(Track track){
		super(track.getTrackNum(), track.getMusic(), new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1).rarity(Rarity.RARE), track.length);
	}
	
	public Track getTrack() {
		return this.track;
	}
	
	@Override
	public int getAnalogOutput() {
		return this.getTrack().getTrackNum();
	}
	
	@SuppressWarnings("unused")
	public enum Track{
		FALLEN_DOWN(1, () -> (SoundRegistry.trackFallenDown.get()), "Fallen Down", "Hayami Saori", 4800),
		RING_MY_BELL(2, () -> (SoundRegistry.trackRingMyBell.get()), "Ring My Bell", "Blue Drops", 1800);
		
		private int trackNum;
		private Supplier<SoundEvent> trackMusic;
		private String trackName;
		private String author;
		private int length;
		
		Track(int trackNum, Supplier<SoundEvent> trackMusic, String trackName, String author, int length) {
			this.trackNum = trackNum;
			this.trackMusic = trackMusic;
			this.trackName = trackName;
			this.author = author;
			this.length = length;
		}
		
		private int getTrackNum() {return this.trackNum;}
		private Supplier<SoundEvent> getMusic() {return this.trackMusic;}
		private String getTrackName() {return this.trackName;}
		private String getAuthor() {return this.author;}
	}
	
	
}

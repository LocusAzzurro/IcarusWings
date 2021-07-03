package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Utils.MOD_ID);
    public static final RegistryObject<SoundEvent> trackFallenDown = SOUNDS.register("track_fallen_down",
    		() -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "track_fallen_down")));
}

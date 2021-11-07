package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class SoundRegistry {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModData.MOD_ID);
    public static final RegistryObject<SoundEvent> trackFallenDown = SOUNDS.register("track_fallen_down",
    		() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID, "track_fallen_down")));
    public static final RegistryObject<SoundEvent> trackRingMyBell = SOUNDS.register("track_ring_my_bell",
    		() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID, "track_ring_my_bell")));
}

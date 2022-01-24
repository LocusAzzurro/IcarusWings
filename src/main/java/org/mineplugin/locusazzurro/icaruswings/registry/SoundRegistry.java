package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class SoundRegistry {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModData.MOD_ID);
    public static final RegistryObject<SoundEvent> trackFallenDown = SOUNDS.register("track.fallen_down",
    		() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID, "track.fallen_down")));
    public static final RegistryObject<SoundEvent> trackRingMyBell = SOUNDS.register("track.ring_my_bell",
    		() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID, "track.ring_my_bell")));
	public static final RegistryObject<SoundEvent> spearThrow = SOUNDS.register("item.spear.throw",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.spear.throw")));
	public static final RegistryObject<SoundEvent> spearHit = SOUNDS.register("item.spear.hit",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.spear.hit")));
	public static final RegistryObject<SoundEvent> spearHitGround = SOUNDS.register("item.spear.hit_ground",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.spear.hit_ground")));
	public static final RegistryObject<SoundEvent> airJarFill = SOUNDS.register("item.air_jar.fill",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.air_jar.fill")));
	public static final RegistryObject<SoundEvent> airJarEmpty = SOUNDS.register("item.air_jar.empty",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.air_jar.empty")));
	public static final RegistryObject<SoundEvent> artemisMissileLaunch = SOUNDS.register("entity.artemis_missile_launch",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.artemis_missile_launch")));

}

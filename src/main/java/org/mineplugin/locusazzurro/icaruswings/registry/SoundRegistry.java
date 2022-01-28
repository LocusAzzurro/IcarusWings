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
	public static final RegistryObject<SoundEvent> windWandBurst = SOUNDS.register("item.wind_wand.burst",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wind_wand.burst")));
	public static final RegistryObject<SoundEvent> artemisMissileLaunch = SOUNDS.register("entity.artemis_missile.launch",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.artemis_missile.launch")));
	public static final RegistryObject<SoundEvent> timeRiftCollapse = SOUNDS.register("entity.time_rift.collapse",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.time_rift.collapse")));
	public static final RegistryObject<SoundEvent> timeRiftGeneratorFire = SOUNDS.register("item.time_rift_generator.fire",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.time_rift_generator.fire")));
	public static final RegistryObject<SoundEvent> transportCardActivationGeneric = SOUNDS.register("item.transport_card.activation.generic",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.generic")));
	public static final RegistryObject<SoundEvent> transportCardActivationArtemis = SOUNDS.register("item.transport_card.activation.artemis",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.artemis")));
	public static final RegistryObject<SoundEvent> transportCardActivationElectronic = SOUNDS.register("item.transport_card.activation.electronic",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.electronic")));
	public static final RegistryObject<SoundEvent> transportCardActivationChrono = SOUNDS.register("item.transport_card.activation.chrono",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.chrono")));
	public static final RegistryObject<SoundEvent> transportCardFail = SOUNDS.register("item.transport_card.fail",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.fail")));

}

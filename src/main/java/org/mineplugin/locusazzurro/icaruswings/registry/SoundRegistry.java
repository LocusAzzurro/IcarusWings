package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

public class SoundRegistry {
	
	public static final DeferredRegister<net.minecraft.sounds.SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModData.MOD_ID);
    public static final net.minecraftforge.registries.RegistryObject<SoundEvent> trackFallenDown = SOUNDS.register("track.fallen_down",
    		() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID, "track.fallen_down")));
    public static final net.minecraftforge.registries.RegistryObject<SoundEvent> trackRingMyBell = SOUNDS.register("track.ring_my_bell",
    		() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID, "track.ring_my_bell")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> spearThrow = SOUNDS.register("item.spear.throw",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.spear.throw")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> spearHit = SOUNDS.register("item.spear.hit",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.spear.hit")));
	public static final RegistryObject<net.minecraft.sounds.SoundEvent> spearHitGround = SOUNDS.register("item.spear.hit_ground",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.spear.hit_ground")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> meadPotBrew = SOUNDS.register("block.mead_pot.brew",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"block.mead_pot.brew")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> greekFireAmbient = SOUNDS.register("block.greek_fire.ambient",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"block.greek_fire.ambient")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> greekFirePop = SOUNDS.register("block.greek_fire.pop",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"block.greek_fire.pop")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> bucketFillGreekFire = SOUNDS.register("item.bucket.fill_greek_fire",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.bucket.fill_greek_fire")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> bucketEmptyGreekFire = SOUNDS.register("item.bucket.empty_greek_fire",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.bucket.empty_greek_fire")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> goldenRamAmbient = SOUNDS.register("entity.golden_ram.ambient",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.ambient")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> goldenRamDeath = SOUNDS.register("entity.golden_ram.death",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"entity.golden_ram.death")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> goldenRamHurt = SOUNDS.register("entity.golden_ram.hurt",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.hurt")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> goldenRamShear = SOUNDS.register("entity.golden_ram.shear",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.shear")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> goldenRamStep = SOUNDS.register("entity.golden_ram.step",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.step")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> armorEquipFeather = SOUNDS.register("item.armor.equip_feather",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_feather")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> armorEquipBeeswax = SOUNDS.register("item.armor.equip_beeswax",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.armor.equip_beeswax")));
	public static final RegistryObject<net.minecraft.sounds.SoundEvent> armorEquipLinen = SOUNDS.register("item.armor.equip_linen",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.armor.equip_linen")));
	public static final RegistryObject<net.minecraft.sounds.SoundEvent> armorEquipHerbal = SOUNDS.register("item.armor.equip_herbal",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_herbal")));
	public static final RegistryObject<net.minecraft.sounds.SoundEvent> armorEquipSynapse = SOUNDS.register("item.armor.equip_synapse",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.armor.equip_synapse")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> wingsEquipFeather = SOUNDS.register("item.wings.equip_feather",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_feather")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> wingsEquipSynapse = SOUNDS.register("item.wings.equip_synapse",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.wings.equip_synapse")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> wingsEquipPaper = SOUNDS.register("item.wings.equip_paper",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_paper")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> wingsEquipMagic = SOUNDS.register("item.wings.equip_magic",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_magic")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> airJarFill = SOUNDS.register("item.air_jar.fill",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.air_jar.fill")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> airJarEmpty = SOUNDS.register("item.air_jar.empty",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.air_jar.empty")));
	public static final RegistryObject<net.minecraft.sounds.SoundEvent> windWandBurst = SOUNDS.register("item.wind_wand.burst",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wind_wand.burst")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> artemisMissileLaunch = SOUNDS.register("entity.artemis_missile.launch",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.artemis_missile.launch")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> timeRiftCollapse = SOUNDS.register("entity.time_rift.collapse",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"entity.time_rift.collapse")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> timeRiftGeneratorFire = SOUNDS.register("item.time_rift_generator.fire",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.time_rift_generator.fire")));

	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> transportCardActivationGeneric = SOUNDS.register("item.transport_card.activation.generic",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.generic")));
	public static final RegistryObject<net.minecraft.sounds.SoundEvent> transportCardActivationArtemis = SOUNDS.register("item.transport_card.activation.artemis",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.artemis")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> transportCardActivationElectronic = SOUNDS.register("item.transport_card.activation.electronic",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.electronic")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> transportCardActivationChrono = SOUNDS.register("item.transport_card.activation.chrono",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.chrono")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> transportCardTeleportAnchor = SOUNDS.register("item.transport_card.teleport_anchor",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.teleport_anchor")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> transportCardTeleport = SOUNDS.register("item.transport_card.teleport",
			() -> new net.minecraft.sounds.SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.teleport")));
	public static final net.minecraftforge.registries.RegistryObject<SoundEvent> transportCardFail = SOUNDS.register("item.transport_card.fail",
			() -> new net.minecraft.sounds.SoundEvent(new net.minecraft.resources.ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.fail")));

}

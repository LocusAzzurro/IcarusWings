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

	public static final RegistryObject<SoundEvent> meadPotBrew = SOUNDS.register("block.mead_pot.brew",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"block.mead_pot.brew")));
	public static final RegistryObject<SoundEvent> greekFireAmbient = SOUNDS.register("block.greek_fire.ambient",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"block.greek_fire.ambient")));
	public static final RegistryObject<SoundEvent> greekFirePop = SOUNDS.register("block.greek_fire.pop",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"block.greek_fire.pop")));

	public static final RegistryObject<SoundEvent> bucketFillGreekFire = SOUNDS.register("item.bucket.fill_greek_fire",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.bucket.fill_greek_fire")));
	public static final RegistryObject<SoundEvent> bucketEmptyGreekFire = SOUNDS.register("item.bucket.empty_greek_fire",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.bucket.empty_greek_fire")));

	public static final RegistryObject<SoundEvent> goldenRamAmbient = SOUNDS.register("entity.golden_ram.ambient",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.ambient")));
	public static final RegistryObject<SoundEvent> goldenRamDeath = SOUNDS.register("entity.golden_ram.death",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.death")));
	public static final RegistryObject<SoundEvent> goldenRamHurt = SOUNDS.register("entity.golden_ram.hurt",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.hurt")));
	public static final RegistryObject<SoundEvent> goldenRamShear = SOUNDS.register("entity.golden_ram.shear",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.shear")));
	public static final RegistryObject<SoundEvent> goldenRamStep = SOUNDS.register("entity.golden_ram.step",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"entity.golden_ram.step")));

	public static final RegistryObject<SoundEvent> armorEquipFeather = SOUNDS.register("item.armor.equip_feather",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_feather")));
	public static final RegistryObject<SoundEvent> armorEquipBeeswax = SOUNDS.register("item.armor.equip_beeswax",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_beeswax")));
	public static final RegistryObject<SoundEvent> armorEquipLinen = SOUNDS.register("item.armor.equip_linen",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_linen")));
	public static final RegistryObject<SoundEvent> armorEquipHerbal = SOUNDS.register("item.armor.equip_herbal",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_herbal")));
	public static final RegistryObject<SoundEvent> armorEquipSynapse = SOUNDS.register("item.armor.equip_synapse",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.armor.equip_synapse")));

	public static final RegistryObject<SoundEvent> wingsEquipFeather = SOUNDS.register("item.wings.equip_feather",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_feather")));
	public static final RegistryObject<SoundEvent> wingsEquipSynapse = SOUNDS.register("item.wings.equip_synapse",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_synapse")));
	public static final RegistryObject<SoundEvent> wingsEquipPaper = SOUNDS.register("item.wings.equip_paper",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_paper")));
	public static final RegistryObject<SoundEvent> wingsEquipMagic = SOUNDS.register("item.wings.equip_magic",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.wings.equip_magic")));

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
	public static final RegistryObject<SoundEvent> transportCardTeleportAnchor = SOUNDS.register("item.transport_card.teleport_anchor",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.teleport_anchor")));
	public static final RegistryObject<SoundEvent> transportCardTeleport = SOUNDS.register("item.transport_card.teleport",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.teleport")));
	public static final RegistryObject<SoundEvent> transportCardFail = SOUNDS.register("item.transport_card.fail",
			() -> new SoundEvent(new ResourceLocation(ModData.MOD_ID,"item.transport_card.activation.fail")));

}

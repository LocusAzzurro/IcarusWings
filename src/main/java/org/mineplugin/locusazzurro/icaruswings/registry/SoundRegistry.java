package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

@SuppressWarnings("unused")
public class SoundRegistry {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, IcarusWings.MOD_ID);
	
    public static final DeferredHolder<SoundEvent, SoundEvent> TRACK_FALLEN_DOWN = SOUNDS.register("track.fallen_down",
    		() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "track.fallen_down")));
    public static final DeferredHolder<SoundEvent, SoundEvent> TRACK_RING_MY_BELL = SOUNDS.register("track.ring_my_bell",
    		() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "track.ring_my_bell")));

	public static final DeferredHolder<SoundEvent, SoundEvent> SPEAR_THROW = SOUNDS.register("item.spear.throw",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.spear.throw")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPEAR_HIT = SOUNDS.register("item.spear.hit",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.spear.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPEAR_HIT_GROUND = SOUNDS.register("item.spear.hit_ground",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.spear.hit_ground")));

	public static final DeferredHolder<SoundEvent, SoundEvent> MEAD_POT_BREW = SOUNDS.register("block.mead_pot.brew",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"block.mead_pot.brew")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GREEK_FIRE_AMBIENT = SOUNDS.register("block.greek_fire.ambient",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"block.greek_fire.ambient")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GREEK_FIRE_POP = SOUNDS.register("block.greek_fire.pop",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"block.greek_fire.pop")));

	public static final DeferredHolder<SoundEvent, SoundEvent> BUCKET_FILL_GREEK_FIRE = SOUNDS.register("item.bucket.fill_greek_fire",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.bucket.fill_greek_fire")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BUCKET_EMPTY_GREEK_FIRE = SOUNDS.register("item.bucket.empty_greek_fire",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.bucket.empty_greek_fire")));

	public static final DeferredHolder<SoundEvent, SoundEvent> GOLDEN_RAM_AMBIENT = SOUNDS.register("entity.golden_ram.ambient",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.golden_ram.ambient")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GOLDEN_RAM_DEATH = SOUNDS.register("entity.golden_ram.death",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.golden_ram.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GOLDEN_RAM_HURT = SOUNDS.register("entity.golden_ram.hurt",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.golden_ram.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GOLDEN_RAM_SHEAR = SOUNDS.register("entity.golden_ram.shear",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.golden_ram.shear")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GOLDEN_RAM_STEP = SOUNDS.register("entity.golden_ram.step",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.golden_ram.step")));

	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_FEATHER = SOUNDS.register("item.armor.equip_feather",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.armor.equip_feather")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_BEESWAX = SOUNDS.register("item.armor.equip_beeswax",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.armor.equip_beeswax")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_LINEN = SOUNDS.register("item.armor.equip_linen",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.armor.equip_linen")));

	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_GOLDEN_FLEECE = SOUNDS.register("item.armor.equip_golden_fleece",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.armor.equip_golden_fleece")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_HERBAL = SOUNDS.register("item.armor.equip_herbal",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.armor.equip_herbal")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_SYNAPSE = SOUNDS.register("item.armor.equip_synapse",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.armor.equip_synapse")));

	public static final DeferredHolder<SoundEvent, SoundEvent> WINGS_EQUIP_FEATHER = SOUNDS.register("item.wings.equip_feather",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.wings.equip_feather")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WINGS_EQUIP_SYNAPSE = SOUNDS.register("item.wings.equip_synapse",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.wings.equip_synapse")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WINGS_EQUIP_PAPER = SOUNDS.register("item.wings.equip_paper",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.wings.equip_paper")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WINGS_EQUIP_MAGIC = SOUNDS.register("item.wings.equip_magic",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.wings.equip_magic")));

	public static final DeferredHolder<SoundEvent, SoundEvent> AIR_JAR_FILL = SOUNDS.register("item.air_jar.fill",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.air_jar.fill")));
	public static final DeferredHolder<SoundEvent, SoundEvent> AIR_JAR_EMPTY = SOUNDS.register("item.air_jar.empty",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.air_jar.empty")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WIND_WAND_BURST = SOUNDS.register("item.wind_wand.burst",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.wind_wand.burst")));

	public static final DeferredHolder<SoundEvent, SoundEvent> ARTEMIS_MISSILE_LAUNCH = SOUNDS.register("entity.artemis_missile.launch",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.artemis_missile.launch")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TIME_RIFT_COLLAPSE = SOUNDS.register("entity.time_rift.collapse",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"entity.time_rift.collapse")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TIME_RIFT_GENERATOR_FIRE = SOUNDS.register("item.time_rift_generator.fire",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.time_rift_generator.fire")));

	public static final DeferredHolder<SoundEvent, SoundEvent> DEMETER_BLAST = SOUNDS.register("item.demeter.blast",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.demeter.blast")));

	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_ACTIVATION_GENERIC = SOUNDS.register("item.transport_card.activation.generic",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.activation.generic")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_ACTIVATION_ARTEMIS = SOUNDS.register("item.transport_card.activation.artemis",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.activation.artemis")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_ACTIVATION_ELECTRONIC = SOUNDS.register("item.transport_card.activation.electronic",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.activation.electronic")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_ACTIVATION_CHRONO = SOUNDS.register("item.transport_card.activation.chrono",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.activation.chrono")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_TELEPORT_ANCHOR = SOUNDS.register("item.transport_card.teleport_anchor",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.teleport_anchor")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_TELEPORT = SOUNDS.register("item.transport_card.teleport",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.teleport")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TRANSPORT_CARD_FAIL = SOUNDS.register("item.transport_card.fail",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID,"item.transport_card.activation.fail")));

}

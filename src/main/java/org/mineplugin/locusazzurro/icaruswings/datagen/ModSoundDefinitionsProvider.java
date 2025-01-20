package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Supplier;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {

    protected ModSoundDefinitionsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, DataGenerators.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(SoundRegistry.TRACK_FALLEN_DOWN, definition().with(
                sound(modLoc("track_fallen_down")).stream()).replace(true));
        add(SoundRegistry.TRACK_RING_MY_BELL, definition().with(
                sound(modLoc("track_fallen_down")).stream()).replace(true));
        addSound(SoundRegistry.SPEAR_THROW,
                sound(modLoc("spear_throw_1")),
                sound(modLoc("spear_throw_2")),
                sound(modLoc("spear_throw_3")));
        addSound(SoundRegistry.SPEAR_HIT,
                sound(mcLoc("item/trident/pierce1")),
                sound(mcLoc("item/trident/pierce2")),
                sound(mcLoc("item/trident/pierce3")));
        addSound(SoundRegistry.SPEAR_HIT_GROUND,
                sound(modLoc("spear_impact_1")).volume(0.9),
                sound(modLoc("spear_impact_2")).volume(0.9),
                sound(modLoc("spear_impact_3")).volume(0.9),
                sound(modLoc("spear_impact_4")).volume(0.9));
        addSound(SoundRegistry.MEAD_POT_BREW,
                sound(mcLoc("block/brewing_stand/brew1")),
                sound(mcLoc("block/brewing_stand/brew2")));
        addSound(SoundRegistry.GREEK_FIRE_AMBIENT,
                sound(mcLoc("liquid/lava")));
        addSound(SoundRegistry.GREEK_FIRE_POP,
                sound(mcLoc("liquid/lavapop")));
        addSound(SoundRegistry.BUCKET_FILL_GREEK_FIRE,
                sound(mcLoc("item/bucket/fill_lava1")),
                sound(mcLoc("item/bucket/fill_lava2")),
                sound(mcLoc("item/bucket/fill_lava3")));
        addSound(SoundRegistry.BUCKET_EMPTY_GREEK_FIRE,
                sound(mcLoc("item/bucket/empty_lava1")),
                sound(mcLoc("item/bucket/empty_lava2")),
                sound(mcLoc("item/bucket/empty_lava3")));
        addSound(SoundRegistry.GOLDEN_RAM_AMBIENT,
                sound(mcLoc("mob/sheep/say1")),
                sound(mcLoc("mob/sheep/say2")),
                sound(mcLoc("mob/sheep/say3")));
        addSound(SoundRegistry.GOLDEN_RAM_DEATH,
                sound(mcLoc("mob/sheep/say1")),
                sound(mcLoc("mob/sheep/say2")),
                sound(mcLoc("mob/sheep/say3")));
        addSound(SoundRegistry.GOLDEN_RAM_HURT,
                sound(mcLoc("mob/sheep/say1")),
                sound(mcLoc("mob/sheep/say2")),
                sound(mcLoc("mob/sheep/say3")));
        addSound(SoundRegistry.GOLDEN_RAM_SHEAR,
                sound(mcLoc("mob/sheep/shear")));
        addSound(SoundRegistry.GOLDEN_RAM_STEP,
                sound(mcLoc("mob/sheep/step1")),
                sound(mcLoc("mob/sheep/step2")),
                sound(mcLoc("mob/sheep/step3")),
                sound(mcLoc("mob/sheep/step4")),
                sound(mcLoc("mob/sheep/step5")));
        addSound(SoundRegistry.ARMOR_EQUIP_FEATHER,
                sound(mcLoc("item/armor/equip_chain1")),
                sound(mcLoc("item/armor/equip_chain2")),
                sound(mcLoc("item/armor/equip_chain3")),
                sound(mcLoc("item/armor/equip_chain4")),
                sound(mcLoc("item/armor/equip_chain5")),
                sound(mcLoc("item/armor/equip_chain6")));
        addSound(SoundRegistry.ARMOR_EQUIP_BEESWAX,
                sound(mcLoc("block/honeyblock/slide1")).volume(0.8),
                sound(mcLoc("block/honeyblock/slide2")).volume(0.8),
                sound(mcLoc("block/honeyblock/slide3")).volume(0.8),
                sound(mcLoc("block/honeyblock/slide4")).volume(0.8),
                sound(mcLoc("block/honeyblock/slide1")).volume(0.8).pitch(0.9),
                sound(mcLoc("block/honeyblock/slide2")).volume(0.8).pitch(0.8),
                sound(mcLoc("block/honeyblock/slide3")).volume(0.8).pitch(0.8),
                sound(mcLoc("block/honeyblock/slide4")).volume(0.8).pitch(0.8));
        addSound(SoundRegistry.ARMOR_EQUIP_LINEN,
                sound(mcLoc("item/armor/equip_leather1")),
                sound(mcLoc("item/armor/equip_leather2")),
                sound(mcLoc("item/armor/equip_leather3")),
                sound(mcLoc("item/armor/equip_leather4")),
                sound(mcLoc("item/armor/equip_leather5")),
                sound(mcLoc("item/armor/equip_leather6")));
        addSound(SoundRegistry.ARMOR_EQUIP_GOLDEN_FLEECE,
                sound(mcLoc("item/armor/equip_leather1")),
                sound(mcLoc("item/armor/equip_leather2")),
                sound(mcLoc("item/armor/equip_leather3")),
                sound(mcLoc("item/armor/equip_leather4")),
                sound(mcLoc("item/armor/equip_leather5")),
                sound(mcLoc("item/armor/equip_leather6")));
        addSound(SoundRegistry.ARMOR_EQUIP_HERBAL,
                sound(mcLoc("item/armor/equip_leather1")),
                sound(mcLoc("item/armor/equip_leather2")),
                sound(mcLoc("item/armor/equip_leather3")),
                sound(mcLoc("item/armor/equip_leather4")),
                sound(mcLoc("item/armor/equip_leather5")),
                sound(mcLoc("item/armor/equip_leather6")));
        addSound(SoundRegistry.ARMOR_EQUIP_SYNAPSE,
                sound(mcLoc("item/armor/equip_diamond1")),
                sound(mcLoc("item/armor/equip_diamond2")),
                sound(mcLoc("item/armor/equip_diamond3")),
                sound(mcLoc("item/armor/equip_diamond4")),
                sound(mcLoc("item/armor/equip_diamond5")),
                sound(mcLoc("item/armor/equip_diamond6")));
        addSound(SoundRegistry.WINGS_EQUIP_FEATHER,
                sound(mcLoc("item/armor/equip_leather1")),
                sound(mcLoc("item/armor/equip_leather2")),
                sound(mcLoc("item/armor/equip_leather3")),
                sound(mcLoc("item/armor/equip_leather4")),
                sound(mcLoc("item/armor/equip_leather5")),
                sound(mcLoc("item/armor/equip_leather6")));
        addSound(SoundRegistry.WINGS_EQUIP_SYNAPSE,
                sound(mcLoc("item/armor/equip_netherite1")).volume(0.8),
                sound(mcLoc("item/armor/equip_netherite2")).volume(0.8),
                sound(mcLoc("item/armor/equip_netherite3")).volume(0.8),
                sound(mcLoc("item/armor/equip_netherite4")).volume(0.8),
                sound(mcLoc("item/armor/equip_netherite1")).volume(0.8).pitch(0.9),
                sound(mcLoc("item/armor/equip_netherite2")).volume(0.8).pitch(0.9),
                sound(mcLoc("item/armor/equip_netherite3")).volume(0.8).pitch(0.9),
                sound(mcLoc("item/armor/equip_netherite4")).volume(0.8).pitch(0.9));
        addSound(SoundRegistry.WINGS_EQUIP_PAPER,
                sound(mcLoc("item/book/open_flip1")).volume(1.2),
                sound(mcLoc("item/book/open_flip2")).volume(1.2),
                sound(mcLoc("item/book/open_flip3")).volume(1.2));
        addSound(SoundRegistry.WINGS_EQUIP_MAGIC,
                sound(mcLoc("block/end_portal/eyeplace1")),
                sound(mcLoc("block/end_portal/eyeplace2")),
                sound(mcLoc("block/end_portal/eyeplace3")));
        addSound(SoundRegistry.AIR_JAR_FILL,
                sound(mcLoc("item/bottle/fill_dragonbreath1")),
                sound(mcLoc("item/bottle/fill_dragonbreath2")));
        addSound(SoundRegistry.AIR_JAR_EMPTY,
                sound(mcLoc("mob/chicken/plop")));
        addSound(SoundRegistry.WIND_WAND_BURST,
                sound(mcLoc("mob/ghast/fireball4")));
        addSound(SoundRegistry.ARTEMIS_MISSILE_LAUNCH,
                sound(modLoc("artemis_missile_launch_1")),
                sound(modLoc("artemis_missile_launch_2")),
                sound(modLoc("artemis_missile_launch_3")));
        addSound(SoundRegistry.TIME_RIFT_COLLAPSE,
                sound(modLoc("time_rift_collapse")));
        addSound(SoundRegistry.TIME_RIFT_GENERATOR_FIRE,
                sound(modLoc("time_rift_generator_fire")));
        addSound(SoundRegistry.DEMETER_BLAST,
                sound(modLoc("time_rift_generator_fire")).volume(1.2));
        addSound(SoundRegistry.TRANSPORT_CARD_ACTIVATION_GENERIC,
                sound(modLoc("transport_card_activation_generic")));
        addSound(SoundRegistry.TRANSPORT_CARD_ACTIVATION_ARTEMIS,
                sound(modLoc("transport_card_activation_artemis")));
        addSound(SoundRegistry.TRANSPORT_CARD_ACTIVATION_ELECTRONIC,
                sound(modLoc("transport_card_activation_electronic")));
        addSound(SoundRegistry.TRANSPORT_CARD_ACTIVATION_CHRONO,
                sound(modLoc("transport_card_activation_chrono")));
        addSound(SoundRegistry.TRANSPORT_CARD_TELEPORT_ANCHOR,
                sound(mcLoc("block/respawn_anchor/charge1")),
                sound(mcLoc("block/respawn_anchor/charge2")),
                sound(mcLoc("block/respawn_anchor/charge3")));
        addSound(SoundRegistry.TRANSPORT_CARD_TELEPORT,
                sound(mcLoc("mob/endermen/portal")),
                sound(mcLoc("mob/endermen/portal2")));
        addSound(SoundRegistry.TRANSPORT_CARD_FAIL,
                sound(mcLoc("random/fizz")));
    }

    private void addSound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds){
        add(soundEvent, definition().with(sounds).subtitle("subtitles." + soundEvent.get().getLocation().getPath()).replace(true));
    }

    private ResourceLocation modLoc(String name){
        return ResourceLocation.fromNamespaceAndPath(DataGenerators.MOD_ID, name);
    }

    private ResourceLocation mcLoc(String name){
        return ResourceLocation.withDefaultNamespace(name);
    }

}

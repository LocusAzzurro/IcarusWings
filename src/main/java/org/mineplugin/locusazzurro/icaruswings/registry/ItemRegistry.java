package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModItemTier;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.common.item.*;
import org.mineplugin.locusazzurro.icaruswings.item.*;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModData.MOD_ID);
    
    public static final RegistryObject<Item> RED_FEATHER = ITEMS.register("red_feather", ColoredFeather::new);
    public static final RegistryObject<Item> BLUE_FEATHER = ITEMS.register("blue_feather", ColoredFeather::new);
    public static final RegistryObject<Item> GREEN_FEATHER = ITEMS.register("green_feather", ColoredFeather::new);
    public static final RegistryObject<Item> CYAN_FEATHER = ITEMS.register("cyan_feather", ColoredFeather::new);
    public static final RegistryObject<Item> GRAY_FEATHER = ITEMS.register("gray_feather", ColoredFeather::new);
    public static final RegistryObject<Item> GOLDEN_FEATHER = ITEMS.register("golden_feather", GoldenFeather::new);
    
    public static final RegistryObject<Item> FEATHER_BUNCH = ITEMS.register("feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER));
    public static final RegistryObject<Item> COLORED_FEATHER_BUNCH = ITEMS.register("colored_feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER_COLORED));
    public static final RegistryObject<Item> GOLDEN_FEATHER_BUNCH = ITEMS.register("golden_feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER_GOLDEN));
    public static final RegistryObject<Item> HERB_BUNCH = ITEMS.register("herb_bunch", HerbBunch::new);
    
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", WorldIngot::new);
    public static final RegistryObject<Item> MAGMA_INGOT = ITEMS.register("magma_ingot", WorldIngot::new);
    public static final RegistryObject<Item> PURPUR_INGOT = ITEMS.register("purpur_ingot", WorldIngot::new);
    public static final RegistryObject<Item> STEEL_MIXTURE = ITEMS.register("steel_mixture", WorldIngotMaterial::new);
    public static final RegistryObject<Item> MAGMA_MIXTURE = ITEMS.register("magma_mixture", WorldIngotMaterial::new);
    public static final RegistryObject<Item> PURPUR_MIXTURE = ITEMS.register("purpur_mixture", WorldIngotMaterial::new);
    
    public static final RegistryObject<Item> ZEPHIR_ESSENCE = ITEMS.register("zephir_essence", WorldEssence::new);
    public static final RegistryObject<Item> NETHER_ESSENCE = ITEMS.register("nether_essence", WorldEssence::new);
    public static final RegistryObject<Item> VOID_ESSENCE = ITEMS.register("void_essence", WorldEssence::new);
    
    public static final RegistryObject<Item> ULTRAMARINE_GEM = ITEMS.register("ultramarine_gem", GemCurrency::new);
    public static final RegistryObject<Item> SAPPHIRE_GEM = ITEMS.register("sapphire_gem", GemCurrency::new);
    public static final RegistryObject<Item> BLUE_TOPAZ_GEM = ITEMS.register("blue_topaz_gem", GemCurrency::new);
    
    public static final RegistryObject<Item> REFORGED_NETHERITE_INGOT = ITEMS.register("reforged_netherite_ingot", WorldIngot::new);
    public static final RegistryObject<Item> SYNAPSE_ALLOY_INGOT = ITEMS.register("synapse_alloy_ingot", WorldIngot::new);
    
    public static final RegistryObject<Item> BEESWAX = ITEMS.register("beeswax", Beeswax::new);
    public static final RegistryObject<Item> BEESWAX_BLOCK = ITEMS.register("beeswax_block",
    		() -> new BlockItem(BlockRegistry.BEESWAX_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> REFINED_BEESWAX_BAR = ITEMS.register("refined_beeswax_bar", RefinedBeeswaxBar::new);
    public static final RegistryObject<Item> GLISTERING_REFINED_BEESWAX_BAR = ITEMS.register("glistering_refined_beeswax_bar",
    		() -> new RefinedBeeswaxBar(true));
    public static final RegistryObject<Item> REFINED_BEESWAX_BLOCK = ITEMS.register("refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.REFINED_BEESWAX_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> REFINED_BEESWAX_SLAB = ITEMS.register("refined_beeswax_slab",
    		() -> new BlockItem(BlockRegistry.REFINED_BEESWAX_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> REFINED_BEESWAX_PILLAR = ITEMS.register("refined_beeswax_pillar",
    		() -> new BlockItem(BlockRegistry.REFINED_BEESWAX_PILLAR.get(), new Item.Properties()));
    public static final RegistryObject<Item> REFINED_BEESWAX_STAIRS = ITEMS.register("refined_beeswax_stairs",
    		() -> new BlockItem(BlockRegistry.REFINED_BEESWAX_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHISELED_REFINED_BEESWAX_BLOCK = ITEMS.register("chiseled_refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SMOOTH_REFINED_BEESWAX_BLOCK = ITEMS.register("smooth_refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELYSIAN_GRASS_BLOCK = ITEMS.register("elysian_grass_block",
            () -> new BlockItem(BlockRegistry.ELYSIAN_GRASS_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELYSIAN_SOIL = ITEMS.register("elysian_soil",
            () -> new BlockItem(BlockRegistry.ELYSIAN_SOIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELYSIAN_GRASS = ITEMS.register("elysian_grass",
            () -> new BlockItem(BlockRegistry.ELYSIAN_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_WOOL_BLOCK = ITEMS.register("golden_wool_block",
            () -> new BlockItem(BlockRegistry.GOLDEN_WOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_WOOL_CARPET = ITEMS.register("golden_wool_carpet",
            () -> new BlockItem(BlockRegistry.GOLDEN_WOOL_CARPET.get(), new Item.Properties()));
    public static final RegistryObject<Item> HONEY_CAKE = ITEMS.register("honey_cake",
            () -> new BlockItem(BlockRegistry.HONEY_CAKE.get(), new Item.Properties()));
    
    public static final RegistryObject<Item> MEAD_POT = ITEMS.register("mead_pot",
    		() -> new BlockItem(BlockRegistry.MEAD_POT.get(), new Item.Properties()));
    public static final RegistryObject<Item> AMPHORA = ITEMS.register("amphora",
    		() -> new BlockItem(BlockRegistry.AMPHORA.get(), new Item.Properties()));
    public static final RegistryObject<Item> GREEK_FIRE_BUCKET = ITEMS.register("greek_fire_bucket", GreekFireBucket::new);

    public static final RegistryObject<Item> GLASS_JAR = ITEMS.register("glass_jar", GlassJar::new);

    public static final RegistryObject<Item> MEAD = ITEMS.register("mead", Mead::new);
    public static final RegistryObject<Item> ZEPHIR_INFUSED_MEAD = ITEMS.register("zephir_infused_mead",
    		() -> new Mead(Mead.Infusion.ZEPHIR));
    public static final RegistryObject<Item> NETHER_INFUSED_MEAD = ITEMS.register("nether_infused_mead",
    		() -> new Mead(Mead.Infusion.NETHER));
    public static final RegistryObject<Item> VOID_INFUSED_MEAD = ITEMS.register("void_infused_mead",
    		() -> new Mead(Mead.Infusion.VOID));
    public static final RegistryObject<Item> GOLDEN_APPLE_INFUSED_MEAD = ITEMS.register("golden_apple_infused_mead",
    		() -> new Mead(Mead.Infusion.GOLDEN_APPLE));
    public static final RegistryObject<Item> GOLDEN_APPLE_GROWTH_INFUSED_MEAD = ITEMS.register("golden_apple_growth_infused_mead",
            () -> new Mead(Mead.Infusion.GOLDEN_APPLE_GROWTH));
    public static final RegistryObject<Item> HERBS_INFUSED_MEAD = ITEMS.register("herbs_infused_mead",
    		() -> new Mead(Mead.Infusion.HERBS));

    public static final RegistryObject<Item> ZEPHIR_AIR_JAR = ITEMS.register("zephir_air_jar",
            () -> new AirJar(AirJar.AirType.ZEPHIR));
    public static final RegistryObject<Item> NETHER_AIR_JAR = ITEMS.register("nether_air_jar",
            () -> new AirJar(AirJar.AirType.NETHER));
    public static final RegistryObject<Item> VOID_AIR_JAR = ITEMS.register("void_air_jar",
            () -> new AirJar(AirJar.AirType.VOID));
    public static final RegistryObject<Item> ANEMONE = ITEMS.register("anemone", Anemone::new);
    public static final RegistryObject<Item> MAGICAL_ANEMONE = ITEMS.register("magical_anemone", MagicalAnemone::new);
    public static final RegistryObject<Item> WIND_WAND = ITEMS.register("wind_wand", WindWand::new);
    
    public static final RegistryObject<Item> WHEAT_GRAINS = ITEMS.register("wheat_grains", WheatGrains::new);
    public static final RegistryObject<Item> WHEAT_STRING = ITEMS.register("wheat_string", WheatString::new);
    public static final RegistryObject<Item> FLAX = ITEMS.register("flax", Flax::new);
    public static final RegistryObject<Item> FLAX_SEEDS = ITEMS.register("flax_seeds", FlaxSeeds::new);
    public static final RegistryObject<Item> LINEN_STRING = ITEMS.register("linen_string", LinenString::new);
    public static final RegistryObject<Item> GOLDEN_STRING = ITEMS.register("golden_string", GoldenString::new);
    public static final RegistryObject<Item> GOLDEN_FLEECE = ITEMS.register("golden_fleece", GoldenString::new);
    
    public static final RegistryObject<Item> LINEN = ITEMS.register("linen", Linen::new);
    public static final RegistryObject<Item> GRAINS_MEAL = ITEMS.register("grains_meal", GrainsMeal::new);

    public static final RegistryObject<Item> HERBAL_CROWN = ITEMS.register("herbal_crown",
    		() -> new HerbalAccessory(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> FEATHER_HELMET = ITEMS.register("feather_helmet",
    		() -> new FeatherArmor(ArmorItem.Type.HELMET, false));
    public static final RegistryObject<Item> GOLDEN_FEATHER_HELMET = ITEMS.register("golden_feather_helmet",
    		() -> new FeatherArmor(ArmorItem.Type.HELMET, true));
    public static final RegistryObject<Item> LINEN_CLOAK = ITEMS.register("linen_cloak",
    		() -> new LinenCloth(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> LINEN_UNDERGARMENT = ITEMS.register("linen_undergarment",
    		() -> new LinenCloth(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> GOLDEN_FLEECE_MANTLE = ITEMS.register("golden_fleece_mantle",
            () -> new GoldenFleeceArmor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> BEESWAX_BOOTS = ITEMS.register("beeswax_boots",
    		() -> new BeeswaxArmor(ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> GOLDEN_RAM_SPAWN_EGG = ITEMS.register("golden_ram_spawn_egg", GoldenRamSpawnEgg::new);
    
    public static final RegistryObject<Item> FEATHER_WINGS = ITEMS.register("feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER));
    public static final RegistryObject<Item> COLORED_FEATHER_WINGS = ITEMS.register("colored_feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER_COLORED));
    public static final RegistryObject<Item> GOLDEN_FEATHER_WINGS = ITEMS.register("golden_feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER_GOLDEN));
    public static final RegistryObject<Item> PAPER_WINGS = ITEMS.register("paper_wings", PaperWings::new);
    
    public static final RegistryObject<Item> MAGIC_MEMBRANE = ITEMS.register("magic_membrane", MagicMembrane::new);
    public static final RegistryObject<Item> PHILOSOPHER_STONE = ITEMS.register("philosopher_stone", PhilosopherStone::new);
    
    public static final RegistryObject<Item> MAGIC_WINGS = ITEMS.register("magic_wings",
    		() -> new MagicWings(WingsType.MAGIC));
    public static final RegistryObject<Item> FLANDRE_MAGIC_WINGS = ITEMS.register("flandre_magic_wings",
    		() -> new MagicWings(WingsType.PHI_STONE));
    
    public static final RegistryObject<Item> FALLEN_RELIC_CORE = ITEMS.register("fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE));
    public static final RegistryObject<Item> FALLEN_RELIC_INTERFACE = ITEMS.register("fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE));
    public static final RegistryObject<Item> FALLEN_RELIC_OFFENSIVE = ITEMS.register("fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE));
    public static final RegistryObject<Item> FALLEN_RELIC_DEFENSIVE = ITEMS.register("fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE));
    public static final RegistryObject<Item> FALLEN_RELIC_PROPULSION = ITEMS.register("fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION));
    
    public static final RegistryObject<Item> DAEDALUS_MANUSCRIPT = ITEMS.register("daedalus_manuscript",
    		() -> new DaedalusDecryptor(false));
    public static final RegistryObject<Item> DAEDALUS_DECRYPTOR = ITEMS.register("daedalus_decryptor", DaedalusDecryptor::new);
    
    public static final RegistryObject<Item> RESTORED_FALLEN_RELIC_CORE = ITEMS.register("restored_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE, true));
    public static final RegistryObject<Item> RESTORED_FALLEN_RELIC_INTERFACE = ITEMS.register("restored_fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE, true));
    public static final RegistryObject<Item> RESTORED_FALLEN_RELIC_OFFENSIVE = ITEMS.register("restored_fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE, true));
    public static final RegistryObject<Item> RESTORED_FALLEN_RELIC_DEFENSIVE = ITEMS.register("restored_fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE, true));
    public static final RegistryObject<Item> RESTORED_FALLEN_RELIC_PROPULSION = ITEMS.register("restored_fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION, true));
    
    public static final RegistryObject<Item> UPGRADED_FALLEN_RELIC_CORE = ITEMS.register("upgraded_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.SECOND_GEN_CORE, true));
    
    public static final RegistryObject<Item> SYNAPSE_REPAIR_KIT = ITEMS.register("synapse_repair_kit", SynapseRepairKit::new);
    public static final RegistryObject<Item> SYNAPSE_WINGS_RECHARGER = ITEMS.register("synapse_wings_recharger", SynapseWingsRecharger::new);
    public static final RegistryObject<Item> QUANTUM_FUEL = ITEMS.register("quantum_fuel", QuantumFuel::new);
    
    public static final RegistryObject<Item> SYNAPSE_HELMET = ITEMS.register("synapse_helmet",
    		() -> new SynapseArmor(ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> SYNAPSE_CHESTPLATE = ITEMS.register("synapse_chestplate",
    		() -> new SynapseArmor(ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> SYNAPSE_LEGGINGS = ITEMS.register("synapse_leggings",
    		() -> new SynapseArmor(ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> SYNAPSE_BOOTS = ITEMS.register("synapse_boots",
    		() -> new SynapseArmor(ArmorItem.Type.BOOTS));
    
    public static final RegistryObject<Item> SYNAPSE_ALLOY_SWORD = ITEMS.register("synapse_alloy_sword", SynapseAlloySword::new);
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", SteelPickaxe::new);

    public static final RegistryObject<Item> WOODEN_SPEAR = ITEMS.register("wooden_spear", () -> new SpearItem(Tiers.WOOD));
    public static final RegistryObject<Item> STONE_SPEAR = ITEMS.register("stone_spear", () -> new SpearItem(Tiers.STONE));
    public static final RegistryObject<Item> IRON_SPEAR = ITEMS.register("iron_spear", () -> new SpearItem(Tiers.IRON));
    public static final RegistryObject<Item> STEEL_SPEAR = ITEMS.register("steel_spear", () -> new SpearItem(ModItemTier.STEEL));
    public static final RegistryObject<Item> GOLDEN_SPEAR = ITEMS.register("golden_spear", () -> new SpearItem(Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_SPEAR = ITEMS.register("diamond_spear", () -> new SpearItem(Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_SPEAR = ITEMS.register("netherite_spear", () -> new SpearItem(Tiers.NETHERITE));
    public static final RegistryObject<Item> SYNAPSE_ALLOY_SPEAR = ITEMS.register("synapse_alloy_spear", () -> new SpearItem(ModItemTier.SYNAPSE_ALLOY));

    public static final RegistryObject<Item> IKAROS_WINGS = ITEMS.register("ikaros_wings", SynapseWingsAlpha::new);
    public static final RegistryObject<Item> NYMPH_WINGS = ITEMS.register("nymph_wings", SynapseWingsBeta::new);
    public static final RegistryObject<Item> ASTRAEA_WINGS = ITEMS.register("astraea_wings", SynapseWingsDelta::new);
    public static final RegistryObject<Item> CHAOS_WINGS = ITEMS.register("chaos_wings", SynapseWingsEpsilon::new);
    public static final RegistryObject<Item> HIYORI_WINGS = ITEMS.register("hiyori_wings", SynapseWingsZeta::new);
    public static final RegistryObject<Item> MELAN_WINGS = ITEMS.register("melan_wings", SynapseWingsTheta::new);

    public static final RegistryObject<Item> ARTEMIS_LAUNCHER = ITEMS.register("artemis_launcher", ArtemisLauncher::new);
    public static final RegistryObject<Item> ARTEMIS_MISSILE = ITEMS.register("artemis_missile", ArtemisMissile::new);
    public static final RegistryObject<Item> TIME_RIFT_GENERATOR = ITEMS.register("time_rift_generator", TimeRiftGenerator::new);
    public static final RegistryObject<Item> TIME_RIFT_CHARGE = ITEMS.register("time_rift_charge", TimeRiftCharge::new);
    public static final RegistryObject<Item> DEMETER = ITEMS.register("demeter", Demeter::new);
    public static final RegistryObject<Item> DEMETER_CHARGE = ITEMS.register("demeter_charge", () -> new TimeRiftCharge(true));

    public static final RegistryObject<Item> TRANSPORT_CARD_BASE = ITEMS.register("transport_card_base", BaseTransportCard::new);
    public static final RegistryObject<Item> TRANSPORT_CARD_ARTEMIS_HOMING = ITEMS.register("transport_card_artemis_homing",
            () -> new ArtemisTransportCard(true));
    public static final RegistryObject<Item> TRANSPORT_CARD_ARTEMIS_SCATTER = ITEMS.register("transport_card_artemis_scatter",
            ArtemisTransportCard::new);
    public static final RegistryObject<Item> TRANSPORT_CARD_CHRONO_EXPLOSION = ITEMS.register("transport_card_chrono_explosion",
            ChronoExplosionTransportCard::new);
    public static final RegistryObject<Item> TRANSPORT_CARD_CHRONO_TELEPORT = ITEMS.register("transport_card_chrono_teleport",
            TeleportTransportCard::new);
    public static final RegistryObject<Item> TRANSPORT_CARD_MASKING_FIELD = ITEMS.register("transport_card_masking_field",
            MaskingFieldTransportCard::new);
    public static final RegistryObject<Item> TRANSPORT_CARD_INTERDICTION_FIELD = ITEMS.register("transport_card_interdiction_field",
            InterdictionFieldTransportCard::new);
    public static final RegistryObject<Item> TRANSPORT_CARD_TREASURE = ITEMS.register("transport_card_treasure",
            TreasureTransportCard::new);

    public static final RegistryObject<Item> DISC_FALLEN_DOWN = ITEMS.register("disc_fallen_down",
    		() -> new SkyMusicDisc(SkyMusicDisc.Track.FALLEN_DOWN));
    public static final RegistryObject<Item> DISC_RING_MY_BELL = ITEMS.register("disc_ring_my_bell",
    		() -> new SkyMusicDisc(SkyMusicDisc.Track.RING_MY_BELL));
    
    public static final RegistryObject<Item> MELON = ITEMS.register("melon", MelonItem::new);
    public static final RegistryObject<Item> ICON_BADGE = ITEMS.register("icon_badge", IconBadge::new);

}



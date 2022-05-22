package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import org.mineplugin.locusazzurro.icaruswings.data.*;
import org.mineplugin.locusazzurro.icaruswings.items.*;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModData.MOD_ID);
    
    public static final RegistryObject<Item> redFeather = ITEMS.register("red_feather", ColoredFeather::new);
    public static final RegistryObject<Item> blueFeather = ITEMS.register("blue_feather", ColoredFeather::new);
    public static final RegistryObject<Item> greenFeather = ITEMS.register("green_feather", ColoredFeather::new);
    public static final RegistryObject<Item> cyanFeather = ITEMS.register("cyan_feather", ColoredFeather::new);
    public static final RegistryObject<Item> grayFeather = ITEMS.register("gray_feather", ColoredFeather::new);
    public static final RegistryObject<Item> goldenFeather = ITEMS.register("golden_feather", GoldenFeather::new);
    
    public static final RegistryObject<Item> featherBunch = ITEMS.register("feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER));
    public static final RegistryObject<Item> coloredFeatherBunch = ITEMS.register("colored_feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER_COLORED));
    public static final RegistryObject<Item> goldenFeatherBunch = ITEMS.register("golden_feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER_GOLDEN));
    public static final RegistryObject<Item> herbBunch = ITEMS.register("herb_bunch", HerbBunch::new);
    
    public static final RegistryObject<Item> steelIngot = ITEMS.register("steel_ingot", WorldIngot::new);
    public static final RegistryObject<Item> magmaIngot = ITEMS.register("magma_ingot", WorldIngot::new);
    public static final RegistryObject<Item> purpurIngot = ITEMS.register("purpur_ingot", WorldIngot::new);
    public static final RegistryObject<Item> steelIngotRaw = ITEMS.register("steel_mixture", WorldIngotMaterial::new);
    public static final RegistryObject<Item> magmaIngotRaw = ITEMS.register("magma_mixture", WorldIngotMaterial::new);
    public static final RegistryObject<Item> purpurIngotRaw = ITEMS.register("purpur_mixture", WorldIngotMaterial::new);
    
    public static final RegistryObject<Item> zephirEssence = ITEMS.register("zephir_essence", WorldEssence::new);
    public static final RegistryObject<Item> netherEssence = ITEMS.register("nether_essence", WorldEssence::new);
    public static final RegistryObject<Item> voidEssence = ITEMS.register("void_essence", WorldEssence::new);
    
    public static final RegistryObject<Item> ultramarineGem = ITEMS.register("ultramarine_gem", GemCurrency::new);
    public static final RegistryObject<Item> sapphireGem = ITEMS.register("sapphire_gem", GemCurrency::new);
    public static final RegistryObject<Item> blueTopazGem = ITEMS.register("blue_topaz_gem", GemCurrency::new);
    
    public static final RegistryObject<Item> reforgedNetheriteIngot = ITEMS.register("reforged_netherite_ingot", WorldIngot::new);
    public static final RegistryObject<Item> synapseAlloyIngot = ITEMS.register("synapse_alloy_ingot", WorldIngot::new);
    
    public static final RegistryObject<Item> beeswax = ITEMS.register("beeswax", Beeswax::new);
    public static final RegistryObject<Item> beeswaxBlock = ITEMS.register("beeswax_block",
    		() -> new BlockItem(BlockRegistry.beeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxBar = ITEMS.register("refined_beeswax_bar", RefinedBeeswaxBar::new);
    public static final RegistryObject<Item> glisteringRefinedBeeswaxBar = ITEMS.register("glistering_refined_beeswax_bar",
    		() -> new RefinedBeeswaxBar(true));
    public static final RegistryObject<Item> refinedBeeswaxBlock = ITEMS.register("refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxSlab = ITEMS.register("refined_beeswax_slab",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxSlab.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxPillar = ITEMS.register("refined_beeswax_pillar",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxPillar.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxStairs = ITEMS.register("refined_beeswax_stairs",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxStairs.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxBlockChiseled = ITEMS.register("chiseled_refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxBlockChiseled.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> smoothRefinedBeeswaxBlock = ITEMS.register("smooth_refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.smoothRefinedBeeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> elysianGrassBlock = ITEMS.register("elysian_grass_block",
            () -> new BlockItem(BlockRegistry.elysianGrassBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> elysianSoil = ITEMS.register("elysian_soil",
            () -> new BlockItem(BlockRegistry.elysianSoil.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> elysianGrass = ITEMS.register("elysian_grass",
            () -> new BlockItem(BlockRegistry.elysianGrass.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> goldenWoolBlock = ITEMS.register("golden_wool_block",
            () -> new BlockItem(BlockRegistry.goldenWoolBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> goldenWoolCarpet = ITEMS.register("golden_wool_carpet",
            () -> new BlockItem(BlockRegistry.goldenWoolCarpet.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    
    public static final RegistryObject<Item> meadPot = ITEMS.register("mead_pot",
    		() -> new BlockItem(BlockRegistry.meadPot.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> amphora = ITEMS.register("amphora",
    		() -> new BlockItem(BlockRegistry.amphora.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> greekFireBucket = ITEMS.register("greek_fire_bucket", GreekFireBucket::new);

    public static final RegistryObject<Item> glassJar = ITEMS.register("glass_jar", GlassJar::new);

    public static final RegistryObject<Item> mead = ITEMS.register("mead", Mead::new);
    public static final RegistryObject<Item> zephirInfusedMead = ITEMS.register("zephir_infused_mead",
    		() -> new Mead(Mead.Infusion.ZEPHIR));
    public static final RegistryObject<Item> netherInfusedMead = ITEMS.register("nether_infused_mead",
    		() -> new Mead(Mead.Infusion.NETHER));
    public static final RegistryObject<Item> voidInfusedMead = ITEMS.register("void_infused_mead",
    		() -> new Mead(Mead.Infusion.VOID));
    public static final RegistryObject<Item> goldenAppleInfusedMead = ITEMS.register("golden_apple_infused_mead",
    		() -> new Mead(Mead.Infusion.GOLDEN_APPLE));
    public static final RegistryObject<Item> goldenAppleInfusedGrowthMead = ITEMS.register("golden_apple_growth_infused_mead",
            () -> new Mead(Mead.Infusion.GOLDEN_APPLE_GROWTH));
    public static final RegistryObject<Item> herbsInfusedMead = ITEMS.register("herbs_infused_mead",
    		() -> new Mead(Mead.Infusion.HERBS));

    public static final RegistryObject<Item> zephirAirJar = ITEMS.register("zephir_air_jar",
            () -> new AirJar(AirJar.AirType.ZEPHIR));
    public static final RegistryObject<Item> netherAirJar = ITEMS.register("nether_air_jar",
            () -> new AirJar(AirJar.AirType.NETHER));
    public static final RegistryObject<Item> voidAirJar = ITEMS.register("void_air_jar",
            () -> new AirJar(AirJar.AirType.VOID));
    public static final RegistryObject<Item> anemone = ITEMS.register("anemone", Anemone::new);
    public static final RegistryObject<Item> magicalAnemone = ITEMS.register("magical_anemone", MagicalAnemone::new);
    public static final RegistryObject<Item> windWand = ITEMS.register("wind_wand", WindWand::new);
    
    public static final RegistryObject<Item> wheatGrains = ITEMS.register("wheat_grains", WheatGrains::new);
    public static final RegistryObject<Item> wheatString = ITEMS.register("wheat_string", WheatString::new);
    public static final RegistryObject<Item> flax = ITEMS.register("flax", Flax::new);
    public static final RegistryObject<Item> flaxSeeds = ITEMS.register("flax_seeds", FlaxSeeds::new);
    public static final RegistryObject<Item> linenString = ITEMS.register("linen_string", LinenString::new);
    public static final RegistryObject<Item> goldenString = ITEMS.register("golden_string", GoldenString::new);
    public static final RegistryObject<Item> goldenFleece = ITEMS.register("golden_fleece", GoldenString::new);
    
    public static final RegistryObject<Item> linen = ITEMS.register("linen", Linen::new);
    public static final RegistryObject<Item> grainsMeal = ITEMS.register("grains_meal", GrainsMeal::new);

    public static final RegistryObject<Item> herbalCrown = ITEMS.register("herbal_crown",
    		() -> new HerbalAccessory(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> featherHelmet = ITEMS.register("feather_helmet",
    		() -> new FeatherArmor(EquipmentSlot.HEAD, false));
    public static final RegistryObject<Item> goldenFeatherHelmet = ITEMS.register("golden_feather_helmet",
    		() -> new FeatherArmor(EquipmentSlot.HEAD, true));
    public static final RegistryObject<Item> linenCloak = ITEMS.register("linen_cloak",
    		() -> new LinenCloth(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> linenUndergarment = ITEMS.register("linen_undergarment",
    		() -> new LinenCloth(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> goldenFleeceMantle = ITEMS.register("golden_fleece_mantle",
            () -> new GoldenFleeceArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> beeswaxBoots = ITEMS.register("beeswax_boots",
    		() -> new BeeswaxArmor(EquipmentSlot.FEET));

    public static final RegistryObject<Item> goldemRamSpawmEgg = ITEMS.register("golden_ram_spawn_egg", GoldenRamSpawnEgg::new);
    
    public static final RegistryObject<Item> featherWings = ITEMS.register("feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER));
    public static final RegistryObject<Item> coloredFeatherWings = ITEMS.register("colored_feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER_COLORED));
    public static final RegistryObject<Item> goldenFeatherWings = ITEMS.register("golden_feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER_GOLDEN));
    public static final RegistryObject<Item> paperWings = ITEMS.register("paper_wings", PaperWings::new);
    
    public static final RegistryObject<Item> magicMembrane = ITEMS.register("magic_membrane", MagicMembrane::new);
    public static final RegistryObject<Item> philosopherStone = ITEMS.register("philosopher_stone", PhilosopherStone::new);
    
    public static final RegistryObject<Item> magicWings = ITEMS.register("magic_wings",
    		() -> new MagicWings(WingsType.MAGIC));
    public static final RegistryObject<Item> flandreMagicWings = ITEMS.register("flandre_magic_wings",
    		() -> new MagicWings(WingsType.PHI_STONE));
    
    public static final RegistryObject<Item> fallenRelicCore = ITEMS.register("fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE));
    public static final RegistryObject<Item> fallenRelicInterface = ITEMS.register("fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE));
    public static final RegistryObject<Item> fallenRelicOffensive = ITEMS.register("fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE));
    public static final RegistryObject<Item> fallenRelicDefensive = ITEMS.register("fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE));
    public static final RegistryObject<Item> fallenRelicPropulsion = ITEMS.register("fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION));
    
    public static final RegistryObject<Item> daedalusManuscript = ITEMS.register("daedalus_manuscript",
    		() -> new DaedalusDecryptor(false));
    public static final RegistryObject<Item> daedalusDecryptor = ITEMS.register("daedalus_decryptor", DaedalusDecryptor::new);
    
    public static final RegistryObject<Item> restoredFallenRelicCore = ITEMS.register("restored_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE, true));
    public static final RegistryObject<Item> restoredFallenRelicInterface = ITEMS.register("restored_fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE, true));
    public static final RegistryObject<Item> restoredFallenRelicOffensive = ITEMS.register("restored_fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE, true));
    public static final RegistryObject<Item> restoredFallenRelicDefensive = ITEMS.register("restored_fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE, true));
    public static final RegistryObject<Item> restoredFallenRelicPropulsion = ITEMS.register("restored_fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION, true));
    
    public static final RegistryObject<Item> upgradedFallenRelicCore = ITEMS.register("upgraded_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.SECOND_GEN_CORE, true));
    
    public static final RegistryObject<Item> synapseRepairKit = ITEMS.register("synapse_repair_kit", SynapseRepairKit::new);
    public static final RegistryObject<Item> synapseWingsRecharger = ITEMS.register("synapse_wings_recharger", SynapseWingsRecharger::new);
    public static final RegistryObject<Item> quantumFuel = ITEMS.register("quantum_fuel", QuantumFuel::new);
    
    public static final RegistryObject<Item> synapseHelmet = ITEMS.register("synapse_helmet",
    		() -> new SynapseArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<Item> synapseChestplate = ITEMS.register("synapse_chestplate",
    		() -> new SynapseArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<Item> synapseLeggings = ITEMS.register("synapse_leggings",
    		() -> new SynapseArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<Item> synapseBoots = ITEMS.register("synapse_boots",
    		() -> new SynapseArmor(EquipmentSlot.FEET));
    
    public static final RegistryObject<Item> synapseAlloySword = ITEMS.register("synapse_alloy_sword", SynapseAlloySword::new);
    public static final RegistryObject<Item> steelPickaxe = ITEMS.register("steel_pickaxe", SteelPickaxe::new);

    public static final RegistryObject<Item> woodenSpear = ITEMS.register("wooden_spear", () -> new SpearItem(Tiers.WOOD));
    public static final RegistryObject<Item> stoneSpear = ITEMS.register("stone_spear", () -> new SpearItem(Tiers.STONE));
    public static final RegistryObject<Item> ironSpear = ITEMS.register("iron_spear", () -> new SpearItem(Tiers.IRON));
    public static final RegistryObject<Item> steelSpear = ITEMS.register("steel_spear", () -> new SpearItem(ModItemTier.STEEL));
    public static final RegistryObject<Item> goldenSpear = ITEMS.register("golden_spear", () -> new SpearItem(Tiers.GOLD));
    public static final RegistryObject<Item> diamondSpear = ITEMS.register("diamond_spear", () -> new SpearItem(Tiers.DIAMOND));
    public static final RegistryObject<Item> netheriteSpear = ITEMS.register("netherite_spear", () -> new SpearItem(Tiers.NETHERITE));
    public static final RegistryObject<Item> synapseAlloySpear = ITEMS.register("synapse_alloy_spear", () -> new SpearItem(ModItemTier.SYNAPSE_ALLOY));

    public static final RegistryObject<Item> ikarosWings = ITEMS.register("ikaros_wings", SynapseWingsAlpha::new);
    public static final RegistryObject<Item> nymphWings = ITEMS.register("nymph_wings", SynapseWingsBeta::new);
    public static final RegistryObject<Item> astraeaWings = ITEMS.register("astraea_wings", SynapseWingsDelta::new);
    public static final RegistryObject<Item> chaosWings = ITEMS.register("chaos_wings", SynapseWingsEpsilon::new);
    public static final RegistryObject<Item> hiyoriWings = ITEMS.register("hiyori_wings", SynapseWingsZeta::new);
    public static final RegistryObject<Item> melanWings = ITEMS.register("melan_wings", SynapseWingsTheta::new);

    public static final RegistryObject<Item> artemisLauncher = ITEMS.register("artemis_launcher", ArtemisLauncher::new);
    public static final RegistryObject<Item> artemisMissile = ITEMS.register("artemis_missile", ArtemisMissile::new);
    public static final RegistryObject<Item> timeRiftGenerator = ITEMS.register("time_rift_generator", TimeRiftGenerator::new);
    public static final RegistryObject<Item> timeRiftCharge = ITEMS.register("time_rift_charge", TimeRiftCharge::new);
    public static final RegistryObject<Item> demeter = ITEMS.register("demeter", Demeter::new);
    public static final RegistryObject<Item> demeterCharge = ITEMS.register("demeter_charge", TimeRiftCharge::new);

    public static final RegistryObject<Item> transportCardBase = ITEMS.register("transport_card_base", BaseTransportCard::new);
    public static final RegistryObject<Item> transportCardArtemisHoming = ITEMS.register("transport_card_artemis_homing",
            () -> new ArtemisTransportCard(true));
    public static final RegistryObject<Item> transportCardArtemisScatter = ITEMS.register("transport_card_artemis_scatter",
            ArtemisTransportCard::new);
    public static final RegistryObject<Item> transportCardChronoExplosion = ITEMS.register("transport_card_chrono_explosion",
            ChronoExplosionTransportCard::new);
    public static final RegistryObject<Item> transportCardTeleport = ITEMS.register("transport_card_chrono_teleport",
            TeleportTransportCard::new);
    public static final RegistryObject<Item> transportCardMaskingField = ITEMS.register("transport_card_masking_field",
            MaskingFieldTransportCard::new);
    public static final RegistryObject<Item> transportCardInterdictionField = ITEMS.register("transport_card_interdiction_field",
            InterdictionFieldTransportCard::new);
    public static final RegistryObject<Item> transportCardTreasure = ITEMS.register("transport_card_treasure",
            TreasureTransportCard::new);

    public static final RegistryObject<Item> discFallenDown = ITEMS.register("disc_fallen_down",
    		() -> new SkyMusicDisc(SkyMusicDisc.Track.FALLEN_DOWN));
    public static final RegistryObject<Item> discRingMyBell = ITEMS.register("disc_ring_my_bell",
    		() -> new SkyMusicDisc(SkyMusicDisc.Track.RING_MY_BELL));
    
    public static final RegistryObject<Item> melon = ITEMS.register("melon", MelonItem::new);
    public static final RegistryObject<Item> iconBadge = ITEMS.register("icon_badge", IconBadge::new);

}



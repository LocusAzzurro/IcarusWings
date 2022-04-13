package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.ModItemTier;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.items.*;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<net.minecraft.world.item.Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModData.MOD_ID);
    
    public static final RegistryObject<net.minecraft.world.item.Item> redFeather = ITEMS.register("red_feather", ColoredFeather::new);
    public static final RegistryObject<net.minecraft.world.item.Item> blueFeather = ITEMS.register("blue_feather", ColoredFeather::new);
    public static final RegistryObject<net.minecraft.world.item.Item> greenFeather = ITEMS.register("green_feather", ColoredFeather::new);
    public static final RegistryObject<net.minecraft.world.item.Item> cyanFeather = ITEMS.register("cyan_feather", ColoredFeather::new);
    public static final RegistryObject<net.minecraft.world.item.Item> grayFeather = ITEMS.register("gray_feather", ColoredFeather::new);
    public static final RegistryObject<net.minecraft.world.item.Item> goldenFeather = ITEMS.register("golden_feather", GoldenFeather::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> featherBunch = ITEMS.register("feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER));
    public static final RegistryObject<net.minecraft.world.item.Item> coloredFeatherBunch = ITEMS.register("colored_feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER_COLORED));
    public static final RegistryObject<net.minecraft.world.item.Item> goldenFeatherBunch = ITEMS.register("golden_feather_bunch",
    		() -> new FeatherBunch(WingsType.FEATHER_GOLDEN));
    
    public static final RegistryObject<net.minecraft.world.item.Item> steelIngot = ITEMS.register("steel_ingot", WorldIngot::new);
    public static final RegistryObject<net.minecraft.world.item.Item> magmaIngot = ITEMS.register("magma_ingot", WorldIngot::new);
    public static final RegistryObject<net.minecraft.world.item.Item> purpurIngot = ITEMS.register("purpur_ingot", WorldIngot::new);
    public static final RegistryObject<net.minecraft.world.item.Item> steelIngotRaw = ITEMS.register("steel_mixture", WorldIngotMaterial::new);
    public static final RegistryObject<net.minecraft.world.item.Item> magmaIngotRaw = ITEMS.register("magma_mixture", WorldIngotMaterial::new);
    public static final RegistryObject<net.minecraft.world.item.Item> purpurIngotRaw = ITEMS.register("purpur_mixture", WorldIngotMaterial::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> zephirEssence = ITEMS.register("zephir_essence", WorldEssence::new);
    public static final RegistryObject<net.minecraft.world.item.Item> netherEssence = ITEMS.register("nether_essence", WorldEssence::new);
    public static final RegistryObject<net.minecraft.world.item.Item> voidEssence = ITEMS.register("void_essence", WorldEssence::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> ultramarineGem = ITEMS.register("ultramarine_gem", GemCurrency::new);
    public static final RegistryObject<net.minecraft.world.item.Item> sapphireGem = ITEMS.register("sapphire_gem", GemCurrency::new);
    public static final RegistryObject<net.minecraft.world.item.Item> blueTopazGem = ITEMS.register("blue_topaz_gem", GemCurrency::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> reforgedNetheriteIngot = ITEMS.register("reforged_netherite_ingot", WorldIngot::new);
    public static final RegistryObject<net.minecraft.world.item.Item> synapseAlloyIngot = ITEMS.register("synapse_alloy_ingot", WorldIngot::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> beeswax = ITEMS.register("beeswax", Beeswax::new);
    public static final RegistryObject<net.minecraft.world.item.Item> beeswaxBlock = ITEMS.register("beeswax_block",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.beeswaxBlock.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> refinedBeeswaxBar = ITEMS.register("refined_beeswax_bar", RefinedBeeswaxBar::new);
    public static final RegistryObject<net.minecraft.world.item.Item> glisteringRefinedBeeswaxBar = ITEMS.register("glistering_refined_beeswax_bar",
    		() -> new RefinedBeeswaxBar(true));
    public static final RegistryObject<net.minecraft.world.item.Item> refinedBeeswaxBlock = ITEMS.register("refined_beeswax_block",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.refinedBeeswaxBlock.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> refinedBeeswaxSlab = ITEMS.register("refined_beeswax_slab",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.refinedBeeswaxSlab.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> refinedBeeswaxPillar = ITEMS.register("refined_beeswax_pillar",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.refinedBeeswaxPillar.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> refinedBeeswaxStairs = ITEMS.register("refined_beeswax_stairs",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.refinedBeeswaxStairs.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> refinedBeeswaxBlockChiseled = ITEMS.register("chiseled_refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxBlockChiseled.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> smoothRefinedBeeswaxBlock = ITEMS.register("smooth_refined_beeswax_block",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.smoothRefinedBeeswaxBlock.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> flaxCrop = ITEMS.register("flax",
            () -> new net.minecraft.world.item.BlockItem(BlockRegistry.flaxCrop.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> elysianGrassBlock = ITEMS.register("elysian_grass_block",
            () -> new net.minecraft.world.item.BlockItem(BlockRegistry.elysianGrassBlock.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> elysianSoil = ITEMS.register("elysian_soil",
            () -> new net.minecraft.world.item.BlockItem(BlockRegistry.elysianSoil.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> elysianGrass = ITEMS.register("elysian_grass",
            () -> new net.minecraft.world.item.BlockItem(BlockRegistry.elysianGrass.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    
    public static final net.minecraftforge.registries.RegistryObject<Item> meadPot = ITEMS.register("mead_pot",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.meadPot.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> amphora = ITEMS.register("amphora",
    		() -> new net.minecraft.world.item.BlockItem(BlockRegistry.amphora.get(), new net.minecraft.world.item.Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<net.minecraft.world.item.Item> greekFireBucket = ITEMS.register("greek_fire_bucket", GreekFireBucket::new);

    public static final RegistryObject<net.minecraft.world.item.Item> glassJar = ITEMS.register("glass_jar", GlassJar::new);

    public static final RegistryObject<net.minecraft.world.item.Item> mead = ITEMS.register("mead", Mead::new);
    public static final RegistryObject<net.minecraft.world.item.Item> zephirInfusedMead = ITEMS.register("zephir_infused_mead",
    		() -> new Mead(Mead.Infusion.ZEPHIR));
    public static final RegistryObject<net.minecraft.world.item.Item> netherInfusedMead = ITEMS.register("nether_infused_mead",
    		() -> new Mead(Mead.Infusion.NETHER));
    public static final RegistryObject<net.minecraft.world.item.Item> voidInfusedMead = ITEMS.register("void_infused_mead",
    		() -> new Mead(Mead.Infusion.VOID));
    public static final RegistryObject<net.minecraft.world.item.Item> goldenAppleInfusedMead = ITEMS.register("golden_apple_infused_mead",
    		() -> new Mead(Mead.Infusion.GOLDEN_APPLE));
    public static final RegistryObject<net.minecraft.world.item.Item> herbsInfusedMead = ITEMS.register("herbs_infused_mead",
    		() -> new Mead(Mead.Infusion.HERBS));

    public static final RegistryObject<net.minecraft.world.item.Item> zephirAirJar = ITEMS.register("zephir_air_jar",
            () -> new AirJar(AirJar.AirType.ZEPHIR));
    public static final RegistryObject<net.minecraft.world.item.Item> netherAirJar = ITEMS.register("nether_air_jar",
            () -> new AirJar(AirJar.AirType.NETHER));
    public static final RegistryObject<net.minecraft.world.item.Item> voidAirJar = ITEMS.register("void_air_jar",
            () -> new AirJar(AirJar.AirType.VOID));
    public static final RegistryObject<net.minecraft.world.item.Item> anemone = ITEMS.register("anemone", Anemone::new);
    public static final RegistryObject<net.minecraft.world.item.Item> magicalAnemone = ITEMS.register("magical_anemone", MagicalAnemone::new);
    public static final RegistryObject<net.minecraft.world.item.Item> windWand = ITEMS.register("wind_wand", WindWand::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> wheatGrains = ITEMS.register("wheat_grains", WheatGrains::new);
    public static final RegistryObject<net.minecraft.world.item.Item> wheatString = ITEMS.register("wheat_string", WheatString::new);
    public static final RegistryObject<net.minecraft.world.item.Item> flaxSeeds = ITEMS.register("flax_seeds", FlaxSeeds::new);
    public static final RegistryObject<net.minecraft.world.item.Item> linenString = ITEMS.register("linen_string", LinenString::new);
    public static final RegistryObject<net.minecraft.world.item.Item> goldenString = ITEMS.register("golden_string", GoldenString::new);
    public static final RegistryObject<net.minecraft.world.item.Item> goldenFleece = ITEMS.register("golden_fleece", GoldenString::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> linen = ITEMS.register("linen", Linen::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> herbalCrown = ITEMS.register("herbal_crown",
    		() -> new HerbalAccessory(EquipmentSlot.HEAD));
    public static final RegistryObject<net.minecraft.world.item.Item> featherHelmet = ITEMS.register("feather_helmet",
    		() -> new FeatherArmor(EquipmentSlot.HEAD, false));
    public static final RegistryObject<Item> goldenFeatherHelmet = ITEMS.register("golden_feather_helmet",
    		() -> new FeatherArmor(EquipmentSlot.HEAD, true));
    public static final RegistryObject<net.minecraft.world.item.Item> linenCloak = ITEMS.register("linen_cloak",
    		() -> new LinenCloth(EquipmentSlot.CHEST));
    public static final RegistryObject<net.minecraft.world.item.Item> linenUndergarment = ITEMS.register("linen_undergarment",
    		() -> new LinenCloth(EquipmentSlot.LEGS));
    public static final RegistryObject<net.minecraft.world.item.Item> beeswaxBoots = ITEMS.register("beeswax_boots",
    		() -> new BeeswaxArmor(EquipmentSlot.FEET));
    
    public static final RegistryObject<net.minecraft.world.item.Item> featherWings = ITEMS.register("feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER));
    public static final RegistryObject<net.minecraft.world.item.Item> coloredFeatherWings = ITEMS.register("colored_feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER_COLORED));
    public static final RegistryObject<net.minecraft.world.item.Item> goldenFeatherWings = ITEMS.register("golden_feather_wings",
    		() -> new FeatherWings(WingsType.FEATHER_GOLDEN));
    public static final RegistryObject<net.minecraft.world.item.Item> paperWings = ITEMS.register("paper_wings", PaperWings::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> magicMembrane = ITEMS.register("magic_membrane", MagicMembrane::new);
    public static final RegistryObject<net.minecraft.world.item.Item> philosopherStone = ITEMS.register("philosopher_stone", PhilosopherStone::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> magicWings = ITEMS.register("magic_wings",
    		() -> new MagicWings(WingsType.MAGIC));
    public static final RegistryObject<net.minecraft.world.item.Item> flandreMagicWings = ITEMS.register("flandre_magic_wings",
    		() -> new MagicWings(WingsType.PHI_STONE));
    
    public static final RegistryObject<net.minecraft.world.item.Item> fallenRelicCore = ITEMS.register("fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE));
    public static final RegistryObject<net.minecraft.world.item.Item> fallenRelicInterface = ITEMS.register("fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE));
    public static final RegistryObject<net.minecraft.world.item.Item> fallenRelicOffensive = ITEMS.register("fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE));
    public static final RegistryObject<net.minecraft.world.item.Item> fallenRelicDefensive = ITEMS.register("fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE));
    public static final RegistryObject<net.minecraft.world.item.Item> fallenRelicPropulsion = ITEMS.register("fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION));
    
    public static final RegistryObject<net.minecraft.world.item.Item> daedalusManuscript = ITEMS.register("daedalus_manuscript",
    		() -> new DaedalusDecryptor(false));
    public static final RegistryObject<net.minecraft.world.item.Item> daedalusDecryptor = ITEMS.register("daedalus_decryptor", DaedalusDecryptor::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> restoredFallenRelicCore = ITEMS.register("restored_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE, true));
    public static final RegistryObject<net.minecraft.world.item.Item> restoredFallenRelicInterface = ITEMS.register("restored_fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE, true));
    public static final RegistryObject<net.minecraft.world.item.Item> restoredFallenRelicOffensive = ITEMS.register("restored_fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE, true));
    public static final RegistryObject<net.minecraft.world.item.Item> restoredFallenRelicDefensive = ITEMS.register("restored_fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE, true));
    public static final RegistryObject<net.minecraft.world.item.Item> restoredFallenRelicPropulsion = ITEMS.register("restored_fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION, true));
    
    public static final RegistryObject<net.minecraft.world.item.Item> upgradedFallenRelicCore = ITEMS.register("upgraded_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.SECOND_GEN_CORE, true));
    
    public static final RegistryObject<net.minecraft.world.item.Item> synapseRepairKit = ITEMS.register("synapse_repair_kit", SynapseRepairKit::new);
    public static final RegistryObject<net.minecraft.world.item.Item> synapseWingsRecharger = ITEMS.register("synapse_wings_recharger", SynapseWingsRecharger::new);
    public static final RegistryObject<net.minecraft.world.item.Item> quantumFuel = ITEMS.register("quantum_fuel", QuantumFuel::new);
    
    public static final RegistryObject<net.minecraft.world.item.Item> synapseHelmet = ITEMS.register("synapse_helmet",
    		() -> new SynapseArmor(EquipmentSlot.HEAD));
    public static final RegistryObject<net.minecraft.world.item.Item> synapseChestplate = ITEMS.register("synapse_chestplate",
    		() -> new SynapseArmor(EquipmentSlot.CHEST));
    public static final RegistryObject<net.minecraft.world.item.Item> synapseLeggings = ITEMS.register("synapse_leggings",
    		() -> new SynapseArmor(EquipmentSlot.LEGS));
    public static final RegistryObject<net.minecraft.world.item.Item> synapseBoots = ITEMS.register("synapse_boots",
    		() -> new SynapseArmor(EquipmentSlot.FEET));
    
    public static final RegistryObject<net.minecraft.world.item.Item> synapseAlloySword = ITEMS.register("synapse_alloy_sword", SynapseAlloySword::new);
    public static final RegistryObject<net.minecraft.world.item.Item> steelPickaxe = ITEMS.register("steel_pickaxe", SteelPickaxe::new);

    public static final RegistryObject<net.minecraft.world.item.Item> woodenSpear = ITEMS.register("wooden_spear", () -> new SpearItem(Tiers.WOOD));
    public static final RegistryObject<net.minecraft.world.item.Item> stoneSpear = ITEMS.register("stone_spear", () -> new SpearItem(Tiers.STONE));
    public static final RegistryObject<net.minecraft.world.item.Item> ironSpear = ITEMS.register("iron_spear", () -> new SpearItem(Tiers.IRON));
    public static final RegistryObject<net.minecraft.world.item.Item> steelSpear = ITEMS.register("steel_spear", () -> new SpearItem(ModItemTier.STEEL));
    public static final RegistryObject<net.minecraft.world.item.Item> goldenSpear = ITEMS.register("golden_spear", () -> new SpearItem(Tiers.GOLD));
    public static final RegistryObject<net.minecraft.world.item.Item> diamondSpear = ITEMS.register("diamond_spear", () -> new SpearItem(Tiers.DIAMOND));
    public static final RegistryObject<net.minecraft.world.item.Item> netheriteSpear = ITEMS.register("netherite_spear", () -> new SpearItem(Tiers.NETHERITE));
    public static final RegistryObject<net.minecraft.world.item.Item> synapseAlloySpear = ITEMS.register("synapse_alloy_spear", () -> new SpearItem(ModItemTier.SYNAPSE_ALLOY));

    public static final RegistryObject<net.minecraft.world.item.Item> ikarosWings = ITEMS.register("ikaros_wings", SynapseWingsAlpha::new);
    public static final RegistryObject<net.minecraft.world.item.Item> nymphWings = ITEMS.register("nymph_wings", SynapseWingsBeta::new);
    public static final RegistryObject<net.minecraft.world.item.Item> astraeaWings = ITEMS.register("astraea_wings", SynapseWingsDelta::new);
    public static final RegistryObject<net.minecraft.world.item.Item> chaosWings = ITEMS.register("chaos_wings", SynapseWingsEpsilon::new);
    public static final RegistryObject<net.minecraft.world.item.Item> hiyoriWings = ITEMS.register("hiyori_wings", SynapseWingsZeta::new);
    public static final RegistryObject<net.minecraft.world.item.Item> melanWings = ITEMS.register("melan_wings", SynapseWingsTheta::new);

    public static final RegistryObject<net.minecraft.world.item.Item> artemisLauncher = ITEMS.register("artemis_launcher", ArtemisLauncher::new);
    public static final RegistryObject<net.minecraft.world.item.Item> artemisMissile = ITEMS.register("artemis_missile", ArtemisMissile::new);
    public static final RegistryObject<net.minecraft.world.item.Item> timeRiftGenerator = ITEMS.register("time_rift_generator", TimeRiftGenerator::new);
    public static final RegistryObject<net.minecraft.world.item.Item> timeRiftCharge = ITEMS.register("time_rift_charge", TimeRiftCharge::new);

    public static final RegistryObject<net.minecraft.world.item.Item> transportCardBase = ITEMS.register("transport_card_base", BaseTransportCard::new);
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardArtemisHoming = ITEMS.register("transport_card_artemis_homing",
            () -> new ArtemisTransportCard(true));
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardArtemisScatter = ITEMS.register("transport_card_artemis_scatter",
            ArtemisTransportCard::new);
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardChronoExplosion = ITEMS.register("transport_card_chrono_explosion",
            ChronoExplosionTransportCard::new);
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardTeleport = ITEMS.register("transport_card_chrono_teleport",
            TeleportTransportCard::new);
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardMaskingField = ITEMS.register("transport_card_masking_field",
            MaskingFieldTransportCard::new);
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardInterdictionField = ITEMS.register("transport_card_interdiction_field",
            InterdictionFieldTransportCard::new);
    public static final RegistryObject<net.minecraft.world.item.Item> transportCardTreasure = ITEMS.register("transport_card_treasure",
            TreasureTransportCard::new);

    public static final RegistryObject<net.minecraft.world.item.Item> discFallenDown = ITEMS.register("disc_fallen_down",
    		() -> new SkyMusicDisc(SkyMusicDisc.Track.FALLEN_DOWN));
    public static final RegistryObject<net.minecraft.world.item.Item> discRingMyBell = ITEMS.register("disc_ring_my_bell",
    		() -> new SkyMusicDisc(SkyMusicDisc.Track.RING_MY_BELL));
    
    public static final RegistryObject<net.minecraft.world.item.Item> melon = ITEMS.register("melon", MelonItem::new);

}



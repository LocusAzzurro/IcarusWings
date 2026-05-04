package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NullMarked;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModFoods;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModItemTiers;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModJukeboxSongs;
import org.mineplugin.locusazzurro.icaruswings.common.item.*;
import org.mineplugin.locusazzurro.icaruswings.common.item.transportcard.*;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.*;

import java.util.function.Supplier;

@NullMarked
@SuppressWarnings("unused")
public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IcarusWings.MOD_ID);
    
    public static final DeferredHolder<Item, Item> RED_FEATHER = registerSimpleItem("red_feather");
    public static final DeferredHolder<Item, Item> BLUE_FEATHER = registerSimpleItem("blue_feather");
    public static final DeferredHolder<Item, Item> GREEN_FEATHER = registerSimpleItem("green_feather");
    public static final DeferredHolder<Item, Item> CYAN_FEATHER = registerSimpleItem("cyan_feather");
    public static final DeferredHolder<Item, Item> GRAY_FEATHER = registerSimpleItem("gray_feather");
    public static final DeferredHolder<Item, Item> GOLDEN_FEATHER = registerSimpleItem("golden_feather");
    
    public static final DeferredHolder<Item, Item> FEATHER_BUNCH = registerSimpleItem("feather_bunch");
    public static final DeferredHolder<Item, Item> COLORED_FEATHER_BUNCH = registerSimpleItem("colored_feather_bunch");
    public static final DeferredHolder<Item, Item> GOLDEN_FEATHER_BUNCH = registerSimpleItem("golden_feather_bunch");
    public static final DeferredHolder<Item, Item> HERB_BUNCH = registerSimpleItem("herb_bunch");
    
    public static final DeferredHolder<Item, Item> STEEL_INGOT = registerSimpleItem("steel_ingot");
    public static final DeferredHolder<Item, Item> MAGMA_INGOT = registerSimpleItem("magma_ingot");
    public static final DeferredHolder<Item, Item> PURPUR_INGOT = registerSimpleItem("purpur_ingot");
    public static final DeferredHolder<Item, Item> STEEL_MIXTURE = registerSimpleItem("steel_mixture");
    public static final DeferredHolder<Item, Item> MAGMA_MIXTURE = registerSimpleItem("magma_mixture");
    public static final DeferredHolder<Item, Item> PURPUR_MIXTURE = registerSimpleItem("purpur_mixture");
    
    public static final DeferredHolder<Item, Item> ZEPHIR_ESSENCE = registerSimpleItemSmallStack("zephir_essence");
    public static final DeferredHolder<Item, Item> NETHER_ESSENCE = registerSimpleItemSmallStack("nether_essence");
    public static final DeferredHolder<Item, Item> VOID_ESSENCE = registerSimpleItemSmallStack("void_essence");
    
    public static final DeferredHolder<Item, Item> ULTRAMARINE_GEM = registerSimpleItemSmallStack("ultramarine_gem");
    public static final DeferredHolder<Item, Item> SAPPHIRE_GEM = registerSimpleItemSmallStack("sapphire_gem");
    public static final DeferredHolder<Item, Item> BLUE_TOPAZ_GEM = registerSimpleItemSmallStack("blue_topaz_gem");

    public static final DeferredHolder<Item, GoldUpgradeSmithingTemplate> GOLD_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("gold_upgrade_smithing_template", GoldUpgradeSmithingTemplate::new);
    public static final DeferredHolder<Item, Item> REFORGED_NETHERITE_INGOT = registerSimpleItem("reforged_netherite_ingot");
    public static final DeferredHolder<Item, Item> SYNAPSE_ALLOY_INGOT = registerSimpleItem("synapse_alloy_ingot");
    
    public static final DeferredHolder<Item, Item> BEESWAX = ITEMS.registerSimpleItem("beeswax", properties -> properties.food(ModFoods.BEESWAX));
    public static final DeferredHolder<Item, BlockItem> BEESWAX_BLOCK = fromBlock("beeswax_block", BlockRegistry.BEESWAX_BLOCK);
    public static final DeferredHolder<Item, Item> REFINED_BEESWAX_BAR = registerSimpleItem("refined_beeswax_bar");
    public static final DeferredHolder<Item, Item> GLISTERING_REFINED_BEESWAX_BAR = ITEMS.registerItem("glistering_refined_beeswax_bar",
            properties -> new Item(properties){@Override public boolean isPiglinCurrency(ItemStack s) {return true;}});
    public static final DeferredHolder<Item, BlockItem> REFINED_BEESWAX_BLOCK = fromBlock("refined_beeswax_block", BlockRegistry.REFINED_BEESWAX_BLOCK);
    public static final DeferredHolder<Item, BlockItem> REFINED_BEESWAX_SLAB = fromBlock("refined_beeswax_slab", BlockRegistry.REFINED_BEESWAX_SLAB);
    public static final DeferredHolder<Item, BlockItem> REFINED_BEESWAX_PILLAR = fromBlock("refined_beeswax_pillar", BlockRegistry.REFINED_BEESWAX_PILLAR);
    public static final DeferredHolder<Item, BlockItem> REFINED_BEESWAX_STAIRS = fromBlock("refined_beeswax_stairs", BlockRegistry.REFINED_BEESWAX_STAIRS);
    public static final DeferredHolder<Item, BlockItem> CHISELED_REFINED_BEESWAX_BLOCK = fromBlock("chiseled_refined_beeswax_block", BlockRegistry.CHISELED_REFINED_BEESWAX_BLOCK);
    public static final DeferredHolder<Item, BlockItem> SMOOTH_REFINED_BEESWAX_BLOCK = fromBlock("smooth_refined_beeswax_block", BlockRegistry.SMOOTH_REFINED_BEESWAX_BLOCK);
    public static final DeferredHolder<Item, BlockItem> ELYSIAN_GRASS_BLOCK = fromBlock("elysian_grass_block", BlockRegistry.ELYSIAN_GRASS_BLOCK);
    public static final DeferredHolder<Item, BlockItem> ELYSIAN_SOIL = fromBlock("elysian_soil", BlockRegistry.ELYSIAN_SOIL);
    public static final DeferredHolder<Item, BlockItem> ELYSIAN_GRASS = fromBlock("elysian_grass", BlockRegistry.ELYSIAN_GRASS);
    public static final DeferredHolder<Item, BlockItem> GOLDEN_WOOL_BLOCK = fromBlock("golden_wool_block", BlockRegistry.GOLDEN_WOOL_BLOCK);
    public static final DeferredHolder<Item, BlockItem> GOLDEN_WOOL_CARPET = fromBlock("golden_wool_carpet", BlockRegistry.GOLDEN_WOOL_CARPET);
    public static final DeferredHolder<Item, BlockItem> HONEY_CAKE = fromBlock("honey_cake", BlockRegistry.HONEY_CAKE);
    
    public static final DeferredHolder<Item, BlockItem> MEAD_POT = fromBlock("mead_pot", BlockRegistry.MEAD_POT);
    public static final DeferredHolder<Item, BlockItem> AMPHORA = fromBlock("amphora", BlockRegistry.AMPHORA);
    public static final DeferredHolder<Item, BucketItem> GREEK_FIRE_BUCKET = ITEMS.registerItem("greek_fire_bucket",
            properties -> new BucketItem(FluidRegistry.GREEK_FIRE.get(), properties.craftRemainder(Items.BUCKET)));

    public static final DeferredHolder<Item, GlassJar> GLASS_JAR = ITEMS.registerItem("glass_jar", GlassJar::new);

    public static final DeferredHolder<Item, Mead> MEAD = ITEMS.registerItem("mead", Mead::new);
    public static final DeferredHolder<Item, Mead> ZEPHIR_INFUSED_MEAD = ITEMS.registerItem("zephir_infused_mead",
    		properties -> new Mead(Mead.Infusion.ZEPHIR, properties));
    public static final DeferredHolder<Item, Mead> NETHER_INFUSED_MEAD = ITEMS.registerItem("nether_infused_mead",
    		properties -> new Mead(Mead.Infusion.NETHER, properties));
    public static final DeferredHolder<Item, Mead> VOID_INFUSED_MEAD = ITEMS.registerItem("void_infused_mead",
    		properties -> new Mead(Mead.Infusion.VOID, properties));
    public static final DeferredHolder<Item, Mead> GOLDEN_APPLE_INFUSED_MEAD = ITEMS.registerItem("golden_apple_infused_mead",
    		properties -> new Mead(Mead.Infusion.GOLDEN_APPLE, properties));
    public static final DeferredHolder<Item, Mead> GOLDEN_APPLE_GROWTH_INFUSED_MEAD = ITEMS.registerItem("golden_apple_growth_infused_mead",
            properties -> new Mead(Mead.Infusion.GOLDEN_APPLE_GROWTH, properties));
    public static final DeferredHolder<Item, Mead> HERBS_INFUSED_MEAD = ITEMS.registerItem("herbs_infused_mead",
    		properties -> new Mead(Mead.Infusion.HERBS, properties));

    public static final DeferredHolder<Item, AirJar> ZEPHIR_AIR_JAR = ITEMS.registerItem("zephir_air_jar",
            properties -> new AirJar(AirJar.AirType.ZEPHIR, properties));
    public static final DeferredHolder<Item, AirJar> NETHER_AIR_JAR = ITEMS.registerItem("nether_air_jar",
            properties -> new AirJar(AirJar.AirType.NETHER, properties));
    public static final DeferredHolder<Item, AirJar> VOID_AIR_JAR = ITEMS.registerItem("void_air_jar",
            properties -> new AirJar(AirJar.AirType.VOID, properties));
    public static final DeferredHolder<Item, Item> ANEMONE = registerSimpleItem("anemone");
    public static final DeferredHolder<Item, MagicalAnemone> MAGICAL_ANEMONE = ITEMS.registerItem("magical_anemone", MagicalAnemone::new);
    public static final DeferredHolder<Item, WindWand> WIND_WAND = ITEMS.registerItem("wind_wand", WindWand::new);
    
    public static final DeferredHolder<Item, WheatGrains> WHEAT_GRAINS = ITEMS.registerItem("wheat_grains", WheatGrains::new);
    public static final DeferredHolder<Item, Item> WHEAT_STRING = registerSimpleItem("wheat_string");
    public static final DeferredHolder<Item, Item> FLAX = registerSimpleItem("flax");
    public static final DeferredHolder<Item, BlockItem> FLAX_SEEDS = fromBlock("flax_seeds", BlockRegistry.FLAX_CROP);
    public static final DeferredHolder<Item, Item> LINEN_STRING = registerSimpleItem("linen_string");
    public static final DeferredHolder<Item, Item> GOLDEN_STRING = registerSimpleItem("golden_string");
    public static final DeferredHolder<Item, Item> GOLDEN_FLEECE = registerSimpleItem("golden_fleece");
    
    public static final DeferredHolder<Item, Item> LINEN = registerSimpleItem("linen");
    public static final DeferredHolder<Item, Item> GRAINS_MEAL = ITEMS.registerSimpleItem("grains_meal", properties -> properties.food(ModFoods.GRAINS_MEAL).usingConvertsTo(Items.BOWL));

    public static final DeferredHolder<Item, Item> HERBAL_CROWN = ITEMS.registerItem("herbal_crown",
            properties -> new Item(properties.durability(ArmorType.HELMET.getDurability(1)).humanoidArmor(ArmorMaterialRegistry.HERBAL, ArmorType.HELMET)));
    public static final DeferredHolder<Item, Item> FEATHER_HELMET = ITEMS.registerItem("feather_helmet",
    		properties -> new Item(properties.durability(ArmorType.HELMET.getDurability(4)).humanoidArmor(ArmorMaterialRegistry.FEATHER, ArmorType.HELMET)));
    public static final DeferredHolder<Item, Item> GOLDEN_FEATHER_HELMET = ITEMS.registerItem("golden_feather_helmet",
            properties -> new Item(properties.durability(ArmorType.HELMET.getDurability(7)).humanoidArmor(ArmorMaterialRegistry.GOLDEN_FEATHER, ArmorType.HELMET)));
    public static final DeferredHolder<Item, Item> LINEN_CLOAK = ITEMS.registerItem("linen_cloak",
            properties -> new Item(properties.durability(ArmorType.CHESTPLATE.getDurability(4)).humanoidArmor(ArmorMaterialRegistry.LINEN, ArmorType.CHESTPLATE)));
    public static final DeferredHolder<Item, Item> LINEN_UNDERGARMENT = ITEMS.registerItem("linen_undergarment",
            properties -> new Item(properties.durability(ArmorType.LEGGINGS.getDurability(4)).humanoidArmor(ArmorMaterialRegistry.LINEN, ArmorType.LEGGINGS)));
    public static final DeferredHolder<Item, Item> GOLDEN_FLEECE_MANTLE = ITEMS.registerItem("golden_fleece_mantle",
            properties -> new Item(properties.durability(ArmorType.CHESTPLATE.getDurability(7)).humanoidArmor(ArmorMaterialRegistry.GOLDEN_FLEECE, ArmorType.CHESTPLATE)));
    public static final DeferredHolder<Item, BeeswaxArmor> BEESWAX_BOOTS = ITEMS.registerItem("beeswax_boots",
    		properties -> new BeeswaxArmor(ArmorType.BOOTS, properties));

    public static final DeferredHolder<Item, Item> GOLDEN_RAM_SPAWN_EGG = ITEMS.registerItem("golden_ram_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(EntityTypeRegistry.GOLDEN_RAM.get())));
    
    public static final DeferredHolder<Item, FeatherWings> FEATHER_WINGS = ITEMS.registerItem("feather_wings",
    		properties -> new FeatherWings(WingsTypes.FEATHER, properties));
    public static final DeferredHolder<Item, FeatherWings> COLORED_FEATHER_WINGS = ITEMS.registerItem("colored_feather_wings",
    		properties -> new FeatherWings(WingsTypes.FEATHER_COLORED, properties));
    public static final DeferredHolder<Item, FeatherWings> GOLDEN_FEATHER_WINGS = ITEMS.registerItem("golden_feather_wings",
    		properties -> new FeatherWings(WingsTypes.FEATHER_GOLDEN, properties));
    public static final DeferredHolder<Item, AbstractWings> PAPER_WINGS = ITEMS.registerItem("paper_wings",
            properties -> new AbstractWings(WingsTypes.PAPER, properties) {
                @Override public WingsType getType() {return WingsTypes.PAPER;}
            });
    
    public static final DeferredHolder<Item, Item> MAGIC_MEMBRANE = registerSimpleItemSmallStack("magic_membrane");
    public static final DeferredHolder<Item, PhilosopherStone> PHILOSOPHER_STONE = ITEMS.registerItem("philosopher_stone", PhilosopherStone::new);
    
    public static final DeferredHolder<Item, AbstractWings> MAGIC_WINGS = ITEMS.registerItem("magic_wings",
    		properties -> new AbstractWings(WingsTypes.MAGIC, properties) {@Override public WingsType getType() {return WingsTypes.MAGIC;}});
    public static final DeferredHolder<Item, AbstractWings> FLANDRE_MAGIC_WINGS = ITEMS.registerItem("flandre_magic_wings",
    		properties -> new AbstractWings(WingsTypes.PHI_STONE, properties) {@Override public WingsType getType() {return WingsTypes.PHI_STONE;}});
    
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_CORE = ITEMS.registerItem("fallen_relic_core",
    		properties -> new FallenRelic(FallenRelic.RelicType.CORE, false, properties));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_INTERFACE = ITEMS.registerItem("fallen_relic_interface",
    		properties -> new FallenRelic(FallenRelic.RelicType.INTERFACE, false, properties));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_OFFENSIVE = ITEMS.registerItem("fallen_relic_offensive",
    		properties -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE, false, properties));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_DEFENSIVE = ITEMS.registerItem("fallen_relic_defensive",
    		properties -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE, false, properties));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_PROPULSION = ITEMS.registerItem("fallen_relic_propulsion",
    		properties -> new FallenRelic(FallenRelic.RelicType.PROPULSION, false, properties));
    
    public static final DeferredHolder<Item, DaedalusDecryptor> DAEDALUS_MANUSCRIPT = ITEMS.registerItem("daedalus_manuscript",
    		properties -> new DaedalusDecryptor(false, properties));
    public static final DeferredHolder<Item, DaedalusDecryptor> DAEDALUS_DECRYPTOR = ITEMS.registerItem("daedalus_decryptor",
            properties -> new DaedalusDecryptor(true, properties));
    
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_CORE = ITEMS.registerItem("restored_fallen_relic_core",
    		properties -> new FallenRelic(FallenRelic.RelicType.CORE, true, properties));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_INTERFACE = ITEMS.registerItem("restored_fallen_relic_interface",
    		properties -> new FallenRelic(FallenRelic.RelicType.INTERFACE, true, properties));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_OFFENSIVE = ITEMS.registerItem("restored_fallen_relic_offensive",
    		properties -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE, true, properties));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_DEFENSIVE = ITEMS.registerItem("restored_fallen_relic_defensive",
    		properties -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE, true, properties));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_PROPULSION = ITEMS.registerItem("restored_fallen_relic_propulsion",
    		properties -> new FallenRelic(FallenRelic.RelicType.PROPULSION, true, properties));
    
    public static final DeferredHolder<Item, FallenRelic> UPGRADED_FALLEN_RELIC_CORE = ITEMS.registerItem("upgraded_fallen_relic_core",
    		properties -> new FallenRelic(FallenRelic.RelicType.SECOND_GEN_CORE, true, properties));
    
    public static final DeferredHolder<Item, Item> SYNAPSE_REPAIR_KIT = ITEMS.registerSimpleItem("synapse_repair_kit",
            properties -> properties.stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
    public static final DeferredHolder<Item, FoiledItem> SYNAPSE_WINGS_RECHARGER = ITEMS.registerItem("synapse_wings_recharger",
            properties -> new FoiledItem(properties.stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, FoiledItem> QUANTUM_FUEL = ITEMS.registerItem("quantum_fuel",
            properties -> new FoiledItem(properties.stacksTo(4).fireResistant().rarity(Rarity.UNCOMMON)));
    
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_HELMET = ITEMS.registerItem("synapse_helmet",
    		properties -> new SynapseArmor(ArmorType.HELMET, properties));
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_CHESTPLATE = ITEMS.registerItem("synapse_chestplate",
    		properties -> new SynapseArmor(ArmorType.CHESTPLATE, properties));
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_LEGGINGS = ITEMS.registerItem("synapse_leggings",
    		properties -> new SynapseArmor(ArmorType.LEGGINGS, properties));
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_BOOTS = ITEMS.registerItem("synapse_boots",
    		properties -> new SynapseArmor(ArmorType.BOOTS, properties));
    
    public static final DeferredHolder<Item, Item> SYNAPSE_ALLOY_SWORD = ITEMS.registerItem("synapse_alloy_sword",
            properties -> new Item(ModItemTiers.SYNAPSE_ALLOY.applySwordProperties(properties, 3, -2.0f)));
    public static final DeferredHolder<Item, Item> STEEL_PICKAXE = ITEMS.registerItem("steel_pickaxe",
            properties -> new Item(ModItemTiers.STEEL.applyToolProperties(properties, net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE, 1, -2.8f, 0.0f)));

    public static final DeferredHolder<Item, SpearItem> WOODEN_SPEAR = ITEMS.registerItem("wooden_spear", properties -> new SpearItem(ToolMaterial.WOOD, properties));
    public static final DeferredHolder<Item, SpearItem> STONE_SPEAR = ITEMS.registerItem("stone_spear", properties -> new SpearItem(ToolMaterial.STONE, properties));
    public static final DeferredHolder<Item, SpearItem> IRON_SPEAR = ITEMS.registerItem("iron_spear", properties -> new SpearItem(ToolMaterial.IRON, properties));
    public static final DeferredHolder<Item, SpearItem> STEEL_SPEAR = ITEMS.registerItem("steel_spear", properties -> new SpearItem(ModItemTiers.STEEL, properties));
    public static final DeferredHolder<Item, SpearItem> GOLDEN_SPEAR = ITEMS.registerItem("golden_spear", properties -> new SpearItem(ToolMaterial.GOLD, properties));
    public static final DeferredHolder<Item, SpearItem> DIAMOND_SPEAR = ITEMS.registerItem("diamond_spear", properties -> new SpearItem(ToolMaterial.DIAMOND, properties));
    public static final DeferredHolder<Item, SpearItem> NETHERITE_SPEAR = ITEMS.registerItem("netherite_spear", properties -> new SpearItem(ToolMaterial.NETHERITE, properties));
    public static final DeferredHolder<Item, SpearItem> SYNAPSE_ALLOY_SPEAR = ITEMS.registerItem("synapse_alloy_spear", properties -> new SpearItem(ModItemTiers.SYNAPSE_ALLOY, properties));

    public static final DeferredHolder<Item, SynapseWings> IKAROS_WINGS = ITEMS.registerItem("ikaros_wings",
            properties -> new SynapseWings(WingsTypes.SYNAPSE_ALPHA, SynapseWingsSpeedData.ALPHA, 2.0d, SynapseWingsModifiers.ALPHA_MODIFIERS, properties));
    public static final DeferredHolder<Item, SynapseWings> NYMPH_WINGS = ITEMS.registerItem("nymph_wings",
            properties -> new SynapseWings(WingsTypes.SYNAPSE_BETA, SynapseWingsSpeedData.BETA, 1.2d, SynapseWingsModifiers.BETA_MODIFIERS, properties));
    public static final DeferredHolder<Item, SynapseWings> ASTRAEA_WINGS = ITEMS.registerItem("astraea_wings",
            properties -> new SynapseWings(WingsTypes.SYNAPSE_DELTA, SynapseWingsSpeedData.DELTA, 1.4d, SynapseWingsModifiers.DELTA_MODIFIERS, properties));
    public static final DeferredHolder<Item, SynapseWings> CHAOS_WINGS = ITEMS.registerItem("chaos_wings",
            properties -> new SynapseWings(WingsTypes.SYNAPSE_EPSILON, SynapseWingsSpeedData.EPSILON, 1.8f, SynapseWingsModifiers.EPSILON_MODIFIERS, properties));
    public static final DeferredHolder<Item, SynapseWings> HIYORI_WINGS = ITEMS.registerItem("hiyori_wings",
            properties -> new SynapseWings(WingsTypes.SYNAPSE_ZETA, SynapseWingsSpeedData.ZETA, 2.0f, SynapseWingsModifiers.ZETA_MODIFIERS, properties));
    public static final DeferredHolder<Item, SynapseWings> MELAN_WINGS = ITEMS.registerItem("melan_wings",
            properties -> new SynapseWings(WingsTypes.SYNAPSE_THETA, SynapseWingsSpeedData.THETA, 2.0f, SynapseWingsModifiers.THETA_MODIFIERS, properties));

    public static final DeferredHolder<Item, ArtemisLauncher> ARTEMIS_LAUNCHER = ITEMS.registerItem("artemis_launcher", ArtemisLauncher::new);
    public static final DeferredHolder<Item, Item> ARTEMIS_MISSILE = registerSimpleItem("artemis_missile");
    public static final DeferredHolder<Item, TimeRiftGenerator> TIME_RIFT_GENERATOR = ITEMS.registerItem("time_rift_generator", TimeRiftGenerator::new);
    public static final DeferredHolder<Item, TimeRiftCharge> TIME_RIFT_CHARGE = ITEMS.registerItem("time_rift_charge", TimeRiftCharge::new);
    public static final DeferredHolder<Item, Demeter> DEMETER = ITEMS.registerItem("demeter", Demeter::new);
    public static final DeferredHolder<Item, TimeRiftCharge> DEMETER_CHARGE = ITEMS.registerItem("demeter_charge", properties -> new TimeRiftCharge(true, properties));

    public static final DeferredHolder<Item, BaseTransportCard> TRANSPORT_CARD_BASE = ITEMS.registerItem("transport_card_base", BaseTransportCard::new);
    public static final DeferredHolder<Item, ArtemisTransportCard> TRANSPORT_CARD_ARTEMIS_HOMING = ITEMS.registerItem("transport_card_artemis_homing",
            properties -> new ArtemisTransportCard(AbstractTransportCard.CardType.ARTEMIS_HOMING, properties));
    public static final DeferredHolder<Item, ArtemisTransportCard> TRANSPORT_CARD_ARTEMIS_SCATTER = ITEMS.registerItem("transport_card_artemis_scatter",
            properties -> new ArtemisTransportCard(AbstractTransportCard.CardType.ARTEMIS_SCATTER, properties));
    public static final DeferredHolder<Item, ChronoExplosionTransportCard> TRANSPORT_CARD_CHRONO_EXPLOSION = ITEMS.registerItem("transport_card_chrono_explosion",
            ChronoExplosionTransportCard::new);
    public static final DeferredHolder<Item, TeleportTransportCard> TRANSPORT_CARD_CHRONO_TELEPORT = ITEMS.registerItem("transport_card_chrono_teleport",
            TeleportTransportCard::new);
    public static final DeferredHolder<Item, MaskingFieldTransportCard> TRANSPORT_CARD_MASKING_FIELD = ITEMS.registerItem("transport_card_masking_field",
            MaskingFieldTransportCard::new);
    public static final DeferredHolder<Item, InterdictionFieldTransportCard> TRANSPORT_CARD_INTERDICTION_FIELD = ITEMS.registerItem("transport_card_interdiction_field",
            InterdictionFieldTransportCard::new);
    public static final DeferredHolder<Item, TreasureTransportCard> TRANSPORT_CARD_TREASURE = ITEMS.registerItem("transport_card_treasure",
            TreasureTransportCard::new);

    public static final DeferredHolder<Item, Item> DISC_FALLEN_DOWN = ITEMS.registerSimpleItem("disc_fallen_down",
    		properties -> properties.rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(ModJukeboxSongs.FALLEN_DOWN));
    public static final DeferredHolder<Item, Item> DISC_RING_MY_BELL = ITEMS.registerSimpleItem("disc_ring_my_bell",
            properties -> properties.rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(ModJukeboxSongs.RING_MY_BELL));
    
    public static final DeferredHolder<Item, MelonItem> MELON = ITEMS.registerItem("melon", MelonItem::new);
    public static final DeferredHolder<Item, IconBadge> ICON_BADGE = ITEMS.registerItem("icon_badge", IconBadge::new);

    private static DeferredHolder<Item, Item> registerSimpleItem(String name){
        return ITEMS.registerSimpleItem(name);
    }

    private static DeferredHolder<Item, Item> registerSimpleItemSmallStack(String name){
        return ITEMS.registerSimpleItem(name, properties -> properties.stacksTo(16));
    }

    private static <B extends Block> DeferredHolder<Item, BlockItem> fromBlock(String name, Supplier<B> block) {
        return ITEMS.registerSimpleBlockItem(name, block);
    }

}



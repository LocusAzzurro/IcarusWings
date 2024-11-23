package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModFoods;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModItemTiers;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModJukeboxSongs;
import org.mineplugin.locusazzurro.icaruswings.common.item.*;
import org.mineplugin.locusazzurro.icaruswings.common.item.transportcard.*;
import org.mineplugin.locusazzurro.icaruswings.common.item.wings.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, IcarusWings.MOD_ID);
    
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

    public static final DeferredHolder<Item, GoldUpgradeSmithingTemplate> GOLD_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("gold_upgrade_smithing_template", GoldUpgradeSmithingTemplate::new);
    public static final DeferredHolder<Item, Item> REFORGED_NETHERITE_INGOT = registerSimpleItem("reforged_netherite_ingot");
    public static final DeferredHolder<Item, Item> SYNAPSE_ALLOY_INGOT = registerSimpleItem("synapse_alloy_ingot");
    
    public static final DeferredHolder<Item, Item> BEESWAX = ITEMS.register("beeswax",
            () -> new Item(new Item.Properties().food(ModFoods.BEESWAX)));
    public static final DeferredHolder<Item, BlockItem> BEESWAX_BLOCK = fromBlock("beeswax_block", BlockRegistry.BEESWAX_BLOCK);
    public static final DeferredHolder<Item, Item> REFINED_BEESWAX_BAR = registerSimpleItem("refined_beeswax_bar");
    public static final DeferredHolder<Item, Item> GLISTERING_REFINED_BEESWAX_BAR = ITEMS.register("glistering_refined_beeswax_bar",
    		() -> new Item(new Item.Properties()){@Override public boolean isPiglinCurrency(ItemStack s) {return true;}});
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
    public static final DeferredHolder<Item, BucketItem> GREEK_FIRE_BUCKET = ITEMS.register("greek_fire_bucket",
            () -> new BucketItem(FluidRegistry.GREEK_FIRE.get(), new Item.Properties().craftRemainder(Items.BUCKET)));

    public static final DeferredHolder<Item, GlassJar> GLASS_JAR = ITEMS.register("glass_jar", GlassJar::new);

    public static final DeferredHolder<Item, Mead> MEAD = ITEMS.register("mead", () -> new Mead());
    public static final DeferredHolder<Item, Mead> ZEPHIR_INFUSED_MEAD = ITEMS.register("zephir_infused_mead",
    		() -> new Mead(Mead.Infusion.ZEPHIR));
    public static final DeferredHolder<Item, Mead> NETHER_INFUSED_MEAD = ITEMS.register("nether_infused_mead",
    		() -> new Mead(Mead.Infusion.NETHER));
    public static final DeferredHolder<Item, Mead> VOID_INFUSED_MEAD = ITEMS.register("void_infused_mead",
    		() -> new Mead(Mead.Infusion.VOID));
    public static final DeferredHolder<Item, Mead> GOLDEN_APPLE_INFUSED_MEAD = ITEMS.register("golden_apple_infused_mead",
    		() -> new Mead(Mead.Infusion.GOLDEN_APPLE));
    public static final DeferredHolder<Item, Mead> GOLDEN_APPLE_GROWTH_INFUSED_MEAD = ITEMS.register("golden_apple_growth_infused_mead",
            () -> new Mead(Mead.Infusion.GOLDEN_APPLE_GROWTH));
    public static final DeferredHolder<Item, Mead> HERBS_INFUSED_MEAD = ITEMS.register("herbs_infused_mead",
    		() -> new Mead(Mead.Infusion.HERBS));

    public static final DeferredHolder<Item, AirJar> ZEPHIR_AIR_JAR = ITEMS.register("zephir_air_jar",
            () -> new AirJar(AirJar.AirType.ZEPHIR));
    public static final DeferredHolder<Item, AirJar> NETHER_AIR_JAR = ITEMS.register("nether_air_jar",
            () -> new AirJar(AirJar.AirType.NETHER));
    public static final DeferredHolder<Item, AirJar> VOID_AIR_JAR = ITEMS.register("void_air_jar",
            () -> new AirJar(AirJar.AirType.VOID));
    public static final DeferredHolder<Item, Item> ANEMONE = registerSimpleItem("anemone");
    public static final DeferredHolder<Item, MagicalAnemone> MAGICAL_ANEMONE = ITEMS.register("magical_anemone", MagicalAnemone::new);
    public static final DeferredHolder<Item, WindWand> WIND_WAND = ITEMS.register("wind_wand", WindWand::new);
    
    public static final DeferredHolder<Item, WheatGrains> WHEAT_GRAINS = ITEMS.register("wheat_grains", WheatGrains::new);
    public static final DeferredHolder<Item, Item> WHEAT_STRING = registerSimpleItem("wheat_string");
    public static final DeferredHolder<Item, Item> FLAX = registerSimpleItem("flax");
    public static final DeferredHolder<Item, ItemNameBlockItem> FLAX_SEEDS = ITEMS.register("flax_seeds", () -> new ItemNameBlockItem(BlockRegistry.FLAX_CROP.get(), new Item.Properties()));
    public static final DeferredHolder<Item, Item> LINEN_STRING = registerSimpleItem("linen_string");
    public static final DeferredHolder<Item, Item> GOLDEN_STRING = registerSimpleItem("golden_string");
    public static final DeferredHolder<Item, Item> GOLDEN_FLEECE = registerSimpleItem("golden_fleece");
    
    public static final DeferredHolder<Item, Item> LINEN = registerSimpleItem("linen");
    public static final DeferredHolder<Item, Item> GRAINS_MEAL = ITEMS.register("grains_meal",
            () -> new Item(new Item.Properties().food(ModFoods.GRAINS_MEAL)));

    public static final DeferredHolder<Item, ArmorItem> HERBAL_CROWN = ITEMS.register("herbal_crown",
            () -> new ArmorItem(ArmorMaterialRegistry.HERBAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(1))));
    public static final DeferredHolder<Item, ArmorItem> FEATHER_HELMET = ITEMS.register("feather_helmet",
    		() -> new ArmorItem(ArmorMaterialRegistry.FEATHER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(4))));
    public static final DeferredHolder<Item, ArmorItem> GOLDEN_FEATHER_HELMET = ITEMS.register("golden_feather_helmet",
            () -> new ArmorItem(ArmorMaterialRegistry.GOLDEN_FEATHER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(7))));
    public static final DeferredHolder<Item, ArmorItem> LINEN_CLOAK = ITEMS.register("linen_cloak",
            () -> new ArmorItem(ArmorMaterialRegistry.LINEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(4))));
    public static final DeferredHolder<Item, ArmorItem> LINEN_UNDERGARMENT = ITEMS.register("linen_undergarment",
            () -> new ArmorItem(ArmorMaterialRegistry.LINEN, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(4))));
    public static final DeferredHolder<Item, ArmorItem> GOLDEN_FLEECE_MANTLE = ITEMS.register("golden_fleece_mantle",
            () -> new ArmorItem(ArmorMaterialRegistry.GOLDEN_FLEECE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(7))));
    public static final DeferredHolder<Item, BeeswaxArmor> BEESWAX_BOOTS = ITEMS.register("beeswax_boots",
    		() -> new BeeswaxArmor(ArmorItem.Type.BOOTS));

    public static final DeferredHolder<Item, DeferredSpawnEggItem> GOLDEN_RAM_SPAWN_EGG = ITEMS.register("golden_ram_spawn_egg",
            () -> new DeferredSpawnEggItem(EntityTypeRegistry.GOLDEN_RAM, 15198183, 0xf2f1aa, new Item.Properties()));
    
    public static final DeferredHolder<Item, FeatherWings> FEATHER_WINGS = ITEMS.register("feather_wings",
    		() -> new FeatherWings(WingsTypes.FEATHER));
    public static final DeferredHolder<Item, FeatherWings> COLORED_FEATHER_WINGS = ITEMS.register("colored_feather_wings",
    		() -> new FeatherWings(WingsTypes.FEATHER_COLORED));
    public static final DeferredHolder<Item, FeatherWings> GOLDEN_FEATHER_WINGS = ITEMS.register("golden_feather_wings",
    		() -> new FeatherWings(WingsTypes.FEATHER_GOLDEN));
    public static final DeferredHolder<Item, AbstractWings> PAPER_WINGS = ITEMS.register("paper_wings",
            () -> new AbstractWings() {
                @Override public WingsType getType() {return WingsTypes.PAPER;}
                @Override public boolean canElytraFly(ItemStack stack, LivingEntity entity) {return true;}
            });
    
    public static final DeferredHolder<Item, Item> MAGIC_MEMBRANE = registerSimpleItemSmallStack("magic_membrane");
    public static final DeferredHolder<Item, PhilosopherStone> PHILOSOPHER_STONE = ITEMS.register("philosopher_stone", PhilosopherStone::new);
    
    public static final DeferredHolder<Item, AbstractWings> MAGIC_WINGS = ITEMS.register("magic_wings",
    		() -> new AbstractWings() {@Override public WingsType getType() {return WingsTypes.MAGIC;}});
    public static final DeferredHolder<Item, AbstractWings> FLANDRE_MAGIC_WINGS = ITEMS.register("flandre_magic_wings",
    		() -> new AbstractWings() {@Override public WingsType getType() {return WingsTypes.PHI_STONE;}});
    
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_CORE = ITEMS.register("fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_INTERFACE = ITEMS.register("fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_OFFENSIVE = ITEMS.register("fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_DEFENSIVE = ITEMS.register("fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE));
    public static final DeferredHolder<Item, FallenRelic> FALLEN_RELIC_PROPULSION = ITEMS.register("fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION));
    
    public static final DeferredHolder<Item, DaedalusDecryptor> DAEDALUS_MANUSCRIPT = ITEMS.register("daedalus_manuscript",
    		() -> new DaedalusDecryptor(false));
    public static final DeferredHolder<Item, DaedalusDecryptor> DAEDALUS_DECRYPTOR = ITEMS.register("daedalus_decryptor",
            () -> new DaedalusDecryptor(true));
    
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_CORE = ITEMS.register("restored_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.CORE, true));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_INTERFACE = ITEMS.register("restored_fallen_relic_interface",
    		() -> new FallenRelic(FallenRelic.RelicType.INTERFACE, true));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_OFFENSIVE = ITEMS.register("restored_fallen_relic_offensive",
    		() -> new FallenRelic(FallenRelic.RelicType.OFFENSIVE, true));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_DEFENSIVE = ITEMS.register("restored_fallen_relic_defensive",
    		() -> new FallenRelic(FallenRelic.RelicType.DEFENSIVE, true));
    public static final DeferredHolder<Item, FallenRelic> RESTORED_FALLEN_RELIC_PROPULSION = ITEMS.register("restored_fallen_relic_propulsion",
    		() -> new FallenRelic(FallenRelic.RelicType.PROPULSION, true));
    
    public static final DeferredHolder<Item, FallenRelic> UPGRADED_FALLEN_RELIC_CORE = ITEMS.register("upgraded_fallen_relic_core",
    		() -> new FallenRelic(FallenRelic.RelicType.SECOND_GEN_CORE, true));
    
    public static final DeferredHolder<Item, Item> SYNAPSE_REPAIR_KIT = ITEMS.register("synapse_repair_kit",
            () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, FoiledItem> SYNAPSE_WINGS_RECHARGER = ITEMS.register("synapse_wings_recharger",
            () -> new FoiledItem(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final DeferredHolder<Item, FoiledItem> QUANTUM_FUEL = ITEMS.register("quantum_fuel",
            () -> new FoiledItem(new Item.Properties().stacksTo(4).fireResistant().rarity(Rarity.UNCOMMON)));
    
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_HELMET = ITEMS.register("synapse_helmet",
    		() -> new SynapseArmor(ArmorItem.Type.HELMET));
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_CHESTPLATE = ITEMS.register("synapse_chestplate",
    		() -> new SynapseArmor(ArmorItem.Type.CHESTPLATE));
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_LEGGINGS = ITEMS.register("synapse_leggings",
    		() -> new SynapseArmor(ArmorItem.Type.LEGGINGS));
    public static final DeferredHolder<Item, SynapseArmor> SYNAPSE_BOOTS = ITEMS.register("synapse_boots",
    		() -> new SynapseArmor(ArmorItem.Type.BOOTS));
    
    public static final DeferredHolder<Item, SwordItem> SYNAPSE_ALLOY_SWORD = ITEMS.register("synapse_alloy_sword",
            () -> new SwordItem(ModItemTiers.SYNAPSE_ALLOY, new Item.Properties().attributes(SwordItem.createAttributes(ModItemTiers.SYNAPSE_ALLOY, 3, -2.0f))));
    public static final DeferredHolder<Item, PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModItemTiers.STEEL, new Item.Properties().attributes(PickaxeItem.createAttributes(ModItemTiers.STEEL, 1, -2.8f))));

    public static final DeferredHolder<Item, SpearItem> WOODEN_SPEAR = ITEMS.register("wooden_spear", () -> new SpearItem(Tiers.WOOD));
    public static final DeferredHolder<Item, SpearItem> STONE_SPEAR = ITEMS.register("stone_spear", () -> new SpearItem(Tiers.STONE));
    public static final DeferredHolder<Item, SpearItem> IRON_SPEAR = ITEMS.register("iron_spear", () -> new SpearItem(Tiers.IRON));
    public static final DeferredHolder<Item, SpearItem> STEEL_SPEAR = ITEMS.register("steel_spear", () -> new SpearItem(ModItemTiers.STEEL));
    public static final DeferredHolder<Item, SpearItem> GOLDEN_SPEAR = ITEMS.register("golden_spear", () -> new SpearItem(Tiers.GOLD));
    public static final DeferredHolder<Item, SpearItem> DIAMOND_SPEAR = ITEMS.register("diamond_spear", () -> new SpearItem(Tiers.DIAMOND));
    public static final DeferredHolder<Item, SpearItem> NETHERITE_SPEAR = ITEMS.register("netherite_spear", () -> new SpearItem(Tiers.NETHERITE));
    public static final DeferredHolder<Item, SpearItem> SYNAPSE_ALLOY_SPEAR = ITEMS.register("synapse_alloy_spear", () -> new SpearItem(ModItemTiers.SYNAPSE_ALLOY));

    public static final DeferredHolder<Item, SynapseWings> IKAROS_WINGS = ITEMS.register("ikaros_wings",
            () -> new SynapseWings(WingsTypes.SYNAPSE_ALPHA, SynapseWingsSpeedData.ALPHA, 2.0d, SynapseWingsModifiers.ALPHA_MODIFIERS));
    public static final DeferredHolder<Item, SynapseWings> NYMPH_WINGS = ITEMS.register("nymph_wings",
            () -> new SynapseWings(WingsTypes.SYNAPSE_BETA, SynapseWingsSpeedData.BETA, 1.2d, SynapseWingsModifiers.BETA_MODIFIERS));
    public static final DeferredHolder<Item, SynapseWings> ASTRAEA_WINGS = ITEMS.register("astraea_wings",
            () -> new SynapseWings(WingsTypes.SYNAPSE_DELTA, SynapseWingsSpeedData.DELTA, 1.4d, SynapseWingsModifiers.DELTA_MODIFIERS));
    public static final DeferredHolder<Item, SynapseWings> CHAOS_WINGS = ITEMS.register("chaos_wings",
            () -> new SynapseWings(WingsTypes.SYNAPSE_EPSILON, SynapseWingsSpeedData.EPSILON, 1.8f, SynapseWingsModifiers.EPSILON_MODIFIERS));
    public static final DeferredHolder<Item, SynapseWings> HIYORI_WINGS = ITEMS.register("hiyori_wings",
            () -> new SynapseWings(WingsTypes.SYNAPSE_ZETA, SynapseWingsSpeedData.ZETA, 2.0f, SynapseWingsModifiers.ZETA_MODIFIERS));
    public static final DeferredHolder<Item, SynapseWings> MELAN_WINGS = ITEMS.register("melan_wings",
            () -> new SynapseWings(WingsTypes.SYNAPSE_THETA, SynapseWingsSpeedData.THETA, 2.0f, SynapseWingsModifiers.THETA_MODIFIERS));

    public static final DeferredHolder<Item, ArtemisLauncher> ARTEMIS_LAUNCHER = ITEMS.register("artemis_launcher", ArtemisLauncher::new);
    public static final DeferredHolder<Item, Item> ARTEMIS_MISSILE = registerSimpleItem("artemis_missile");
    public static final DeferredHolder<Item, TimeRiftGenerator> TIME_RIFT_GENERATOR = ITEMS.register("time_rift_generator", TimeRiftGenerator::new);
    public static final DeferredHolder<Item, TimeRiftCharge> TIME_RIFT_CHARGE = ITEMS.register("time_rift_charge", () -> new TimeRiftCharge());
    public static final DeferredHolder<Item, Demeter> DEMETER = ITEMS.register("demeter", Demeter::new);
    public static final DeferredHolder<Item, TimeRiftCharge> DEMETER_CHARGE = ITEMS.register("demeter_charge", () -> new TimeRiftCharge(true));

    public static final DeferredHolder<Item, BaseTransportCard> TRANSPORT_CARD_BASE = ITEMS.register("transport_card_base", BaseTransportCard::new);
    public static final DeferredHolder<Item, ArtemisTransportCard> TRANSPORT_CARD_ARTEMIS_HOMING = ITEMS.register("transport_card_artemis_homing",
            () -> new ArtemisTransportCard(AbstractTransportCard.CardType.ARTEMIS_HOMING));
    public static final DeferredHolder<Item, ArtemisTransportCard> TRANSPORT_CARD_ARTEMIS_SCATTER = ITEMS.register("transport_card_artemis_scatter",
            () -> new ArtemisTransportCard(AbstractTransportCard.CardType.ARTEMIS_SCATTER));
    public static final DeferredHolder<Item, ChronoExplosionTransportCard> TRANSPORT_CARD_CHRONO_EXPLOSION = ITEMS.register("transport_card_chrono_explosion",
            ChronoExplosionTransportCard::new);
    public static final DeferredHolder<Item, TeleportTransportCard> TRANSPORT_CARD_CHRONO_TELEPORT = ITEMS.register("transport_card_chrono_teleport",
            TeleportTransportCard::new);
    public static final DeferredHolder<Item, MaskingFieldTransportCard> TRANSPORT_CARD_MASKING_FIELD = ITEMS.register("transport_card_masking_field",
            MaskingFieldTransportCard::new);
    public static final DeferredHolder<Item, InterdictionFieldTransportCard> TRANSPORT_CARD_INTERDICTION_FIELD = ITEMS.register("transport_card_interdiction_field",
            InterdictionFieldTransportCard::new);
    public static final DeferredHolder<Item, TreasureTransportCard> TRANSPORT_CARD_TREASURE = ITEMS.register("transport_card_treasure",
            TreasureTransportCard::new);

    public static final DeferredHolder<Item, Item> DISC_FALLEN_DOWN = ITEMS.register("disc_fallen_down",
    		() -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(ModJukeboxSongs.FALLEN_DOWN)));
    public static final DeferredHolder<Item, Item> DISC_RING_MY_BELL = ITEMS.register("disc_ring_my_bell",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(ModJukeboxSongs.RING_MY_BELL)));
    
    public static final DeferredHolder<Item, MelonItem> MELON = ITEMS.register("melon", MelonItem::new);
    public static final DeferredHolder<Item, IconBadge> ICON_BADGE = ITEMS.register("icon_badge", IconBadge::new);

    private static DeferredHolder<Item, Item> registerItem(String name, DeferredHolder<Item, Item> item){
        return ITEMS.register(name, item);
    }

    private static DeferredHolder<Item, Item> registerSimpleItem(String name){
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static DeferredHolder<Item, Item> registerSimpleItemSmallStack(String name){
        return ITEMS.register(name, () -> new Item(new Item.Properties().stacksTo(16)));
    }

    private static <B extends Block> DeferredHolder<Item, BlockItem> fromBlock(String name, Supplier<B> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}



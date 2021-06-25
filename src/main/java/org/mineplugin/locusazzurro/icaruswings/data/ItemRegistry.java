package org.mineplugin.locusazzurro.icaruswings.data;

import org.mineplugin.locusazzurro.icaruswings.items.*;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Item> redFeather = ITEMS.register("red_feather", ColoredFeather::new);
    public static final RegistryObject<Item> blueFeather = ITEMS.register("blue_feather", ColoredFeather::new);
    public static final RegistryObject<Item> greenFeather = ITEMS.register("green_feather", ColoredFeather::new);
    public static final RegistryObject<Item> cyanFeather = ITEMS.register("cyan_feather", ColoredFeather::new);
    public static final RegistryObject<Item> grayFeather = ITEMS.register("gray_feather", ColoredFeather::new);
    
    public static final RegistryObject<Item> featherBunch = ITEMS.register("feather_bunch", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER));
    public static final RegistryObject<Item> coloredFeatherBunch = ITEMS.register("colored_feather_bunch", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER_COLORED));
    public static final RegistryObject<Item> goldenFeatherBunch = ITEMS.register("golden_feather_bunch", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER_GOLDEN));
    
    public static final RegistryObject<Item> beeswax = ITEMS.register("beeswax", Beeswax::new);
    public static final RegistryObject<Item> beeswaxBlock = ITEMS.register("beeswax_block", 
    		() -> new BlockItem(BlockRegistry.beeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxBar = ITEMS.register("refined_beeswax_bar", RefinedBeeswaxBar::new);
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
    
    public static final RegistryObject<Item> meadPot = ITEMS.register("mead_pot",
    		() -> new BlockItem(BlockRegistry.meadPot.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    
    public static final RegistryObject<Item> mead = ITEMS.register("mead", Mead::new);
    public static final RegistryObject<Item> wheatGrains = ITEMS.register("wheat_grains", WheatGrains::new);
    public static final RegistryObject<Item> wheatString = ITEMS.register("wheat_string", WheatString::new);
    
    public static final RegistryObject<Item> featherWings = ITEMS.register("feather_wings", FeatherWings::new);
    public static final RegistryObject<Item> coloredFeatherWings = ITEMS.register("colored_feather_wings", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER_COLORED));
    public static final RegistryObject<Item> goldenFeatherWings = ITEMS.register("golden_feather_wings", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER_GOLDEN));
    
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
    
    public static final RegistryObject<Item> synapseRepairKit = ITEMS.register("synapse_repair_kit", SynapseRepairKit::new);
    
    public static final RegistryObject<Item> synapseHelmet = ITEMS.register("synapse_helmet",
    		() -> new SynapseArmor(EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> synapseChestplate = ITEMS.register("synapse_chestplate",
    		() -> new SynapseArmor(EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> synapseLeggings = ITEMS.register("synapse_leggings",
    		() -> new SynapseArmor(EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> synapseBoots = ITEMS.register("synapse_boots",
    		() -> new SynapseArmor(EquipmentSlotType.FEET));
    
    public static final RegistryObject<Item> ikarosWings = ITEMS.register("ikaros_wings", IkarosWings::new);
    

}



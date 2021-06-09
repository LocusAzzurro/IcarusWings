package org.mineplugin.locusazzurro.icaruswings.data;

import org.mineplugin.locusazzurro.icaruswings.items.*;

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
    
    public static final RegistryObject<Item> beeswax = ITEMS.register("beeswax", Beeswax::new);
    public static final RegistryObject<Item> beeswaxBlock = ITEMS.register("beeswax_block", 
    		() -> new BlockItem(BlockRegistry.beeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> refinedBeeswaxBlock = ITEMS.register("refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.refinedBeeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> smoothRefinedBeeswaxBlock = ITEMS.register("smooth_refined_beeswax_block",
    		() -> new BlockItem(BlockRegistry.smoothRefinedBeeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    
    public static final RegistryObject<Item> wheatGrains = ITEMS.register("wheat_grains", WheatGrains::new);
    
    public static final RegistryObject<Item> featherWings = ITEMS.register("feather_wings", FeatherWings::new);
    public static final RegistryObject<Item> coloredFeatherWings = ITEMS.register("colored_feather_wings", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER_COLORED));
    
    public static final RegistryObject<Item> ikarosWings = ITEMS.register("ikaros_wings", IkarosWings::new);
    

}



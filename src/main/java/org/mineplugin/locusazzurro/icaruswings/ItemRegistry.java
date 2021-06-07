package org.mineplugin.locusazzurro.icaruswings;

import org.mineplugin.locusazzurro.icaruswings.items.*;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Item> blueFeather = ITEMS.register("blue_feather", BlueFeather::new);
    public static final RegistryObject<Item> beeswax = ITEMS.register("beeswax", Beeswax::new);
    public static final RegistryObject<Item> beeswaxBlock = ITEMS.register("beeswax_block", 
    		() -> new BlockItem(BlockRegistry.beeswaxBlock.get(), new Item.Properties().tab(ModGroup.itemGroup)));
    public static final RegistryObject<Item> featherWings = ITEMS.register("feather_wings", FeatherWings::new);
    public static final RegistryObject<Item> coloredFeatherWings = ITEMS.register("colored_feather_wings", 
    		() -> new FeatherWings(FeatherWings.FeatherWingsType.FEATHER_COLORED));
    

}



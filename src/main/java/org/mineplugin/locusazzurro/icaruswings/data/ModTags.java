package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static final TagKey<Item> COLORED_FEATHERS = createItemTag("colored_feathers");
    public static final TagKey<Item> HERBS = createItemTag("herbs");
    public static final TagKey<Item> FALLEN_RELICS = createItemTag("fallen_relics");
    public static final TagKey<Item> RESTORED_FALLEN_RELICS = createItemTag("restored_fallen_relics");
    public static final TagKey<Item> WORLD_ESSENCES = createItemTag("world_essences");
    public static final TagKey<Item> WORLD_INGOTS = createItemTag("world_ingots");
    public static final TagKey<Item> SYNAPSE_ARMOR = createItemTag("synapse_armor");

    private static TagKey<Item> createItemTag(String path){
        return ItemTags.create(new ResourceLocation(ModData.MOD_ID, path));
    }


}

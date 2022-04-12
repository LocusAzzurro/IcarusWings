package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static final TagKey<Item> COLORED_FEATHERS = createItemTag("colored_feathers");

    private static TagKey<Item> createItemTag(String path){
        return ItemTags.create(new ResourceLocation(ModData.MOD_ID, path));
    }


}

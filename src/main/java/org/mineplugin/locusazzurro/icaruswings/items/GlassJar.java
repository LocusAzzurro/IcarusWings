package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.item.Item;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class GlassJar extends Item{

    public GlassJar(){
        super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(16));
    }
}

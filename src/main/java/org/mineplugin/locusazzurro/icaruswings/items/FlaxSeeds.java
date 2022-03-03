package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class FlaxSeeds extends BlockNamedItem {

    public FlaxSeeds() {
        super(BlockRegistry.flaxCrop.get(), new Item.Properties().tab(ModGroup.itemGroup));
    }
}

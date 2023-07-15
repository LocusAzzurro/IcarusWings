package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class FlaxSeeds extends ItemNameBlockItem {

    public FlaxSeeds() {
        super(BlockRegistry.flaxCrop.get(), new Item.Properties());
    }
}

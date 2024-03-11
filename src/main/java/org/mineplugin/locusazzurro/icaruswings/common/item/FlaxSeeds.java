package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

public class FlaxSeeds extends ItemNameBlockItem {

    public FlaxSeeds() {
        super(BlockRegistry.FLAX_CROP.get(), new Item.Properties());
    }
}

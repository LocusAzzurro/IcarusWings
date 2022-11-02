package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraftforge.common.ForgeSpawnEggItem;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

public class GoldenRamSpawnEgg extends ForgeSpawnEggItem {


    public GoldenRamSpawnEgg() {
        super(EntityTypeRegistry.goldenRamEntity,
                15198183, 0xf2f1aa,
                new Properties().tab(ModGroup.itemGroup));
    }
}

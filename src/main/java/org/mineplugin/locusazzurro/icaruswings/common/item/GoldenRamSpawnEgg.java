package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

public class GoldenRamSpawnEgg extends DeferredSpawnEggItem {


    public GoldenRamSpawnEgg() {
        super(EntityTypeRegistry.GOLDEN_RAM,
                15198183, 0xf2f1aa,
                new Properties());
    }
}

package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.EntityTypeRegistry;

import java.util.function.Supplier;

public class GoldenRamSpawnEgg extends ForgeSpawnEggItem {


    public GoldenRamSpawnEgg() {
        super(EntityTypeRegistry.goldenRamEntity,
                15198183, 0xf2f1aa,
                new Properties().tab(ModGroup.itemGroup));
    }
}

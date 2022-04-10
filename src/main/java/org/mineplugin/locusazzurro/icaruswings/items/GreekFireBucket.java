package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;

public class GreekFireBucket extends BucketItem {

    public GreekFireBucket() {
        super(FluidRegistry.greekFire, new Properties().tab(ModGroup.itemGroup).craftRemainder(Items.BUCKET));
    }
}

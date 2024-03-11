package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Items;
import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;

public class GreekFireBucket extends BucketItem {

    public GreekFireBucket() {
        super(FluidRegistry.greekFire, new Properties().craftRemainder(Items.BUCKET));
    }
}

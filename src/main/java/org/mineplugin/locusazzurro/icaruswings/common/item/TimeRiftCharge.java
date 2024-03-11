package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TimeRiftCharge extends Item {

    private boolean advanced = false;

    public TimeRiftCharge(){
        super(new Item.Properties());
    }

    public TimeRiftCharge(boolean advanced){
        this();
        this.advanced = advanced;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return this.advanced;
    }
}

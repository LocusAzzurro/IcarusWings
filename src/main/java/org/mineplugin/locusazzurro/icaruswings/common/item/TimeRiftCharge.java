package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TimeRiftCharge extends Item {

    private boolean advanced = false;

    public TimeRiftCharge(){
        this(new Item.Properties());
    }

    public TimeRiftCharge(Item.Properties properties){
        super(properties);
    }

    public TimeRiftCharge(boolean advanced){
        this();
        this.advanced = advanced;
    }

    public TimeRiftCharge(boolean advanced, Item.Properties properties){
        this(properties);
        this.advanced = advanced;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return this.advanced;
    }
}

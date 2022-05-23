package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class TimeRiftCharge extends Item {

    private boolean advanced = false;

    public TimeRiftCharge(){
        super(new Item.Properties().tab(ModGroup.itemGroup));
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

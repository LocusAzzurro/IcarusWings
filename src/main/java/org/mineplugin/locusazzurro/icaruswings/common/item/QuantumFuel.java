package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class QuantumFuel extends Item {

	public QuantumFuel() {
		super(new Properties().stacksTo(4).rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return true;
	}
	
}

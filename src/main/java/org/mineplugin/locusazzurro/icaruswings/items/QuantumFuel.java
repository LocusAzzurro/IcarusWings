package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

@Mod.EventBusSubscriber()
public class QuantumFuel extends Item {

	public QuantumFuel() {
		super(new Properties().tab(ModGroup.itemGroup).stacksTo(4).rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return true;
	}
	
}

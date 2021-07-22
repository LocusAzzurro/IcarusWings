package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import net.minecraft.item.ItemStack;

public class PaperWings extends AbstractWings{
	
	public PaperWings() {
		super(WingsType.PAPER);
	}
	
	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
		return true;
	}
}

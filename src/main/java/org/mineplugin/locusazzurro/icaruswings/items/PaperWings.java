package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

public class PaperWings extends AbstractWings{
	
	public PaperWings() {
		super(WingsType.PAPER);
	}
	
	@Override
	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return true;
	}
}

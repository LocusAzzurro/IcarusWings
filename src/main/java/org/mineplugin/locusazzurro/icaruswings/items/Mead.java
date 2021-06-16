package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class Mead extends Item{

	public Mead() {
		super(new Properties().tab(ModGroup.itemGroup).food(food).stacksTo(4));
	}
	private static final Food food = (new Food.Builder())
			.saturationMod(1)
			.nutrition(5)
			.alwaysEat()
			.build();
	//TODO: nausea/regen effect and poison cure, switch to drink type animation
}

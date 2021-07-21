package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.UUID;

import org.mineplugin.locusazzurro.icaruswings.data.Utils;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsTranslucent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

public class SynapseWingsDelta extends AbstractWings implements IWingsExpandable, IWingsTranslucent{

	private final UUID MODIFIER_UUID = Utils.ARMOR_MODIFIER_UUID_PER_SLOT[2];
	
	public SynapseWingsDelta() {
		super(WingsType.SYNAPSE_DELTA, Rarity.RARE);
	}

	@Override
	public float getExpansionFactor() {
		return 1.4f;
	}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 0.0f;
	}
	
}

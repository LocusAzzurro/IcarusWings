package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsTranslucent;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SynapseWingsBeta extends SynapseWings implements IWingsExpandable, IWingsTranslucent{
	
	public SynapseWingsBeta() {
		super(WingsType.SYNAPSE_BETA);
	}

	@Override
	protected double getDirectSpeedMod(){return 0.08d;}
	@Override
	protected double getInertialSpeedMod(){return 1.2d;}
	@Override
	protected double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 1.2f;
	}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 0.2f;
	}
	
	@Override
	protected Multimap<Attribute, AttributeModifier> getModifiers()
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 2,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 1, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIER_UUID, "attack_damage", 0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIER_UUID, "movement_speed", 0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));

		return builder.build();
	}
	
}

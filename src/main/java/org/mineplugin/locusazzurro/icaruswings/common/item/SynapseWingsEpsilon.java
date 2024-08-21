package org.mineplugin.locusazzurro.icaruswings.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsExpandable;

public class SynapseWingsEpsilon extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsEpsilon() {
		super(WingsType.SYNAPSE_EPSILON);
	}

	public double getDirectSpeedMod(){return 0.04d;}
	public double getInertialSpeedMod(){return 1.2d;}
	public double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 1.8f;
	}

	@Override
	protected Multimap<Attribute, AttributeModifier> getModifiers() {
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 2,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIER_UUID, "attack_damage", 2.0f, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIER_UUID, "movement_speed", 0.02f, AttributeModifier.Operation.ADDITION));
		return builder.build();
	}

}

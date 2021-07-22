package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class SynapseWingsEpsilon extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsEpsilon() {
		super(WingsType.SYNAPSE_EPSILON);
	}

	@Override
	public float getExpansionFactor() {
		return 1.8f;
	}

	@Override
	protected Multimap<Attribute, AttributeModifier> getModifiers() {
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		return builder.build();
	}
	
}

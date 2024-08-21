package org.mineplugin.locusazzurro.icaruswings.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsExpandable;

public class SynapseWingsZeta extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsZeta() {
		super(WingsType.SYNAPSE_ZETA);
	}

	public double getDirectSpeedMod(){return 0.08d;}
	public double getInertialSpeedMod(){return 1.1d;}
	public double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 2.0f;
	}

	@Override
	protected Multimap<Attribute, AttributeModifier> getModifiers() {
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 8,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 6, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MODIFIER_UUID, "max_health", 10.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(MODIFIER_UUID, "knockback_resistance", 0.6f, AttributeModifier.Operation.ADDITION));
		return builder.build();
	}

}

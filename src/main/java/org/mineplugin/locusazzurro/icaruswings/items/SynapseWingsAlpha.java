package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;

public class SynapseWingsAlpha extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsAlpha() {
		super(WingsType.SYNAPSE_ALPHA);
	}

	protected double getDirectSpeedMod(){return 0.1d;}
	protected double getInertialSpeedMod(){return 1.5d;}
	protected double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 2.0f;
	}
	
	protected Multimap<Attribute, AttributeModifier> getModifiers()
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 5,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 3, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIER_UUID, "attack_damage", 2.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MODIFIER_UUID, "max_health", 10.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(MODIFIER_UUID, "knockback_resistance", 0.2f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIER_UUID, "movement_speed", 0.05f, AttributeModifier.Operation.ADDITION));

		return builder.build();
	}
}

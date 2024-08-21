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

public class SynapseWingsTheta extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsTheta() {
		super(WingsType.SYNAPSE_THETA);
	}

	public double getDirectSpeedMod(){return 0.1d;}
	public double getInertialSpeedMod(){return 1.5d;}
	public double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 2.0f;
	}

	@Override
	protected Multimap<Attribute, AttributeModifier> getModifiers() {
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 6,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 4, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIER_UUID, "attack_damage", 4.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MODIFIER_UUID, "max_health", 16.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(MODIFIER_UUID, "knockback_resistance", 0.4f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIER_UUID, "movement_speed", 0.05f, AttributeModifier.Operation.ADDITION));
		return builder.build();
	}

}

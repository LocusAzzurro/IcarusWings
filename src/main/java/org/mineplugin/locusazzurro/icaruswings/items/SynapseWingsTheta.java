package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.ai.attributes.Attribute;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SynapseWingsTheta extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsTheta() {
		super(WingsType.SYNAPSE_THETA);
	}

	protected double getDirectSpeedMod(){return 0.1d;}
	protected double getInertialSpeedMod(){return 1.5d;}
	protected double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 2.0f;
	}

	@Override
	protected Multimap<net.minecraft.world.entity.ai.attributes.Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> getModifiers() {
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 6,	AttributeModifier.Operation.ADDITION));
	    builder.put(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 4, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new net.minecraft.world.entity.ai.attributes.AttributeModifier(MODIFIER_UUID, "attack_damage", 4.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH, new AttributeModifier(MODIFIER_UUID, "max_health", 16.0f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new net.minecraft.world.entity.ai.attributes.AttributeModifier(MODIFIER_UUID, "knockback_resistance", 0.4f, AttributeModifier.Operation.ADDITION));
	    builder.put(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED, new net.minecraft.world.entity.ai.attributes.AttributeModifier(MODIFIER_UUID, "movement_speed", 0.05f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
		return builder.build();
	}

}

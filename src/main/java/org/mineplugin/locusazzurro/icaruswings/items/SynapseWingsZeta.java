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
public class SynapseWingsZeta extends SynapseWings implements IWingsExpandable{
	
	public SynapseWingsZeta() {
		super(WingsType.SYNAPSE_ZETA);
	}

	protected double getDirectSpeedMod(){return 0.08d;}
	protected double getInertialSpeedMod(){return 1.1d;}
	protected double getTotalSpeedMod(){return 0.5d;}

	@Override
	public float getExpansionFactor() {
		return 2.0f;
	}

	@Override
	protected Multimap<Attribute, AttributeModifier> getModifiers() {
		Builder<net.minecraft.world.entity.ai.attributes.Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 8,	AttributeModifier.Operation.ADDITION));
	    builder.put(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS, new net.minecraft.world.entity.ai.attributes.AttributeModifier(MODIFIER_UUID, "Armor toughness", 6, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
	    builder.put(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH, new net.minecraft.world.entity.ai.attributes.AttributeModifier(MODIFIER_UUID, "max_health", 10.0f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new net.minecraft.world.entity.ai.attributes.AttributeModifier(MODIFIER_UUID, "knockback_resistance", 0.6f, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION));
		return builder.build();
	}

}

package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsTranslucent;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SynapseWingsBeta extends SynapseWings implements IWingsExpandable, IWingsTranslucent{
	
	public SynapseWingsBeta() {
		super(WingsType.SYNAPSE_BETA);
	}

	@Override
	public float getExpansionFactor() {
		return 1.2f;
	}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 0.2f;
	}
	
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		if (player.isFallFlying() && player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemRegistry.nymphWings.get()) {
            Vector3d lookAngle = player.getLookAngle();
            Vector3d flyAngle = player.getDeltaMovement();
            player.setDeltaMovement(flyAngle.add(
            		lookAngle.x * 0.08D + (lookAngle.x * 1.2D - flyAngle.x) * 0.5D,
            		lookAngle.y * 0.05D + (lookAngle.y * 1.0D - flyAngle.y) * 0.5D,
            		lookAngle.z * 0.08D + (lookAngle.z * 1.2D - flyAngle.z) * 0.5D));
        }
	}
	
	protected Multimap<Attribute, AttributeModifier> getModifiers()
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 2,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 1, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIER_UUID, "attack_damage", 1.1f, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    builder.put(Attributes.FOLLOW_RANGE, new AttributeModifier(MODIFIER_UUID, "follow_range", -0.7f, AttributeModifier.Operation.MULTIPLY_BASE));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIER_UUID, "movement_speed", 1.05f, AttributeModifier.Operation.MULTIPLY_TOTAL));

		return builder.build();
	}
	
}

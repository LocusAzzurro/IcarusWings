package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.UUID;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.Utils;
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
import net.minecraft.item.Rarity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SynapseWingsAlpha extends AbstractWings implements IWingsExpandable{
	
	private final UUID MODIFIER_UUID = Utils.ARMOR_MODIFIER_UUID_PER_SLOT[2];
	
	public SynapseWingsAlpha() {
		super(WingsType.SYNAPSE_ALPHA, Rarity.RARE);
	}
	
	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
		return stack.getDamageValue() < stack.getMaxDamage() - 10;
	}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 0.0f;
	}
	
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		if (player.isFallFlying() && player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemRegistry.ikarosWings.get()) {
            Vector3d lookAngle = player.getLookAngle();
            Vector3d flyAngle = player.getDeltaMovement();
            player.setDeltaMovement(flyAngle.add(
            		lookAngle.x * 0.1D + (lookAngle.x * 1.5D - flyAngle.x) * 0.5D,
            		lookAngle.y * 0.1D + (lookAngle.y * 1.5D - flyAngle.y) * 0.5D,
            		lookAngle.z * 0.1D + (lookAngle.z * 1.5D - flyAngle.z) * 0.5D));
        }
	}
	//TODO: buff speed
	/*
	 * lookAngle.x * 0.1D + (lookAngle.x * 1.5D - flyAngle.x) * 0.5D
	 * */
	@Override
	public float getExpansionFactor() {
		return 2.0f;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
		return slot == EquipmentSlotType.CHEST ? this.getModifiers() : super.getDefaultAttributeModifiers(slot);
	}
	
	private Multimap<Attribute, AttributeModifier> getModifiers()
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ARMOR, new AttributeModifier(MODIFIER_UUID, "Armor modifier", 5,	AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(MODIFIER_UUID, "Armor toughness", 3, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIER_UUID, "attack_damage", 2.0f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(MODIFIER_UUID, "knockback_resistance", 0.2f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIER_UUID, "movement_speed", 0.05f, AttributeModifier.Operation.ADDITION));

		return builder.build();
	}
}

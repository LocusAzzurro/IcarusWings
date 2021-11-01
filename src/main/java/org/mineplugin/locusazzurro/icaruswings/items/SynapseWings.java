package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModConfig;
import org.mineplugin.locusazzurro.icaruswings.data.Utils;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Mod.EventBusSubscriber
public abstract class SynapseWings extends AbstractWings{
	
	protected final UUID MODIFIER_UUID = Utils.ARMOR_MODIFIER_UUID_PER_SLOT[2];
	private static final double WINGS_SPEED_MOD_CONFIG = ModConfig.WINGS_SPEED_MOD.get();

	public SynapseWings(WingsType type) {
		super(type, Rarity.RARE);
	}
	
	protected UUID getModifierUUID() {return MODIFIER_UUID;}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 0.0f;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
		return slot == EquipmentSlotType.CHEST ? this.getModifiers() : super.getDefaultAttributeModifiers(slot);
	}
	
	protected abstract Multimap<Attribute, AttributeModifier> getModifiers();

	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
		return stack.getDamageValue() < stack.getMaxDamage() - 10;
	}

	//Default: Direct = 0.1, Inertial = 1.5, Total = 0.5
	protected abstract double getDirectSpeedMod();
	protected abstract double getInertialSpeedMod();
	protected abstract double getTotalSpeedMod();

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		PlayerEntity player = event.player;
		Item item = player.getItemBySlot(EquipmentSlotType.CHEST).getItem();
		if (player.isFallFlying() && item instanceof SynapseWings) {
			Vector3d lookAngle = player.getLookAngle();
			Vector3d flyAngle = player.getDeltaMovement();
			SynapseWings wings = (SynapseWings) item;
			double d = wings.getDirectSpeedMod();
			double i = wings.getInertialSpeedMod();
			double t = wings.getTotalSpeedMod();
			double c = WINGS_SPEED_MOD_CONFIG;
			player.setDeltaMovement(flyAngle.add(
					(lookAngle.x * d + (lookAngle.x * i - flyAngle.x) * t) * c,
					(lookAngle.y * d + (lookAngle.y * i - flyAngle.y) * t) * c,
					(lookAngle.z * d + (lookAngle.z * i - flyAngle.z) * t) * c));
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TranslationTextComponent("item.locusazzurro_icaruswings." + this.type.getName() + ".tooltip")
				.setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
	}
}

package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.data.ModConfig;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public abstract class SynapseWings extends AbstractWings{
	
	protected final UUID MODIFIER_UUID = ModData.ARMOR_MODIFIER_UUID_PER_SLOT[2];

	public SynapseWings(WingsType type) {
		super(type, Rarity.RARE);
	}
	
	protected UUID getModifierUUID() {return MODIFIER_UUID;}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 1.0f;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.MENDING && !this.acceptsMending() ? false : super.canApplyAtEnchantingTable(stack, enchantment);
	}

	public boolean acceptsMending(){
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.CHEST ? this.getModifiers() : super.getDefaultAttributeModifiers(slot);
	}
	
	protected abstract Multimap<Attribute, AttributeModifier> getModifiers();

	@Override
	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return stack.getDamageValue() < stack.getMaxDamage() - 10;
	}

	//Default: Direct = 0.1, Inertial = 1.5, Total = 0.5
	protected abstract double getDirectSpeedMod();
	protected abstract double getInertialSpeedMod();
	protected abstract double getTotalSpeedMod();

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
		if (player.isFallFlying() && item instanceof SynapseWings wings) {
			Vec3 lookAngle = player.getLookAngle();
			Vec3 flyAngle = player.getDeltaMovement();
			double d = wings.getDirectSpeedMod();
			double i = wings.getInertialSpeedMod();
			double t = wings.getTotalSpeedMod();
			double c = ModConfig.WINGS_SPEED_MOD.get();
			player.setDeltaMovement(flyAngle.add(
					(lookAngle.x * d + (lookAngle.x * i - flyAngle.x) * t) * c,
					(lookAngle.y * d + (lookAngle.y * i - flyAngle.y) * t) * c,
					(lookAngle.z * d + (lookAngle.z * i - flyAngle.z) * t) * c));
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.translatable("item.locusazzurro_icaruswings." + this.type.getName() + ".tooltip")
				.setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
	}
}

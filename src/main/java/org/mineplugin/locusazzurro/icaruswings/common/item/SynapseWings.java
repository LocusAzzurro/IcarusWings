package org.mineplugin.locusazzurro.icaruswings.common.item;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;

import java.util.List;

public abstract class SynapseWings extends AbstractWings{

	public SynapseWings(WingsType type) {
		super(type, Rarity.RARE);
	}

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
	public abstract double getDirectSpeedMod();
	public abstract double getInertialSpeedMod();
	public abstract double getTotalSpeedMod();

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		tooltipComponents.add(Component.translatable("item.locusazzurro_icaruswings." + this.type.getName() + ".tooltip")
				.setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
	}
}

package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.common.data.SynapseWingsSpeedData;
import org.mineplugin.locusazzurro.icaruswings.common.data.WingsType;

import java.util.List;
import java.util.function.UnaryOperator;

public class SynapseWings extends AbstractWings implements IWingsExpandable {

	private static final double DEFAULT_EXPANSION_FACTOR = 1.0d;

	private final SynapseWingsSpeedData speed;
	private final double expansionFactor;
	private final UnaryOperator<ItemAttributeModifiers> attributeModifiers;
	private final WingsType type;

	public SynapseWings(WingsType type) {
		this(type, SynapseWingsSpeedData.DEFAULT, DEFAULT_EXPANSION_FACTOR, UnaryOperator.identity());
	}

	public SynapseWings(WingsType type, SynapseWingsSpeedData speed, double expansionFactor, UnaryOperator<ItemAttributeModifiers> attributes){
		super(type, Rarity.RARE);
		this.speed = speed;
		this.expansionFactor = expansionFactor;
		this.attributeModifiers = attributes;
		this.type = type;
	}


	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 1.0f;
	}

	@Override
	@SuppressWarnings("deprecation")
	public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
		return this.attributeModifiers.apply(super.getDefaultAttributeModifiers());
	}

	@Override
	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return stack.getDamageValue() < stack.getMaxDamage() - 10;
	}

	public double getDirectSpeedMod() {return speed.directSpeed();}
	public double getInertialSpeedMod() {return speed.inertialSpeed();}
	public double getTotalSpeedMod() {return speed.totalSpeed();}
	@Override public double getExpansionFactor() {return expansionFactor;}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		tooltipComponents.add(Component.translatable("item.locusazzurro_icaruswings." + this.type.getName() + ".tooltip")
				.setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
	}
}

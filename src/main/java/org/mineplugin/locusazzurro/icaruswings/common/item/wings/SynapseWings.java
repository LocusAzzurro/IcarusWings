package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.phys.Vec3;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.common.data.IcarusWingsConfig;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class SynapseWings extends AbstractWings implements IWingsExpandable {

	private static final double DEFAULT_EXPANSION_FACTOR = 1.0d;

	private final SynapseWingsSpeedData speed;
	private final double expansionFactor;
	private final UnaryOperator<ItemAttributeModifiers> attributeModifiers;
	private final WingsTypes type;
	
	public SynapseWings(WingsTypes type) {
		this(type, SynapseWingsSpeedData.DEFAULT, DEFAULT_EXPANSION_FACTOR, UnaryOperator.identity());
	}

	public SynapseWings(WingsTypes type, Item.Properties properties) {
		this(type, SynapseWingsSpeedData.DEFAULT, DEFAULT_EXPANSION_FACTOR, UnaryOperator.identity(), properties);
	}

	public SynapseWings(WingsTypes type, SynapseWingsSpeedData speed, double expansionFactor, UnaryOperator<ItemAttributeModifiers> attributes){
		this(type, speed, expansionFactor, attributes, new Item.Properties());
	}

	public SynapseWings(WingsTypes type, SynapseWingsSpeedData speed, double expansionFactor, UnaryOperator<ItemAttributeModifiers> attributes, Item.Properties properties){
		super(type, Rarity.RARE, properties);
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
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return this.attributeModifiers.apply(super.getDefaultAttributeModifiers(stack));
    }

	@Override
	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return stack.getDamageValue() < stack.getMaxDamage() - 10;
	}

	@Override
	public void elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
		if (!(entity instanceof Player player)) {
			return;
		}
		Vec3 lookAngle = player.getLookAngle();
		Vec3 flyAngle = player.getDeltaMovement();
		double direct = getDirectSpeedMod();
		double inertial = getInertialSpeedMod();
		double total = getTotalSpeedMod();
		double config = IcarusWingsConfig.WINGS_SPEED_MOD.get();
		player.setDeltaMovement(
				flyAngle.add(
						(lookAngle.x * direct + (lookAngle.x * inertial - flyAngle.x) * total) * config,
						(lookAngle.y * direct + (lookAngle.y * inertial - flyAngle.y) * total) * config,
						(lookAngle.z * direct + (lookAngle.z * inertial - flyAngle.z) * total) * config
				)
		);
	}

	public double getDirectSpeedMod() {return speed.directSpeed();}
	public double getInertialSpeedMod() {return speed.inertialSpeed();}
	public double getTotalSpeedMod() {return speed.totalSpeed();}
	@Override public double getExpansionFactor() {return expansionFactor;}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
		tooltipComponents.accept(Component.translatable("item.locusazzurro_icaruswings." + this.type.getName() + ".tooltip")
				.setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
	}
}

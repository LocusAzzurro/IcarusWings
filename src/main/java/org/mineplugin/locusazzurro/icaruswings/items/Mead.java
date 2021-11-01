package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Mead extends Item {
	
	private Infusion infusionType = null;
	
	public Mead() {
		super(new Properties().tab(ModGroup.itemGroup).food(food).stacksTo(4));
	}
	
	public Mead(Infusion type) {
		this();
		this.infusionType = type;
	}

	private static final Food food = (new Food.Builder())
			.saturationMod(1)
			.nutrition(5)
			.alwaysEat()
			.build();
	
	public ItemStack finishUsingItem(ItemStack stackIn, World worldIn, LivingEntity entityIn) {
		Infusion type =((Mead)stackIn.getItem()).infusionType;
		super.finishUsingItem(stackIn, worldIn, entityIn);
		if (entityIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stackIn);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!worldIn.isClientSide) {
			entityIn.removeEffect(Effects.POISON);
			entityIn.removeEffect(Effects.WITHER);
			entityIn.removeEffect(Effects.WEAKNESS);
			entityIn.addEffect(new EffectInstance(Effects.CONFUSION, 400, 0));
			entityIn.addEffect(new EffectInstance(Effects.REGENERATION, 20, 0));
			List<EffectInstance> extraEffect = new ArrayList<>();
			if(type != null) {
				switch(type) {
				case ZEPHIR: 
					extraEffect.add(new EffectInstance(Effects.MOVEMENT_SPEED, 1200, 2)); 
					extraEffect.add(new EffectInstance(Effects.REGENERATION, 200, 0));
					break;
				case NETHER:
					extraEffect.add(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0));
					extraEffect.add(new EffectInstance(Effects.DAMAGE_BOOST, 1200, 0));
					break;
				case VOID:
					extraEffect.add(new EffectInstance(Effects.SLOW_FALLING, 1200, 1));
					extraEffect.add(new EffectInstance(Effects.INVISIBILITY, 1200, 0));
					break;
				case GOLDEN_APPLE:
					extraEffect.add(new EffectInstance(Effects.DAMAGE_RESISTANCE, 1200, 1));
					extraEffect.add(new EffectInstance(Effects.HEALTH_BOOST, 1200, 1));
					break;
				case HERBS:
					extraEffect.add(new EffectInstance(Effects.REGENERATION, 200, 0));
					extraEffect.add(new EffectInstance(Effects.LUCK, 1200, 0));
					break;
				default:
					break;
				}
				
				extraEffect.forEach(e -> entityIn.addEffect(e));

			}

		}

		if (stackIn.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (entityIn instanceof PlayerEntity && !((PlayerEntity) entityIn).abilities.instabuild) {
				ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
				PlayerEntity playerentity = (PlayerEntity) entityIn;
				if (!playerentity.inventory.add(itemstack)) {
					playerentity.drop(itemstack, false);
				}
			}

			return stackIn;
		}
	}

	public int getUseDuration(ItemStack p_77626_1_) {
		return 60;
	}

	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.DRINK;
	}

	public SoundEvent getDrinkingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	public SoundEvent getEatingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return DrinkHelper.useDrink(worldIn, playerIn, handIn);
	}
	
	@Nullable
	public Infusion getInfusionType() {
		return this.infusionType;
	}
	
	public enum Infusion{
		ZEPHIR,NETHER,VOID,GOLDEN_APPLE,HERBS
	}
}

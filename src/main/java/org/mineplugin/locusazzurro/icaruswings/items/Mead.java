package org.mineplugin.locusazzurro.icaruswings.items;

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

	public Mead() {
		super(new Properties().tab(ModGroup.itemGroup).food(food).stacksTo(4));
	}

	private static final Food food = (new Food.Builder())
			.saturationMod(1)
			.nutrition(5)
			.alwaysEat()
			.build();
	
	//TODO: add elixir made from mead
	
	public ItemStack finishUsingItem(ItemStack stackIn, World worldIn, LivingEntity entityIn) {
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
			entityIn.addEffect(new EffectInstance(Effects.CONFUSION, 100, 0));
			entityIn.addEffect(new EffectInstance(Effects.REGENERATION, 20, 0));
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
}

package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Mead extends Item {
	
	private Infusion infusionType = null;
	
	public Mead() {
		super(new Properties().tab(ModGroup.itemGroup).food(food).stacksTo(4));
	}
	
	public Mead(Infusion type) {
		this();
		this.infusionType = type;
	}

	private static final FoodProperties food = (new FoodProperties.Builder())
			.saturationMod(1)
			.nutrition(5)
			.alwaysEat()
			.build();
	
	@Override
	public ItemStack finishUsingItem(ItemStack stackIn, Level worldIn, LivingEntity entityIn) {
		Infusion type =((Mead)stackIn.getItem()).infusionType;
		super.finishUsingItem(stackIn, worldIn, entityIn);
		if (entityIn instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) entityIn;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stackIn);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!worldIn.isClientSide) {
			entityIn.removeEffect(MobEffects.POISON);
			entityIn.removeEffect(MobEffects.WITHER);
			entityIn.removeEffect(MobEffects.WEAKNESS);
			entityIn.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0));
			entityIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0));
			List<MobEffectInstance> extraEffect = new ArrayList<>();
			if(type != null) {
				switch(type) {
				case ZEPHIR: 
					extraEffect.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2));
					extraEffect.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
					break;
				case NETHER:
					extraEffect.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0));
					extraEffect.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));
					break;
				case VOID:
					extraEffect.add(new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 1));
					extraEffect.add(new MobEffectInstance(MobEffects.INVISIBILITY, 1200, 0));
					break;
				case GOLDEN_APPLE:
					extraEffect.add(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1));
					extraEffect.add(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1));
					break;
				case HERBS:
					extraEffect.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
					extraEffect.add(new MobEffectInstance(MobEffects.LUCK, 1200, 0));
					break;
				default:
					break;
				}
				
				extraEffect.forEach(entityIn::addEffect);

			}

		}

		if (stackIn.isEmpty()) {
			return new ItemStack(ItemRegistry.glassJar.get());
		} else {
			if (entityIn instanceof Player && !((Player) entityIn).getAbilities().instabuild) {
				ItemStack itemstack = new ItemStack(ItemRegistry.glassJar.get());
				Player playerentity = (Player) entityIn;
				ItemHandlerHelper.giveItemToPlayer(playerentity, itemstack);
				//if (!playerentity.inventory.add(itemstack)) {playerentity.drop(itemstack, false);}
			}

			return stackIn;
		}
	}

	@Override
	public int getUseDuration(ItemStack p_77626_1_) {
		return 60;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_77661_1_) {
		return UseAnim.DRINK;
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemUtils.startUsingInstantly(worldIn, playerIn, handIn);
		return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
	}
	
	@Nullable
	public Infusion getInfusionType() {
		return this.infusionType;
	}
	
	public enum Infusion{
		ZEPHIR,NETHER,VOID,GOLDEN_APPLE,HERBS
	}
}

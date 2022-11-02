package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.*;

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
                switch (type) {
                    case ZEPHIR -> {
                        extraEffect.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2));
                        extraEffect.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                    }
                    case NETHER -> {
                        extraEffect.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0));
                        extraEffect.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));
                    }
                    case VOID -> {
                        extraEffect.add(new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 1));
                        extraEffect.add(new MobEffectInstance(MobEffects.INVISIBILITY, 1200, 0));
                    }
                    case GOLDEN_APPLE, GOLDEN_APPLE_GROWTH -> {
                        extraEffect.add(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1));
                        extraEffect.add(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1));
                    }
					case HERBS -> {
                        extraEffect.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                        extraEffect.add(new MobEffectInstance(MobEffects.LUCK, 1200, 0));
						extraEffect.add(new MobEffectInstance(EffectRegistry.poisonImmunity.get(), 1200, 0));
                    }
                    default -> {
                    }
                }
				
				extraEffect.forEach(entityIn::addEffect);

			}

		}

		if (stackIn.isEmpty()) {
			return new ItemStack(ItemRegistry.glassJar.get());
		} else {
			if (entityIn instanceof Player player && !((Player) entityIn).getAbilities().instabuild) {
				ItemStack itemstack = new ItemStack(ItemRegistry.glassJar.get());
                ItemHandlerHelper.giveItemToPlayer(player, itemstack);
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

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState block = level.getBlockState(pos);

		if (block.is(Blocks.DIRT) || block.is(Blocks.GRASS_BLOCK)){
			ItemStack item = context.getItemInHand();
			Player player = context.getPlayer();
			if (player != null && item.getItem() instanceof Mead mead && mead.infusionType == Infusion.GOLDEN_APPLE_GROWTH) {

				boolean clientSide = level.isClientSide();

				if (!clientSide){
					BlockPos.withinManhattan(pos,1,1,1).forEach(p -> {
						BlockState block1 = level.getBlockState(p);
						BlockState blockUp = level.getBlockState(p.above());
						if ((block1.is(Blocks.DIRT) || block1.is(Blocks.GRASS_BLOCK)) && blockUp.isAir() && level.random.nextFloat() < 0.4) {
							level.setBlock(p.above(), BlockRegistry.elysianGrass.get().defaultBlockState(), 3);
						}
						if (block1.is(Blocks.DIRT)) {
							level.setBlock(p, BlockRegistry.elysianSoil.get().defaultBlockState(), 3);
						}
						if (block1.is(Blocks.GRASS_BLOCK)) {
							level.setBlock(p, BlockRegistry.elysianGrassBlock.get().defaultBlockState(), 3);
						}
					});

					AABB aabb = new AABB(pos).inflate(1);
					List<Sheep> sheep = level.getEntitiesOfClass(Sheep.class, aabb);
					if (!sheep.isEmpty()){
						Sheep sheep1 = sheep.get(level.random.nextInt(sheep.size()));
						sheep1.convertTo(EntityTypeRegistry.goldenRamEntity.get(), true);
					}

					if (!player.getAbilities().instabuild) {
						item.shrink(1);
						ItemStack itemstack = new ItemStack(ItemRegistry.glassJar.get());
						ItemHandlerHelper.giveItemToPlayer(player, itemstack);
					}
				}
				else {
					var rnd = level.getRandom();
					for (int i = 0; i < 10; i++) {
						level.addParticle(ParticleRegistry.goldenSparkle.get(),
								pos.getX() + 0.5 + (rnd.nextFloat() - 0.5),
								pos.getY() + 1.5 + (rnd.nextFloat() - 0.5) / 3,
								pos.getZ() + 0.5 + (rnd.nextFloat() - 0.5),
								(rnd.nextFloat() - 0.5) / 4,
								(rnd.nextFloat() - 0.4) / 4,
								(rnd.nextFloat() - 0.5) / 4);
					}
				}
				return InteractionResult.sidedSuccess(clientSide);
			}
		}
		return InteractionResult.PASS;
	}

	@Nullable
	public Infusion getInfusionType() {
		return this.infusionType;
	}
	
	public enum Infusion implements StringRepresentable {
		NONE("none"),ZEPHIR("zephir"),NETHER("nether"),VOID("void"),
		GOLDEN_APPLE("golden_apple"),GOLDEN_APPLE_GROWTH("golden_apple_growth"),HERBS("herbs");

		private String name;

		Infusion(String name) {this.name = name;}
		@Override
		public String getSerializedName() {
			return name;
		}
	}
}

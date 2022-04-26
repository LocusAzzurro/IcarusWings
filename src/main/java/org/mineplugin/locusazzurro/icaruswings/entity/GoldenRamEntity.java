package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.network.NetworkHooks;
import org.mineplugin.locusazzurro.icaruswings.entity.ai.EatGoldenGrassGoal;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GoldenRamEntity extends Animal implements IForgeShearable {

	private static final EntityDataAccessor<Boolean> IS_SHEARED = SynchedEntityData.defineId(GoldenRamEntity.class, EntityDataSerializers.BOOLEAN);
	private EatGoldenGrassGoal eatBlockGoal;
	private int eatAnimationTick;

	public GoldenRamEntity(EntityType<GoldenRamEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_SHEARED, false);
	}
  
	@Override
	@ParametersAreNonnullByDefault
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setSheared(compound.getBoolean("Sheared"));
    }
	
	@Override
	@ParametersAreNonnullByDefault
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sheared", this.isSheared());
    }
	
	@Override
	public Packet<?> getAddEntityPacket() {
	  return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static AttributeSupplier.Builder setCustomAttributes(){
		return LivingEntity.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 18.0d)
				.add(Attributes.MOVEMENT_SPEED, 0.27d)
				.add(Attributes.FOLLOW_RANGE, 32d);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.eatBlockGoal = new EatGoldenGrassGoal(this);
		this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1, 10));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false));
		this.goalSelector.addGoal(3, this.eatBlockGoal);
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}
	
	@Override
	public void tick() {
		super.tick();
		if (level.isClientSide() && (this.tickCount % 4 == 0)){
			Random rnd = level.getRandom();
			level.addParticle(ParticleRegistry.goldenSparkle.get(),
					this.getX(), this.getY()+0.8, this.getZ(),
					(rnd.nextFloat() - 0.5) / 4 ,
					(rnd.nextFloat() - 0.4) / 4 ,
					(rnd.nextFloat() - 0.5) / 4 );
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {return SoundRegistry.goldenRamAmbient.get();}
	
	@Override
	@ParametersAreNonnullByDefault
	protected SoundEvent getHurtSound(DamageSource ds) {return SoundRegistry.goldenRamHurt.get();}
	
	@Override
	protected SoundEvent getDeathSound() {return SoundRegistry.goldenRamDeath.get();}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundRegistry.goldenRamStep.get(), 0.15F, 1.0F);
	}

	public void setSheared(boolean isSheared) {
		this.entityData.set(IS_SHEARED, isSheared);
	}

	public boolean isSheared() {
		return this.entityData.get(IS_SHEARED);
	}

	@Override
	public void ate() {
		this.setSheared(false);
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, Level world, BlockPos pos) {
		return this.isAlive() && !this.isSheared();
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundRegistry.goldenRamShear.get(), player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			List<ItemStack> items = new ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(ItemRegistry.goldenFleece.get()));
			}
			return items;
		}
		return Collections.emptyList();
	}

	@Override
	protected void customServerAiStep() {
		this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
		super.customServerAiStep();
	}

	@Override
	public void aiStep() {
		if (this.level.isClientSide) {
			this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
		}
		super.aiStep();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 10) {
			this.eatAnimationTick = 40;
		} else {
			super.handleEntityEvent(p_70103_1_);
		}

	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadEatPositionScale(float z) {
		if (this.eatAnimationTick <= 0) {
			return 0.0F;
		} else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
			return 1.0F;
		} else {
			return this.eatAnimationTick < 4 ? ((float)this.eatAnimationTick - z) / 4.0F : -((float)(this.eatAnimationTick - 40) - z) / 4.0F;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadEatAngleScale(float z) {
		if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
			float f = ((float)(this.eatAnimationTick - 4) - z) / 32.0F;
			return ((float)Math.PI / 5F) + 0.21991149F * Mth.sin(f * 28.7F);
		} else {
			return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.getXRot() * ((float)Math.PI / 180F);
		}
	}
	


}

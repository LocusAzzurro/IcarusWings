package org.mineplugin.locusazzurro.icaruswings.entity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mineplugin.locusazzurro.icaruswings.entity.ai.EatGoldenGrassGoal;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.Collections;
import java.util.List;

public class GoldenRamEntity extends AnimalEntity implements IForgeShearable {

	private static final DataParameter<Boolean> IS_SHEARED = EntityDataManager.defineId(GoldenRamEntity.class, DataSerializers.BOOLEAN);
	private EatGoldenGrassGoal eatBlockGoal;
	private int eatAnimationTick;

	public GoldenRamEntity(EntityType<GoldenRamEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_SHEARED, false);
	}
  
	@Override
	@ParametersAreNonnullByDefault
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setSheared(compound.getBoolean("Sheared"));
    }
	
	@Override
	@ParametersAreNonnullByDefault
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sheared", this.isSheared());
    }
	
	@Override
	public IPacket<?> getAddEntityPacket() {
	  return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
		return LivingEntity.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 18.0d)
				.add(Attributes.MOVEMENT_SPEED, 0.27d)
				.add(Attributes.FOLLOW_RANGE, 32d);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.eatBlockGoal = new EatGoldenGrassGoal(this);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false)); //todo special item
		this.goalSelector.addGoal(3, this.eatBlockGoal);
		this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	}
	
	@Override
	public void tick() {
		super.tick();
		if (level.isClientSide() && ((this.tickCount >> 1 & 1) != 0)){
			level.addParticle(ParticleRegistry.goldenSparkle.get(), this.getX(), this.getY(), this.getZ(), 0,0,0);
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

	@Nullable
	@Override
	@ParametersAreNonnullByDefault
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageableEntity) {
		return null;
	}

	public void setSheared(boolean isSheared) {
		this.entityData.set(IS_SHEARED, isSheared);
	}

	public boolean isSheared() {
		return this.entityData.get(IS_SHEARED);
	}

	public void ate() {
		this.setSheared(false);
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, World world, BlockPos pos) {
		return this.isAlive() && !this.isSheared();
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundRegistry.goldenRamShear.get(), player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
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
			return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
		} else {
			return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.xRot * ((float)Math.PI / 180F);
		}
	}
	


}

package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class GoldenRamEntity extends AnimalEntity implements IForgeShearable {

	private static final DataParameter<Boolean> IS_SHEARED = EntityDataManager.defineId(GoldenRamEntity.class, DataSerializers.BOOLEAN);
	
	public GoldenRamEntity(EntityType<GoldenRamEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_SHEARED, false);
	}
  
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setSheared(compound.getBoolean("Sheared"));
    }
	
	@Override
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
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.0d));
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	protected SoundEvent getAmbientSound() {return SoundEvents.SHEEP_AMBIENT;}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource ds) {return SoundEvents.SHEEP_HURT;}
	
	@Override
	protected SoundEvent getDeathSound() {return SoundEvents.SHEEP_DEATH;}
	
	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
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
		world.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(Items.GOLDEN_APPLE));
			}
			return items;
		}
		return Collections.emptyList();
	}
	


}

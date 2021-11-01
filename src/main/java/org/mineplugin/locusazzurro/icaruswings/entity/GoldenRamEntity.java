package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoldenRamEntity extends AnimalEntity{
	
	public GoldenRamEntity(EntityType<GoldenRamEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	private Logger logger = LogManager.getLogger();
	private static final DataParameter<Integer> TEST_COUNT = EntityDataManager.defineId(GoldenRamEntity.class, DataSerializers.INT);
	
	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TEST_COUNT, 0);
	}
  
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
        this.entityData.set(TEST_COUNT, compound.getInt("count"));
    }
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
        compound.putInt("count", this.entityData.get(TEST_COUNT));
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
		if(level.isClientSide) {
			logger.info(this.entityData.get(TEST_COUNT));
		}
		if(!level.isClientSide) {
			logger.info(this.entityData.get(TEST_COUNT));
			this.entityData.set(TEST_COUNT, this.entityData.get(TEST_COUNT)+ 1);
		}
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
	


}

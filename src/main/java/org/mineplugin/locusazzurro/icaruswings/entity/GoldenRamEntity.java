package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Multimap;

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
	
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        p_213386_4_ = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);

        getAttribute(Attributes.MAX_HEALTH).setBaseValue(20f);
        setHealth(20f);
        return p_213386_4_;
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
	
	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}
	


}

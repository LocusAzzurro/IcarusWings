package org.mineplugin.locusazzurro.icaruswings.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class GoldenRamEntity extends AnimalEntity{
	
	public GoldenRamEntity(EntityType<GoldenRamEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	private static final DataParameter<Integer> TEST_COUNT = EntityDataManager.defineId(GoldenRamEntity.class, DataSerializers.INT);
	
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}
	
	@Override
	public IPacket<?> getAddEntityPacket() {
	  return NetworkHooks.getEntitySpawningPacket(this);
	}
}

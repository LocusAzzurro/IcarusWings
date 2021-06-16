package org.mineplugin.locusazzurro.icaruswings.blocks;

import org.mineplugin.locusazzurro.icaruswings.data.TileEntityTypeRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MeadPotTileEntity extends TileEntity implements ITickableTileEntity{
	
	private static final int FERMENTATION_TIME = 10 * 20;
	private int fermentationProgress = 0;
	private boolean isFermenting = false;
	private boolean isComplete = false;
	
	public MeadPotTileEntity() {
		super(TileEntityTypeRegistry.meadPotTileEntity.get());
	}
	
	public void startFermeting() {
		this.fermentationProgress = 0;
		this.isFermenting = true;
		this.isComplete = false;
		this.setParentBlockState(MeadPot.MeadPotState.FERMENTING);
		//System.out.println("startFem called");
	}
	
	public void setComplete() {
		this.fermentationProgress = 101;
		this.isFermenting = false;
		this.isComplete = true;
		this.setParentBlockState(MeadPot.MeadPotState.COMPLETE);
		//System.out.println("setComplete called");
	}
	
	public void setEmpty() {
		this.fermentationProgress = 0;
		this.isFermenting = false;
		this.isComplete = false;
		this.setParentBlockState(MeadPot.MeadPotState.EMPTY);
	}
	
	public void setParentBlockState(MeadPot.MeadPotState state) {
		this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(MeadPot.STATE, state), 3);
		this.setChanged();
	}
	
	public boolean isFermenting() {
		return this.isFermenting;
	}
	
	public boolean isComplete() {
		return this.isComplete;
	}
	
	@Override
	public void tick() {
		World world = this.level;
		boolean needUpdate = false;
		if (world != null && !world.isClientSide) {
			if (this.isFermenting && (fermentationProgress < FERMENTATION_TIME)) {
				fermentationProgress ++;
				//System.out.println("TE Tick #" + fermentationProgress);
				needUpdate = true;
			}
			if (this.isFermenting && (fermentationProgress == FERMENTATION_TIME)) {
				
				this.setComplete();
				//System.out.println("Complete - TE Tick #" + fermentationProgress);
				needUpdate = true;
			}
			if (needUpdate) this.setChanged();
		}
	}
	
	 @Override
	 public void load(BlockState state, CompoundNBT nbt) {
		 this.fermentationProgress = nbt.getInt("fermentation_progress");
		 this.isFermenting = nbt.getBoolean("fermeting");
		 this.isComplete = nbt.getBoolean("complete");
	     super.load(state, nbt);
	 }
	 
	 @Override
	 public CompoundNBT save(CompoundNBT compound) {
		 compound.putInt("fermentation_progress", this.fermentationProgress);
		 compound.putBoolean("fermeting", this.isFermenting);
		 compound.putBoolean("complete", this.isComplete);
	     return super.save(compound);
	 }
}

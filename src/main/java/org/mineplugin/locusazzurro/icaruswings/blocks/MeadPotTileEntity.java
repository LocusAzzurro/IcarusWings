package org.mineplugin.locusazzurro.icaruswings.blocks;

import org.mineplugin.locusazzurro.icaruswings.data.TileEntityTypeRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.LightType;
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
	}
	
	public void setComplete() {
		this.fermentationProgress = FERMENTATION_TIME;
		this.isFermenting = false;
		this.isComplete = true;
		this.setParentBlockState(MeadPot.MeadPotState.COMPLETE);
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
	
	public int getFermentationProgress() {
		return this.fermentationProgress;
	}
	
	public static int getFermentationTime() {
		return FERMENTATION_TIME;
	}
	
	//TODO: increment fermentation only when exposed to sky and has air above
	@Override
	public void tick() {
		World world = this.level;
		boolean needUpdate = false;
		if (world != null && !world.isClientSide) {
			int skyLight = this.level.getBrightness(LightType.SKY, this.getBlockPos().above());
			if (this.isFermenting && (fermentationProgress < FERMENTATION_TIME && skyLight > 13)) {
				fermentationProgress ++;
				needUpdate = true;
			}
			if (this.isFermenting && (fermentationProgress == FERMENTATION_TIME)) {
				
				this.setComplete();
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

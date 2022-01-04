package org.mineplugin.locusazzurro.icaruswings.blocks;

import org.mineplugin.locusazzurro.icaruswings.registry.TileEntityTypeRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class MeadPotTileEntity extends TileEntity implements ITickableTileEntity{
	
	private static final int FERMENTATION_TIME = 3600 * 20; //1h / 3 in-game days
	private int fermentationProgress = 0;
	private boolean isFermenting = false;
	private boolean isComplete = false;
	
	public MeadPotTileEntity() {
		super(TileEntityTypeRegistry.meadPotTileEntity.get());
	}
	
	public void startFermenting() {
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
	
	@Override
	public void tick() {
		World world = this.level;
		boolean needUpdate = false;
		if (world != null && !world.isClientSide) {
			int skyLight = this.level.getBrightness(LightType.SKY, this.getBlockPos().above());
			if (this.isFermenting && (fermentationProgress < FERMENTATION_TIME) && skyLight > 13) {
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
		 this.fermentationProgress = nbt.getInt("FermentationProgress");
		 this.isFermenting = nbt.getBoolean("Fermenting");
		 this.isComplete = nbt.getBoolean("Complete");
	     super.load(state, nbt);
	 }
	 
	 @Override
	 public CompoundNBT save(CompoundNBT compound) {
		 compound.putInt("FermentationProgress", this.fermentationProgress);
		 compound.putBoolean("Fermenting", this.isFermenting);
		 compound.putBoolean("Complete", this.isComplete);
	     return super.save(compound);
	 }
}

package org.mineplugin.locusazzurro.icaruswings.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineplugin.locusazzurro.icaruswings.blocks.MeadPot;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockEntityTypeRegistry;

public class MeadPotBlockEntity extends BlockEntity implements ITickableBlockEntity<MeadPotBlockEntity> {
	
	private static final int FERMENTATION_TIME = 3600 * 20; //1h / 3 in-game days
	private int fermentationProgress = 0;
	private boolean isFermenting = false;
	private boolean isComplete = false;
	
	public MeadPotBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityTypeRegistry.MEAD_POT_BLOCK_ENTITY.get(), pos, state);
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
	public MeadPotBlockEntity getBlockEntity() {
		return this;
	}

	@Override
	public void tick() {
		Level world = this.level;
		boolean needUpdate = false;
		if (world != null && !world.isClientSide) {
			int skyLight = this.level.getBrightness(LightLayer.SKY, this.getBlockPos().above());
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
	public void load(CompoundTag nbt) {
		super.load(nbt);
		 this.fermentationProgress = nbt.getInt("FermentationProgress");
		 this.isFermenting = nbt.getBoolean("Fermenting");
		 this.isComplete = nbt.getBoolean("Complete");
	     super.load(nbt);
	 }

	@Override
	protected void saveAdditional(CompoundTag compound) {
		 compound.putInt("FermentationProgress", this.fermentationProgress);
		 compound.putBoolean("Fermenting", this.isFermenting);
		 compound.putBoolean("Complete", this.isComplete);
	     super.saveAdditional(compound);
	 }
}

package org.mineplugin.locusazzurro.icaruswings.blocks;

import org.mineplugin.locusazzurro.icaruswings.registry.TileEntityTypeRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AmphoraTileEntity extends LockableLootTileEntity{
	
	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);
	public AmphoraTileEntity() {
		super(TileEntityTypeRegistry.amphoraTileEntity.get());
	}

	@Override
	public int getContainerSize() {
		return 45;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> listIn) {
		this.items = listIn;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.amphora");
	}

	@Override
	protected Container createMenu(int a, PlayerInventory invIn) {
		return new ChestContainer(ContainerType.GENERIC_9x5, a, invIn, this, 5);
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		if (!this.trySaveLootTable(nbt)) {
			ItemStackHelper.saveAllItems(nbt, this.items);
		}

		return nbt;
	}
	
	@Override
	public void load(BlockState stateIn, CompoundNBT nbt) {
		super.load(stateIn, nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}

	}
	
}

package org.mineplugin.locusazzurro.icaruswings.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineplugin.locusazzurro.icaruswings.registry.TileEntityTypeRegistry;

public class AmphoraTileEntity extends RandomizableContainerBlockEntity {
	
	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);


	public AmphoraTileEntity(BlockPos a, BlockState b) {
		super(TileEntityTypeRegistry.amphoraTileEntity.get(), a, b);
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
	protected Component getDefaultName() {
		return Component.translatable("container.amphora");
	}

	@Override
	protected AbstractContainerMenu createMenu(int a, Inventory invIn) {
		return new ChestMenu(MenuType.GENERIC_9x5, a, invIn, this, 5);
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		if (!this.trySaveLootTable(nbt)) {
			ContainerHelper.saveAllItems(nbt, this.items);
		}
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(nbt)) {
			ContainerHelper.loadAllItems(nbt, this.items);
		}
	}
}

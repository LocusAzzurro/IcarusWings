package org.mineplugin.locusazzurro.icaruswings.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineplugin.locusazzurro.icaruswings.registry.TileEntityTypeRegistry;

public class AmphoraTileEntity extends ChestBlockEntity {
	
	private NonNullList<net.minecraft.world.item.ItemStack> items = NonNullList.withSize(45, net.minecraft.world.item.ItemStack.EMPTY);
	public AmphoraTileEntity(BlockPos a, BlockState b) {
		super(TileEntityTypeRegistry.amphoraTileEntity.get(), a, b);
	}

	@Override
	protected void signalOpenCount(Level p_155865_, BlockPos p_155866_, BlockState p_155867_, int p_155868_, int p_155869_) {
		super.signalOpenCount(p_155865_, p_155866_, p_155867_, p_155868_, p_155869_);
		if (p_155868_ != p_155869_) {
			Block block = p_155867_.getBlock();
			p_155865_.updateNeighborsAt(p_155866_, block);
			p_155865_.updateNeighborsAt(p_155866_.below(), block);
		}

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
	protected void setItems(NonNullList<net.minecraft.world.item.ItemStack> listIn) {
		this.items = listIn;
	}

	@Override
	protected TextComponent getDefaultName() {
		return new TextComponent(new TranslatableComponent("container.amphora").getString());
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
		this.items = NonNullList.withSize(this.getContainerSize(), net.minecraft.world.item.ItemStack.EMPTY);
		if (!this.tryLoadLootTable(nbt)) {
			ContainerHelper.loadAllItems(nbt, this.items);
		}
	}
}

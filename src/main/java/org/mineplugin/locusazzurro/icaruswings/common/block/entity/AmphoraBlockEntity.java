package org.mineplugin.locusazzurro.icaruswings.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockEntityTypeRegistry;

public class AmphoraBlockEntity extends RandomizableContainerBlockEntity {
	
	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);


	public AmphoraBlockEntity(BlockPos a, BlockState b) {
		super(BlockEntityTypeRegistry.AMPHORA_BLOCK_ENTITY.get(), a, b);
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
	protected void saveAdditional(ValueOutput tag) {
		super.saveAdditional(tag);
		if (!this.trySaveLootTable(tag)) {
			ContainerHelper.saveAllItems(tag, this.items);
		}
	}

	@Override
	protected void loadAdditional(ValueInput tag) {
		super.loadAdditional(tag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(tag)) {
			ContainerHelper.loadAllItems(tag, this.items);
		}
	}

}

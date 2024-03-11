package org.mineplugin.locusazzurro.icaruswings.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

/**
 * @author DustW
 **/
public interface ITickableBlockEntity<TYPE extends BlockEntity> {
    TYPE getBlockEntity();
    void tick();

    /**
     * 需要的时候调这个
     * @param <OTHER> getTicker提供的
     * @param <SELF> 自己
     */
    static <OTHER extends BlockEntity, SELF extends BlockEntity & ITickableBlockEntity<SELF>> BlockEntityTicker<SELF> getTicker(Level level, BlockEntityType<SELF> type, BlockEntityType<OTHER> current) {
        return level.isClientSide ? null : createTickerHelper(type, current, ITickableBlockEntity::tick);
    }

    private static <SELF extends BlockEntity & ITickableBlockEntity<SELF>> void tick(Level level, BlockPos pos, BlockState state, SELF self) {
        self.tick();
    }

    @Nullable
    private static <OTHER extends BlockEntity, SELF extends BlockEntity & ITickableBlockEntity<SELF>> BlockEntityTicker<SELF> createTickerHelper(BlockEntityType<SELF> type, BlockEntityType<OTHER> current, BlockEntityTicker<SELF> ticker) {
        return current == type ? ticker : null;
    }
}

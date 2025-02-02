package org.mineplugin.locusazzurro.icaruswings.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.AmphoraBlockEntity;

import javax.annotation.Nullable;

public class Amphora extends BaseEntityBlock{
	
	private static final VoxelShape SHAPE_LOWER = box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape SHAPE_UPPER = box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public static final MapCodec<Amphora> CODEC = simpleCodec(properties -> new Amphora());

	public Amphora() {
		super(Properties.of()
				.strength(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(FACING, Direction.NORTH)
				.setValue(TYPE, ChestType.SINGLE)
		);
	}

	@Override
	public VoxelShape getShape(BlockState stateIn, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) ? SHAPE_UPPER : SHAPE_LOWER;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState stateIn, BlockGetter pLevel, BlockPos pPos) {
		return (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) ? SHAPE_UPPER : SHAPE_LOWER;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos blockpos = context.getClickedPos();
		if (blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)) {
			return this.defaultBlockState()
					.setValue(FACING, context.getHorizontalDirection())
					.setValue(HALF, DoubleBlockHalf.LOWER);
		} else {
			return null;
		}
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateIn) {
	      stateIn.add(HALF, FACING, TYPE);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public RenderShape getRenderShape(BlockState p_149645_1_) {
		return RenderShape.MODEL;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
	}

	@Override
	public boolean canSurvive(BlockState stateIn, LevelReader worldReaderIn, BlockPos posIn) {
		BlockPos below = posIn.below();
		BlockState blockstate = worldReaderIn.getBlockState(below);
		return stateIn.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(worldReaderIn, below, Direction.UP) : blockstate.is(this);
	}

	@Override
	public BlockState updateShape(BlockState pState, Direction dirIn, BlockState pNeighborState, LevelAccessor worldIn, BlockPos pCurrentPos, BlockPos pNeighborPos) {
		DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
		if (dirIn.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (dirIn == Direction.UP)) {
			return pNeighborState.is(this) && pNeighborState.getValue(HALF) != doubleblockhalf ? pState.setValue(FACING, pNeighborState.getValue(FACING)) : Blocks.AIR.defaultBlockState();
		} 
		else {
			return doubleblockhalf == DoubleBlockHalf.LOWER && dirIn == Direction.DOWN && !pState.canSurvive(worldIn, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, dirIn, pNeighborState, worldIn, pCurrentPos, pNeighborPos);
		}
	}

	@Override
	public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
		if (!pLevel.isClientSide && pPlayer.isCreative()) {
			preventCreativeDropFromBottomPart(pLevel, pPos, pState, pPlayer);
		}
		super.playerDestroy(pLevel, pPlayer, pPos, pState,pBlockEntity, pTool);
	}


	@Override
	public PushReaction getPistonPushReaction(BlockState pState) {
		return PushReaction.BLOCK;
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState stateIn) {
		return (stateIn.getValue(HALF) == DoubleBlockHalf.LOWER);
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState pState, Level pLevel, BlockPos pPos) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(pLevel.getBlockEntity(pPos));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState state) {
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return new AmphoraBlockEntity(pPos, state);
		}

		return null;
	}


	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}
		else {
			BlockEntity tileentity;
			if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
				tileentity = level.getBlockEntity(pos.below());	}
			else { tileentity = level.getBlockEntity(pos); }
			if (tileentity instanceof AmphoraBlockEntity) {
				player.openMenu((AmphoraBlockEntity) tileentity);
			}
			return InteractionResult.CONSUME;
		}
	}

	@Override
	public void onRemove(BlockState pState, Level levelIn, BlockPos pos, BlockState pNewState, boolean pIsMoving) {
		if (!pState.is(pNewState.getBlock())) {
			BlockEntity tileentity = levelIn.getBlockEntity(pos);
			if (tileentity instanceof Container) {
				Containers.dropContents(levelIn, pos, (Container) tileentity);
				levelIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(pState, levelIn, pos, pNewState, pIsMoving);
		}
	}

	protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = pos.below();
			BlockState blockstate = level.getBlockState(blockpos);
			if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				level.setBlock(blockpos, blockstate1, 35);
				level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			}
		}
	}

}

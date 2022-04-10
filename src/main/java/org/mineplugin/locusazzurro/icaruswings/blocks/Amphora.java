package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.AmphoraTileEntity;

import javax.annotation.Nullable;

public class Amphora extends BaseEntityBlock{
	
	private static final VoxelShape SHAPE_LOWER = box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape SHAPE_UPPER = box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public Amphora() {
		super(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of(Material.STONE)
				.strength(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.noOcclusion());
				//todo tag .harvestLevel(0)
				//.harvestTool(ToolType.PICKAXE));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(FACING, net.minecraft.core.Direction.NORTH)
				);
	}

	@Override
	public VoxelShape getShape(BlockState stateIn, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		if (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) {
			return SHAPE_UPPER;
		}

		return SHAPE_LOWER;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState stateIn, BlockGetter p_199600_2_, net.minecraft.core.BlockPos p_199600_3_) {
		if (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) {
			return SHAPE_UPPER;
		}

		return SHAPE_LOWER;
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
	      stateIn.add(HALF, FACING);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_149645_1_) {
		return RenderShape.MODEL;
	}
	
	@Override
	public void setPlacedBy(Level worldIn, net.minecraft.core.BlockPos posIn, BlockState stateIn, LivingEntity entityIn, ItemStack stackIn) {
	    worldIn.setBlock(posIn.above(), stateIn.setValue(HALF, DoubleBlockHalf.UPPER), 3);
		if (stackIn.hasCustomHoverName()) {
			BlockEntity tileentity = worldIn.getBlockEntity(posIn);
			if (tileentity instanceof AmphoraTileEntity) {
				((AmphoraTileEntity) tileentity).setCustomName(stackIn.getHoverName());
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState stateIn, LevelReader worldReaderIn, BlockPos posIn) {
		net.minecraft.core.BlockPos blockpos = posIn.below();
		BlockState blockstate = worldReaderIn.getBlockState(blockpos);
		return stateIn.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(worldReaderIn, blockpos, net.minecraft.core.Direction.UP) : blockstate.is(this);
	}

	@Override
	public BlockState updateShape(BlockState stateInA, Direction dirIn, BlockState stateInB, LevelAccessor worldIn, BlockPos posInA, BlockPos posInB) {
		DoubleBlockHalf doubleblockhalf = stateInA.getValue(HALF);
		if (dirIn.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (dirIn == Direction.UP)) {
			return stateInB.is(this) && stateInB.getValue(HALF) != doubleblockhalf ? stateInA.setValue(FACING, stateInB.getValue(FACING)) : net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
		} 
		else {
			return doubleblockhalf == DoubleBlockHalf.LOWER && dirIn == net.minecraft.core.Direction.DOWN && !stateInA.canSurvive(worldIn, posInA) ? net.minecraft.world.level.block.Blocks.AIR.defaultBlockState() : super.updateShape(stateInA, dirIn, stateInB, worldIn, posInA, posInB);
		}
	}
	
	@Override
	public void playerWillDestroy(Level p_176208_1_, BlockPos p_176208_2_, BlockState p_176208_3_, Player p_176208_4_) {
		if (!p_176208_1_.isClientSide && p_176208_4_.isCreative()) {
			preventCreativeDropFromBottomPart(p_176208_1_, p_176208_2_, p_176208_3_, p_176208_4_);
		}
		super.playerWillDestroy(p_176208_1_, p_176208_2_, p_176208_3_, p_176208_4_);
	}
	
	@Override
	public PushReaction getPistonPushReaction(BlockState p_149656_1_) {
		return PushReaction.BLOCK;
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState stateIn) {
		return (stateIn.getValue(HALF) == DoubleBlockHalf.LOWER);
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState p_180641_1_, Level p_180641_2_, BlockPos p_180641_3_) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_180641_2_.getBlockEntity(p_180641_3_));
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState state) {
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return new AmphoraTileEntity(pPos, state);
		}

		return null;
	}
	
	@Override
	public InteractionResult use(BlockState stateIn, Level worldIn, net.minecraft.core.BlockPos posIn, Player playerIn, InteractionHand handIn, BlockHitResult rayIn) {
		if (worldIn.isClientSide) {
			return InteractionResult.SUCCESS;
		} 
		else {
			BlockEntity tileentity;
			if (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) {
				tileentity = worldIn.getBlockEntity(posIn.below());	}
			else { tileentity = worldIn.getBlockEntity(posIn); }
			if (tileentity instanceof AmphoraTileEntity) {
				playerIn.openMenu((AmphoraTileEntity) tileentity);
			}
			return InteractionResult.CONSUME;
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState p_196243_1_, Level p_196243_2_, net.minecraft.core.BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
		if (!p_196243_1_.is(p_196243_4_.getBlock())) {
			BlockEntity tileentity = p_196243_2_.getBlockEntity(p_196243_3_);
			if (tileentity instanceof Container) {
				Containers.dropContents(p_196243_2_, p_196243_3_, (Container) tileentity);
				p_196243_2_.updateNeighbourForOutputSignal(p_196243_3_, this);
			}

			super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
		}
	}
	
	protected static void preventCreativeDropFromBottomPart(Level p_241471_0_, BlockPos p_241471_1_, BlockState p_241471_2_, Player p_241471_3_) {
		DoubleBlockHalf doubleblockhalf = p_241471_2_.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			net.minecraft.core.BlockPos blockpos = p_241471_1_.below();
			BlockState blockstate = p_241471_0_.getBlockState(blockpos);
			if (blockstate.getBlock() == p_241471_2_.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				p_241471_0_.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
				p_241471_0_.levelEvent(p_241471_3_, 2001, blockpos, net.minecraft.world.level.block.Block.getId(blockstate));
			}
		}
	}
}

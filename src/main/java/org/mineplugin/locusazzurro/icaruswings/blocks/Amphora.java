package org.mineplugin.locusazzurro.icaruswings.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class Amphora extends ContainerBlock{
	
	private static final VoxelShape SHAPE_LOWER = box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape SHAPE_UPPER = box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;

	public Amphora() {
		super(AbstractBlock.Properties.of(Material.STONE)
				.strength(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.noOcclusion()
				.harvestLevel(0)
				.harvestTool(ToolType.PICKAXE));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(FACING, Direction.NORTH)
				);
	}

	@Override
	public VoxelShape getShape(BlockState stateIn, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		if (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) return SHAPE_UPPER;
		return SHAPE_LOWER;
	}

	@Override
	public VoxelShape getInteractionShape(BlockState stateIn, IBlockReader p_199600_2_, BlockPos p_199600_3_) {
		if (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) return SHAPE_UPPER;
		return SHAPE_LOWER;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateIn) {
	      stateIn.add(HALF, FACING);
	}
	
	@Override
	public BlockRenderType getRenderShape(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public void setPlacedBy(World worldIn, BlockPos posIn, BlockState stateIn, LivingEntity entityIn, ItemStack stackIn) {
	    worldIn.setBlock(posIn.above(), stateIn.setValue(HALF, DoubleBlockHalf.UPPER), 3);
		if (stackIn.hasCustomHoverName()) {
			TileEntity tileentity = worldIn.getBlockEntity(posIn);
			if (tileentity instanceof AmphoraTileEntity) {
				((AmphoraTileEntity) tileentity).setCustomName(stackIn.getHoverName());
			}
		}
	}
	
	@Override
	public boolean canSurvive(BlockState stateIn, IWorldReader worldReaderIn, BlockPos posIn) {
		BlockPos blockpos = posIn.below();
		BlockState blockstate = worldReaderIn.getBlockState(blockpos);
		return stateIn.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(worldReaderIn, blockpos, Direction.UP) : blockstate.is(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState stateInA, Direction dirIn, BlockState stateInB, IWorld worldIn, BlockPos posInA, BlockPos posInB) {
		DoubleBlockHalf doubleblockhalf = stateInA.getValue(HALF);
		if (dirIn.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (dirIn == Direction.UP)) {
			return stateInB.is(this) && stateInB.getValue(HALF) != doubleblockhalf ? stateInA.setValue(FACING, stateInB.getValue(FACING)) : Blocks.AIR.defaultBlockState();
		} 
		else {
			return doubleblockhalf == DoubleBlockHalf.LOWER && dirIn == Direction.DOWN && !stateInA.canSurvive(worldIn, posInA) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateInA, dirIn, stateInB, worldIn, posInA, posInB);
		}
	}
	
	@Override
	public void playerWillDestroy(World p_176208_1_, BlockPos p_176208_2_, BlockState p_176208_3_, PlayerEntity p_176208_4_) {
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
	public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
		return Container.getRedstoneSignalFromBlockEntity(p_180641_2_.getBlockEntity(p_180641_3_));
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
		return new AmphoraTileEntity();
	}
	
	@Override
	public boolean hasTileEntity (BlockState state) {
		return (state.getValue(HALF) == DoubleBlockHalf.LOWER);
	}
	
	@Override
	public ActionResultType use(BlockState stateIn, World worldIn, BlockPos posIn, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult rayIn) {
		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		} 
		else {
			TileEntity tileentity;
			if (stateIn.getValue(HALF) == DoubleBlockHalf.UPPER) {
				tileentity = worldIn.getBlockEntity(posIn.below());	}
			else { tileentity = worldIn.getBlockEntity(posIn); }
			if (tileentity instanceof AmphoraTileEntity) {
				playerIn.openMenu((AmphoraTileEntity) tileentity);
			}
			return ActionResultType.CONSUME;
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
		if (!p_196243_1_.is(p_196243_4_.getBlock())) {
			TileEntity tileentity = p_196243_2_.getBlockEntity(p_196243_3_);
			if (tileentity instanceof IInventory) {
				InventoryHelper.dropContents(p_196243_2_, p_196243_3_, (IInventory) tileentity);
				p_196243_2_.updateNeighbourForOutputSignal(p_196243_3_, this);
			}

			super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
		}
	}
	
	protected static void preventCreativeDropFromBottomPart(World p_241471_0_, BlockPos p_241471_1_, BlockState p_241471_2_, PlayerEntity p_241471_3_) {
		DoubleBlockHalf doubleblockhalf = p_241471_2_.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = p_241471_1_.below();
			BlockState blockstate = p_241471_0_.getBlockState(blockpos);
			if (blockstate.getBlock() == p_241471_2_.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				p_241471_0_.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
				p_241471_0_.levelEvent(p_241471_3_, 2001, blockpos, Block.getId(blockstate));
			}
		}
	}
}

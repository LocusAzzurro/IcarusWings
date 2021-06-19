package org.mineplugin.locusazzurro.icaruswings.blocks;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class MeadPot extends Block{
	
	public static final EnumProperty<MeadPotState> MEAD_POT_STATE = EnumProperty.create("state", MeadPotState.class);
	public static final EnumProperty<MeadPotState> STATE = MEAD_POT_STATE;
	
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = VoxelShapes.join(
			VoxelShapes.block(), INSIDE, IBooleanFunction.ONLY_FIRST);

	public MeadPot() {
		super(AbstractBlock
				.Properties.of(Material.STONE)
				.strength(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops()
				.harvestLevel(2)
				.harvestTool(ToolType.PICKAXE)
				);
		this.registerDefaultState(this.stateDefinition.any().setValue(STATE, MeadPotState.EMPTY));
	}
	
	@Override
	public boolean hasTileEntity (BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity (BlockState state, IBlockReader world) {
		return new MeadPotTileEntity();
	}
	
	@Override
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState p_199600_1_, IBlockReader p_199600_2_, BlockPos p_199600_3_) {
		return INSIDE;
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
		return true;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState stateIn_, World worldIn, BlockPos pos) {
		float progress = ((MeadPotTileEntity) worldIn.getBlockEntity(pos)).getFermentationProgress();
		float fermTime = MeadPotTileEntity.getFermentationTime();
		float progressPerc = progress / fermTime;
		return (int) (progressPerc * 15);
	}
	
	@Override
	public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            MeadPotTileEntity meadPotTE = (MeadPotTileEntity) worldIn.getBlockEntity(pos);
            ItemStack stackIn = player.getItemInHand(handIn);
            if (stackIn.getItem() == Items.HONEY_BOTTLE && stackIn.getCount() >= 4
            		&& !meadPotTE.isFermenting() && !meadPotTE.isComplete()) {
            	ItemStack stackOut = new ItemStack(Items.GLASS_BOTTLE, 4);
            	stackIn.shrink(4);
            	if (!player.inventory.add(stackOut)) player.drop(stackOut, false);
            	meadPotTE.startFermeting();
            	return ActionResultType.SUCCESS;
            }
            if (stackIn.getItem() == Items.GLASS_BOTTLE && meadPotTE.isComplete()) {
            	ItemStack stackOut = new ItemStack(ItemRegistry.mead.get());
            	stackIn.shrink(1);
            	if (!player.inventory.add(stackOut)) player.drop(stackOut, false);
            	meadPotTE.setEmpty();
            	return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }
        return ActionResultType.PASS;
        
    }
	
	//BLOCKSTATES
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		state.add(STATE);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext blockUse) {
		return this.defaultBlockState().setValue(STATE, MeadPotState.EMPTY);
	}
	
	
	public static enum MeadPotState implements IStringSerializable{
		EMPTY("empty"), 
		FERMENTING("fermenting"), 
		COMPLETE("complete");
		
		private final String name;
		
		private MeadPotState(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public String getSerializedName() {
			return this.name;
		}
	}
}

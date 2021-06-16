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
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class MeadPot extends Block{
	
	public static final EnumProperty<MeadPotState> MEAD_POT_STATE = EnumProperty.create("state", MeadPotState.class);
	public static final EnumProperty<MeadPotState> STATE = MEAD_POT_STATE;
	
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
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            MeadPotTileEntity meadPotTE = (MeadPotTileEntity) worldIn.getBlockEntity(pos);
            ItemStack stack = player.getItemInHand(handIn);
            if (stack.getItem() == Items.HONEY_BOTTLE && stack.getCount() >= 4
            		&& !meadPotTE.isFermenting() && !meadPotTE.isComplete()) {
            	stack.shrink(4);
            	player.inventory.add(new ItemStack(Items.GLASS_BOTTLE, 4));
            	meadPotTE.startFermeting();
            	return ActionResultType.SUCCESS;
            }
            if (stack.getItem() == Items.GLASS_BOTTLE && meadPotTE.isComplete()) {
            	stack.shrink(1);
            	player.inventory.add(new ItemStack(ItemRegistry.mead.get()));
            	meadPotTE.setEmpty();
            	return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }
        return ActionResultType.PASS;
        
    }
	
	//define default states
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

package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.MeadPotTileEntity;
import org.mineplugin.locusazzurro.icaruswings.blocks.blockentities.ITickableBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.TileEntityTypeRegistry;

import java.util.Random;

public class MeadPot extends BaseEntityBlock {
	
	public static final EnumProperty<MeadPotState> STATE = EnumProperty.create("state", MeadPotState.class);
	
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = Shapes.join(
			Shapes.block(), INSIDE, BooleanOp.ONLY_FIRST);

	private final double particleR = 233D / 255D;
	private final double particleG = 147D / 255D;
	private final double particleB = 38D / 255D;
	
	public MeadPot() {
		super(BlockBehaviour.Properties.of(Material.STONE)
				.strength(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops()
				);
		this.registerDefaultState(this.stateDefinition.any().setValue(STATE, MeadPotState.EMPTY));
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return ITickableBlockEntity.getTicker(pLevel, TileEntityTypeRegistry.meadPotTileEntity.get(), pBlockEntityType);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new MeadPotTileEntity(pPos, pState);
	}
	
	@Override
	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		return SHAPE;
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState p_199600_1_, BlockGetter p_199600_2_, BlockPos p_199600_3_) {
		return INSIDE;
	}

	@Override
	public RenderShape getRenderShape(BlockState p_149645_1_) {
		return RenderShape.MODEL;
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
		return true;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState stateIn_, Level worldIn, BlockPos pos) {
		float progress = ((MeadPotTileEntity) worldIn.getBlockEntity(pos)).getFermentationProgress();
		float fermTime = MeadPotTileEntity.getFermentationTime();
		float progressPerc = progress / fermTime;
		return (int) (progressPerc * 15);
	}

	@Override
	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return false;
	}
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND && worldIn.getBlockEntity(pos) != null) {
            MeadPotTileEntity meadPotTE = (MeadPotTileEntity) worldIn.getBlockEntity(pos);
            ItemStack stackIn = player.getItemInHand(handIn);
            if (stackIn.getItem() == Items.HONEY_BOTTLE && stackIn.getCount() >= 4
            		&& !meadPotTE.isFermenting() && !meadPotTE.isComplete()) {
            	ItemStack stackOut = new ItemStack(Items.GLASS_BOTTLE, 4);
            	stackIn.shrink(4);
				ItemHandlerHelper.giveItemToPlayer(player, stackOut);
            	meadPotTE.startFermenting();
            	worldIn.playSound(null, pos, SoundRegistry.meadPotBrew.get(), SoundSource.BLOCKS, 2.0f, 1.3f);
            	return InteractionResult.SUCCESS;
            }
            if (stackIn.getItem() == ItemRegistry.glassJar.get() && meadPotTE.isComplete()) {
            	ItemStack stackOut = new ItemStack(ItemRegistry.mead.get());
            	stackIn.shrink(1);
				ItemHandlerHelper.giveItemToPlayer(player, stackOut);
            	meadPotTE.setEmpty();
            	worldIn.playSound(null, pos, SoundRegistry.meadPotBrew.get(), SoundSource.BLOCKS, 2.0f, 1.3f);
            	return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
        
    }

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rng) {
		if(stateIn.getValue(STATE) == MeadPotState.FERMENTING) {
		for (int i = 0; i < 3; ++i) {
			int j = rng.nextInt(2) * 2 - 1;
			int k = rng.nextInt(2) * 2 - 1;
			double d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
			double d1 = (float) pos.getY() + 0.5f +rng.nextFloat();
			double d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
			worldIn.addParticle(ParticleTypes.ENTITY_EFFECT, d0, d1, d2, particleR, particleG, particleB);
		}
		}
	}
	
	//BLOCK STATES
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(STATE);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockUse) {
		return this.defaultBlockState().setValue(STATE, MeadPotState.EMPTY);
	}
	
	
	public enum MeadPotState implements StringRepresentable {
		EMPTY("empty"), 
		FERMENTING("fermenting"), 
		COMPLETE("complete");
		
		private final String name;
		
		MeadPotState(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}

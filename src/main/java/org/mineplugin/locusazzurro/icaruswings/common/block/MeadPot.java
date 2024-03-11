package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
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
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.ITickableBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.MeadPotBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.item.WorldEssence;
import org.mineplugin.locusazzurro.icaruswings.common.item.Mead;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockEntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.IWLazy;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MeadPot extends BaseEntityBlock {
	
	public static final EnumProperty<MeadPotState> STATE = EnumProperty.create("state", MeadPotState.class);
	public static final EnumProperty<Mead.Infusion> INFUSION = EnumProperty.create("infusion", Mead.Infusion.class);
	
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = Shapes.join(
			Shapes.block(), INSIDE, BooleanOp.ONLY_FIRST);

	private static final double particleR = 233D / 255D;
	private static final double particleG = 147D / 255D;
	private static final double particleB = 38D / 255D;
	
	public MeadPot() {
		super(BlockBehaviour.Properties.of()
				.strength(1.5f, 6.0f)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops()
				);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(STATE, MeadPotState.EMPTY)
				.setValue(INFUSION, Mead.Infusion.NONE));
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return ITickableBlockEntity.getTicker(pLevel, BlockEntityTypeRegistry.MEAD_POT_BLOCK_ENTITY.get(), pBlockEntityType);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new MeadPotBlockEntity(pPos, pState);
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return INSIDE;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState pState) {
		return true;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState stateIn, Level worldIn, BlockPos pos) {
		if (worldIn.getBlockEntity(pos) != null && worldIn.getBlockEntity(pos) instanceof MeadPotBlockEntity meadPotTE){
			float progress = meadPotTE.getFermentationProgress();
			float fermentationTime = MeadPotBlockEntity.getFermentationTime();
			return (int) ((progress / fermentationTime) * 15);
		}
		return 0;
	}

	@Override
	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return false;
	}
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND && worldIn.getBlockEntity(pos) instanceof MeadPotBlockEntity meadPotTE) {
            ItemStack stackIn = player.getItemInHand(handIn);
            if (stackIn.getItem() == Items.HONEY_BOTTLE && stackIn.getCount() >= 4
            		&& !meadPotTE.isFermenting() && !meadPotTE.isComplete()) {
				if (!player.getAbilities().instabuild) {
					ItemStack stackOut = new ItemStack(Items.GLASS_BOTTLE, 4);
					stackIn.shrink(4);
					ItemHandlerHelper.giveItemToPlayer(player, stackOut);
				}
            	meadPotTE.startFermenting();
            	worldIn.playSound(null, pos, SoundRegistry.meadPotBrew.get(), SoundSource.BLOCKS, 2.0f, 1.3f);
            	return InteractionResult.SUCCESS;
            }
            if (stackIn.getItem() == ItemRegistry.GLASS_JAR.get() && meadPotTE.isComplete()) {
				Item itemOut = ItemRegistry.MEAD.get();
				if (!player.getAbilities().instabuild) stackIn.shrink(1);
				for (Map.Entry<Mead.Infusion, IWLazy<Item>> entry : infusionMapOut.entrySet()) {
					if (state.getValue(INFUSION) == entry.getKey()) {
						itemOut = entry.getValue().get();
						break;
					}
				}
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(itemOut));
            	meadPotTE.setEmpty();
				worldIn.setBlock(pos, worldIn.getBlockState(pos).setValue(INFUSION, Mead.Infusion.NONE), 3);
            	worldIn.playSound(null, pos, SoundRegistry.meadPotBrew.get(), SoundSource.BLOCKS, 2.0f, 1.3f);
            	return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
        
    }

	@Override
	public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
		if (pEntity instanceof ItemEntity itemEntity
				&& validInfusionItems.test(itemEntity.getItem())
				&& pState.getValue(STATE) == MeadPotState.FERMENTING && pState.getValue(INFUSION) == Mead.Infusion.NONE){
			ItemStack stack = itemEntity.getItem();
			infusionMapIn.forEach((item, infusion) -> {
				if (stack.is(item.get())) {
					pLevel.setBlock(pPos, pLevel.getBlockState(pPos).setValue(INFUSION, infusion), 3);
				}});
			pLevel.playSound(null, pPos, SoundRegistry.meadPotBrew.get(), SoundSource.BLOCKS, 2.0f, 1.0f);
			if (pLevel instanceof ServerLevel server){
				server.sendParticles(ParticleTypes.POOF,
						pPos.getX() + 0.5,
						pPos.getY() + 0.8,
						pPos.getZ() + 0.5,
						30, 0.2, 0.2, 0.2, 0.01);
			}
			stack.shrink(1);
			pLevel.blockEntityChanged(pPos);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rng) {
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

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(STATE);
		state.add(INFUSION);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockUse) {
		return this.defaultBlockState()
				.setValue(STATE, MeadPotState.EMPTY)
				.setValue(INFUSION, Mead.Infusion.NONE);
	}
	
	public static final Predicate<ItemStack> validInfusionItems = (itemStack) ->
			itemStack.getItem() instanceof WorldEssence
			|| itemStack.is(Items.GOLDEN_APPLE)
			|| itemStack.is(ItemRegistry.HERB_BUNCH.get());

	private static final Map<IWLazy<Item>, Mead.Infusion> infusionMapIn = new HashMap<>();
	private static final Map<Mead.Infusion, IWLazy<Item> > infusionMapOut = new HashMap<>();

	static {
		infusionMapIn.put(IWLazy.of(ItemRegistry.ZEPHIR_ESSENCE), Mead.Infusion.ZEPHIR);
		infusionMapIn.put(IWLazy.of(ItemRegistry.NETHER_ESSENCE), Mead.Infusion.NETHER);
		infusionMapIn.put(IWLazy.of(ItemRegistry.VOID_ESSENCE), Mead.Infusion.VOID);
		infusionMapIn.put(IWLazy.of(ItemRegistry.HERB_BUNCH), Mead.Infusion.HERBS);
		infusionMapIn.put(IWLazy.of(() -> Items.GOLDEN_APPLE), Mead.Infusion.GOLDEN_APPLE);
		infusionMapOut.put(Mead.Infusion.NONE, IWLazy.of(ItemRegistry.MEAD));
		infusionMapOut.put(Mead.Infusion.ZEPHIR, IWLazy.of(ItemRegistry.ZEPHIR_INFUSED_MEAD));
		infusionMapOut.put(Mead.Infusion.NETHER, IWLazy.of(ItemRegistry.NETHER_INFUSED_MEAD));
		infusionMapOut.put(Mead.Infusion.VOID, IWLazy.of(ItemRegistry.VOID_INFUSED_MEAD));
		infusionMapOut.put(Mead.Infusion.HERBS, IWLazy.of(ItemRegistry.HERBS_INFUSED_MEAD));
		infusionMapOut.put(Mead.Infusion.GOLDEN_APPLE, IWLazy.of(ItemRegistry.GOLDEN_APPLE_INFUSED_MEAD));
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

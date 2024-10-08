package org.mineplugin.locusazzurro.icaruswings.common.block;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.ITickableBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.block.entity.MeadPotBlockEntity;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.common.item.Mead;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockEntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MeadPot extends BaseEntityBlock {
	
	public static final EnumProperty<MeadPotState> STATE = EnumProperty.create("state", MeadPotState.class);
	public static final EnumProperty<Mead.Infusion> INFUSION = EnumProperty.create("infusion", Mead.Infusion.class);

	public static final MapCodec<MeadPot> CODEC = simpleCodec(properties -> new MeadPot());
	
	private static final VoxelShape INSIDE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape SHAPE = Shapes.join(
			Shapes.block(), INSIDE, BooleanOp.ONLY_FIRST);

	private static final float PARTICLE_R = 233f / 255f;
	private static final float PARTICLE_G = 147f / 255f;
	private static final float PARTICLE_B = 38f / 255f;

	public static final Predicate<ItemStack> isValidInfusionItem = (itemStack) ->
			itemStack.is(ModTags.WORLD_ESSENCES) || itemStack.is(Items.GOLDEN_APPLE) || itemStack.is(ItemRegistry.HERB_BUNCH.get());

	private static final Map<Supplier<Item>, Mead.Infusion> INFUSION_MAP_IN = new HashMap<>();
	private static final Map<Mead.Infusion, Supplier<Item>> INFUSION_MAP_OUT = new HashMap<>();

	static {
		INFUSION_MAP_IN.put(Suppliers.memoize(ItemRegistry.ZEPHIR_ESSENCE::get), Mead.Infusion.ZEPHIR);
		INFUSION_MAP_IN.put(Suppliers.memoize(ItemRegistry.NETHER_ESSENCE::get), Mead.Infusion.NETHER);
		INFUSION_MAP_IN.put(Suppliers.memoize(ItemRegistry.VOID_ESSENCE::get), Mead.Infusion.VOID);
		INFUSION_MAP_IN.put(Suppliers.memoize(ItemRegistry.HERB_BUNCH::get), Mead.Infusion.HERBS);
		INFUSION_MAP_IN.put(Suppliers.memoize(() -> Items.GOLDEN_APPLE), Mead.Infusion.GOLDEN_APPLE);
		INFUSION_MAP_OUT.put(Mead.Infusion.NONE, Suppliers.memoize(ItemRegistry.MEAD::get));
		INFUSION_MAP_OUT.put(Mead.Infusion.ZEPHIR, Suppliers.memoize(ItemRegistry.ZEPHIR_INFUSED_MEAD::get));
		INFUSION_MAP_OUT.put(Mead.Infusion.NETHER, Suppliers.memoize(ItemRegistry.NETHER_INFUSED_MEAD::get));
		INFUSION_MAP_OUT.put(Mead.Infusion.VOID, Suppliers.memoize(ItemRegistry.VOID_INFUSED_MEAD::get));
		INFUSION_MAP_OUT.put(Mead.Infusion.HERBS, Suppliers.memoize(ItemRegistry.HERBS_INFUSED_MEAD::get));
		INFUSION_MAP_OUT.put(Mead.Infusion.GOLDEN_APPLE, Suppliers.memoize(ItemRegistry.GOLDEN_APPLE_INFUSED_MEAD::get));
	}

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
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
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
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
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
            	worldIn.playSound(null, pos, SoundRegistry.MEAD_POT_BREW.get(), SoundSource.BLOCKS, 2.0f, 1.3f);
            	return ItemInteractionResult.CONSUME;
            }
            if (stackIn.getItem() == ItemRegistry.GLASS_JAR.get() && meadPotTE.isComplete()) {
				Item itemOut = ItemRegistry.MEAD.get();
				if (!player.getAbilities().instabuild) stackIn.shrink(1);
				for (Map.Entry<Mead.Infusion, Supplier<Item>> entry : INFUSION_MAP_OUT.entrySet()) {
					if (state.getValue(INFUSION) == entry.getKey()) {
						itemOut = entry.getValue().get();
						break;
					}
				}
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(itemOut));
            	meadPotTE.setEmpty();
				worldIn.setBlock(pos, worldIn.getBlockState(pos).setValue(INFUSION, Mead.Infusion.NONE), 3);
            	worldIn.playSound(null, pos, SoundRegistry.MEAD_POT_BREW.get(), SoundSource.BLOCKS, 2.0f, 1.3f);
				return ItemInteractionResult.SUCCESS;
            }
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        
    }

	@Override
	public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
		if (pEntity instanceof ItemEntity itemEntity
				&& isValidInfusionItem.test(itemEntity.getItem())
				&& pState.getValue(STATE) == MeadPotState.FERMENTING && pState.getValue(INFUSION) == Mead.Infusion.NONE){
			ItemStack stack = itemEntity.getItem();
			INFUSION_MAP_IN.forEach((item, infusion) -> {
				if (stack.is(item.get())) {
					pLevel.setBlock(pPos, pLevel.getBlockState(pPos).setValue(INFUSION, infusion), 3);
				}});
			pLevel.playSound(null, pPos, SoundRegistry.MEAD_POT_BREW.get(), SoundSource.BLOCKS, 2.0f, 1.0f);
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
            double x = pos.getX() + rng.nextFloat();
			double y = pos.getY() + 0.5f + rng.nextFloat();
			double z = pos.getZ() + rng.nextFloat();
			double dx = rng.nextFloat() - 0.5f;
			double dy = rng.nextFloat() * 0.25f;
			double dz = rng.nextFloat() - 0.5f;
			ColorParticleOption colorParticleOption = ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, PARTICLE_R, PARTICLE_G, PARTICLE_B);
			worldIn.addParticle(colorParticleOption, x, y, z, dx, dy, dz);
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

package org.mineplugin.locusazzurro.icaruswings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.Random;

import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.greekFire;
import static org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry.greekFireFlowing;

import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;

public abstract class GreekFireFluid extends ForgeFlowingFluid{

    public static final ResourceLocation GREEK_FIRE_STILL = new ResourceLocation(ModData.MOD_ID, "block/greek_fire_still");
    public static final ResourceLocation GREEK_FIRE_FLOWING = new ResourceLocation(ModData.MOD_ID, "block/greek_fire_flow");

    public static ForgeFlowingFluid.Properties fluidProperties = new ForgeFlowingFluid.Properties(greekFire, greekFireFlowing,null)
            .bucket(ItemRegistry.greekFireBucket).block(BlockRegistry.greekFire).slopeFindDistance(1).explosionResistance(100F);

    public GreekFireFluid(Properties properties) {
        super(properties);
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(GREEK_FIRE_STILL, GREEK_FIRE_FLOWING)
                .color(0xfff79059).density(4000).viscosity(7000).luminosity(15).temperature(1500)
                .translationKey("block.locusazzurro_icaruswings.greek_fire")
                .sound(SoundRegistry.bucketFillGreekFire.get(), SoundRegistry.bucketEmptyGreekFire.get()).build(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(Level worldIn, BlockPos blockPos, FluidState fluidState, Random random) {
        BlockPos blockpos = blockPos.above();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
            int seed = random.nextInt(200);
            if (seed == 0) {
                worldIn.playLocalSound((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), SoundRegistry.greekFireAmbient.get(), SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
            if (seed % 100 == 0){
                double d0 = (double)blockPos.getX() + random.nextDouble();
                double d1 = (double)blockPos.getY() + 1.0D;
                double d2 = (double)blockPos.getZ() + random.nextDouble();
                worldIn.playLocalSound(d0, d1, d2, SoundRegistry.greekFirePop.get(), SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
            if (seed % 50 == 0) {
                double d0 = (double)blockPos.getX() + random.nextDouble();
                double d1 = (double)blockPos.getY() + 1.0D;
                double d2 = (double)blockPos.getZ() + random.nextDouble();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public static class Source extends GreekFireFluid{
        public Source() {
            super(fluidProperties);
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
    }

    public static class Flowing extends GreekFireFluid{
        public Flowing() {
            super(fluidProperties);
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }
}

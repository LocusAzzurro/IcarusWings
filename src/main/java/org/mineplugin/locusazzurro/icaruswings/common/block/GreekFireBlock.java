package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.mineplugin.locusazzurro.icaruswings.registry.FluidRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class GreekFireBlock extends LiquidBlock {

    public GreekFireBlock(){
        super(FluidRegistry.GREEK_FIRE, Block.Properties.of().liquid().noCollission().strength(100.0F).noLootTable().lightLevel((b) -> 15).noLootTable());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!entity.fireImmune()) {
            entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 1);
            if (entity.getRemainingFireTicks() == 0) {
                entity.setSecondsOnFire(8);
            }
        }

        entity.hurt(level.damageSources().inFire(), 4);
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void animateTick(BlockState state, Level worldIn, BlockPos blockPos, RandomSource random) {
        BlockPos blockpos = blockPos.above();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
            int seed = random.nextInt(200);
            if (seed == 0) {
                worldIn.playLocalSound((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), SoundRegistry.GREEK_FIRE_AMBIENT.get(), SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
            if (seed % 100 == 0){
                double d0 = (double)blockPos.getX() + random.nextDouble();
                double d1 = (double)blockPos.getY() + 1.0D;
                double d2 = (double)blockPos.getZ() + random.nextDouble();
                worldIn.playLocalSound(d0, d1, d2, SoundRegistry.GREEK_FIRE_POP.get(), SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
            if (seed % 50 == 0) {
                double d0 = (double)blockPos.getX() + random.nextDouble();
                double d1 = (double)blockPos.getY() + 1.0D;
                double d2 = (double)blockPos.getZ() + random.nextDouble();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}

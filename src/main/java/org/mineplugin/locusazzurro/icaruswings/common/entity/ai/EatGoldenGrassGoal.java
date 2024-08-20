package org.mineplugin.locusazzurro.icaruswings.common.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.neoforged.neoforge.event.EventHooks;
import org.mineplugin.locusazzurro.icaruswings.registry.BlockRegistry;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatGoldenGrassGoal extends Goal {

    private static final Predicate<BlockState> IS_GOLDEN_GRASS = BlockStatePredicate.forBlock(BlockRegistry.ELYSIAN_GRASS.get());
    private final Mob mob;
    private final Level level;
    private final Block eatBlock;
    private final Block eatenBlock;
    private int eatAnimationTick;

    public EatGoldenGrassGoal(Mob entity) {
        this.mob = entity;
        this.level = entity.level();
        this.eatBlock = BlockRegistry.ELYSIAN_GRASS_BLOCK.get();
        this.eatenBlock = BlockRegistry.ELYSIAN_SOIL.get();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = this.mob.blockPosition();
            if (IS_GOLDEN_GRASS.test(this.level.getBlockState(blockpos))) {
                return true;
            } else {
                return this.level.getBlockState(blockpos.below()).is(this.eatBlock);
            }
        }
    }

    @Override
    public void start() {
        this.eatAnimationTick = 40;
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.eatAnimationTick = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    @Override
    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == 4) {
            BlockPos mobPos = this.mob.blockPosition();
            if (IS_GOLDEN_GRASS.test(this.level.getBlockState(mobPos))) {
                if (EventHooks.getMobGriefingEvent(this.level, this.mob)) {
                    this.level.destroyBlock(mobPos, false);
                }

                this.mob.ate();
            } else {
                BlockPos mobPosBelow = mobPos.below();
                if (this.level.getBlockState(mobPosBelow).is(this.eatBlock)) {
                    if (EventHooks.getMobGriefingEvent(this.level, this.mob)) {
                        this.level.levelEvent(2001, mobPosBelow, Block.getId(this.eatBlock.defaultBlockState()));
                        this.level.setBlock(mobPosBelow, this.eatenBlock.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }

        }
    }

}

package org.mineplugin.locusazzurro.icaruswings.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatGoldenGrassGoal extends Goal {

    private static final Predicate<BlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS);
    private final MobEntity mob;
    private final World level;
    private Block eatBlock;
    private Block eatenBlock;
    private int eatAnimationTick;

    public EatGoldenGrassGoal(MobEntity entity) {
        this.mob = entity;
        this.level = entity.level;
        this.eatBlock = Blocks.GRASS_BLOCK;
        this.eatenBlock = Blocks.DIRT;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = this.mob.blockPosition();
            if (IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) {
                return true;
            } else {
                return this.level.getBlockState(blockpos.below()).is(this.eatBlock);
            }
        }
    }

    public void start() {
        this.eatAnimationTick = 40;
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.eatAnimationTick = 0;
    }

    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == 4) {
            BlockPos blockpos = this.mob.blockPosition();
            if (IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) {
                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.mob)) {
                    this.level.destroyBlock(blockpos, false);
                }

                this.mob.ate();
            } else {
                BlockPos blockpos1 = blockpos.below();
                if (this.level.getBlockState(blockpos1).is(this.eatBlock)) {
                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.mob)) {
                        this.level.levelEvent(2001, blockpos1, Block.getId(this.eatBlock.defaultBlockState()));
                        this.level.setBlock(blockpos1, this.eatenBlock.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }

        }
    }

}

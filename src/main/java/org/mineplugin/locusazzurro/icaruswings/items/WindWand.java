package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class WindWand extends Item {

    public WindWand() {
        super(new Properties().tab(ModGroup.itemGroup).durability(100));
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return ActionResult.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return ActionResult.consume(itemstack);
        }
    }

    @Override
    public void releaseUsing(ItemStack itemStack, World worldIn, LivingEntity livingIn, int charge) {
        if (livingIn instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) livingIn;
            if (playerIn.getItemInHand(Hand.OFF_HAND).getItem() instanceof AirJar && itemStack.getDamageValue() > 0) {
                ItemStack offhandStack = playerIn.getItemInHand(Hand.OFF_HAND);
                int repairAmount = 0;
                switch (((AirJar) offhandStack.getItem()).getType()) {
                    case ZEPHIR:
                        repairAmount = 1;
                        break;
                    case NETHER:
                        repairAmount = 2;
                        break;
                    case VOID:
                        repairAmount = 3;
                        break;
                }
                if (!playerIn.abilities.instabuild) {
                    offhandStack.shrink(1);
                    ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegistry.glassJar.get()));
                }
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundRegistry.airJarEmpty.get(), SoundCategory.NEUTRAL, 0.5f, 0.6f);
                itemStack.setDamageValue(Math.max(itemStack.getDamageValue() - repairAmount, 0));
            }
            else {
                Vector3d lookVec = playerIn.getLookAngle();
                Vector3d moveVec = playerIn.getDeltaMovement();
                Vector3d yBonus = new Vector3d(0f, 0.5f, 0f);
                int i = this.getUseDuration(itemStack) - charge;
                if (i >= 5) playerIn.setDeltaMovement(moveVec.add(lookVec.reverse().scale(1.5)).add(yBonus));
                else playerIn.setDeltaMovement(moveVec.add(lookVec.scale(2.0)).add(yBonus));
                playerIn.fallDistance = 0;
                if (!worldIn.isClientSide()) {
                    itemStack.hurtAndBreak(1, playerIn, (player) -> {
                        player.broadcastBreakEvent(playerIn.getUsedItemHand());
                    });
                }
                else {
                    for(int j = 0; j < 10; j++){
                        double xR = worldIn.random.nextGaussian() * 0.1;
                        double yR = worldIn.random.nextGaussian() * 0.05;
                        double zR = worldIn.random.nextGaussian() * 0.1;
                        worldIn.addParticle(ParticleTypes.CLOUD, playerIn.getX(), playerIn.getY(), playerIn.getZ(), xR, yR, zR);
                    }
                }
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                        SoundRegistry.windWandBurst.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                playerIn.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }


}

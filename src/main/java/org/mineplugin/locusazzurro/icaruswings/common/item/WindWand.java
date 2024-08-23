package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class WindWand extends Item {

    public WindWand() {
        super(new Properties().durability(100));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level worldIn, LivingEntity livingIn, int charge) {
        if (livingIn instanceof Player playerIn) {
            if (playerIn.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof AirJar && itemStack.getDamageValue() > 0) {
                ItemStack offhandStack = playerIn.getItemInHand(InteractionHand.OFF_HAND);
                int repairAmount = switch (((AirJar) offhandStack.getItem()).getType()) {
                    case ZEPHIR -> 1;
                    case NETHER -> 2;
                    case VOID -> 3;
                };
                if (!playerIn.getAbilities().instabuild) {
                    offhandStack.shrink(1);
                    ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegistry.GLASS_JAR.get()));
                }
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundRegistry.AIR_JAR_EMPTY.get(), SoundSource.NEUTRAL, 0.5f, 0.6f);
                itemStack.setDamageValue(Math.max(itemStack.getDamageValue() - repairAmount, 0));
            }
            else {
                Vec3 lookVec = playerIn.getLookAngle();
                Vec3 moveVec = playerIn.getDeltaMovement();
                Vec3 yBonus = new Vec3(0f, 0.5f, 0f);
                int i = this.getUseDuration(itemStack, playerIn) - charge;
                if (i >= 5) playerIn.setDeltaMovement(moveVec.add(lookVec.reverse().scale(1.5)).add(yBonus));
                else playerIn.setDeltaMovement(moveVec.add(lookVec.scale(2.0)).add(yBonus));
                playerIn.fallDistance = 0;
                if (!worldIn.isClientSide()) {
                    itemStack.hurtAndBreak(1, (ServerLevel) worldIn, playerIn, item -> {});
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
                        SoundRegistry.WIND_WAND_BURST.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                playerIn.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }


}

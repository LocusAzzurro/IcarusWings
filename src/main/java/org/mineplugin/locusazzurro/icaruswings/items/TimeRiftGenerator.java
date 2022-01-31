package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeRiftParticleEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Predicate;

public class TimeRiftGenerator extends ShootableItem {

    public TimeRiftGenerator(){
        super(new Item.Properties().tab(ModGroup.itemGroup).durability(400));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        ItemStack projectile = playerIn.getProjectile(itemStack);
        if (!playerIn.abilities.instabuild && projectile.isEmpty()){
            return ActionResult.pass(itemStack);
        }

        TimeRiftParticleEntity particle = new TimeRiftParticleEntity(playerIn, worldIn);
        particle.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 2.0f, 0.5f);
        particle.setNoGravity(true);
        worldIn.addFreshEntity(particle);
        worldIn.playSound(null, playerIn, SoundRegistry.timeRiftGeneratorFire.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (!worldIn.isClientSide()) {
            itemStack.hurtAndBreak(1, playerIn, (player) -> {
                player.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        if (!playerIn.abilities.instabuild) {
            projectile.shrink(1);
            if (projectile.isEmpty()) {
                playerIn.inventory.removeItem(projectile);
            }
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.success(itemStack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack thisItem, ItemStack repairItem) {
        return repairItem.getItem() == ItemRegistry.synapseRepairKit.get() || super.isValidRepairItem(thisItem, repairItem);
    }

    public static final Predicate<ItemStack> TIME_RIFT_CHARGE = (item) -> item.getItem().equals(ItemRegistry.timeRiftCharge.get());

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return TIME_RIFT_CHARGE;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 32;
    }
}

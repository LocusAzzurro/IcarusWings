package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.ProjectileUtils;

import java.util.function.Predicate;

public class ArtemisLauncher extends ShootableItem {

    public ArtemisLauncher(){
        super(new Properties().tab(ModGroup.itemGroup).durability(400));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack item = playerIn.getItemInHand(handIn);
        ItemStack projectile = playerIn.getProjectile(item);

        if (!playerIn.abilities.instabuild && projectile.isEmpty()){
            return ActionResult.pass(item);
        }

        LivingEntity entity = ProjectileUtils.rayTraceTarget(playerIn, 0.1f, 300, 1);
        ArtemisMissileEntity missile;
        if (entity != null) {
            missile = new ArtemisMissileEntity(worldIn, playerIn, entity);
            missile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 0.5f, 1.0f);
        } else {
            missile = new ArtemisMissileEntity(worldIn, playerIn);
            missile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 2.0f, 1.0f);
        }
        worldIn.addFreshEntity(missile);
        worldIn.playSound(null, missile, SoundRegistry.artemisMissileLaunch.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (!worldIn.isClientSide()) {
            item.hurtAndBreak(1, playerIn, (player) -> {
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
        return ActionResult.success(item);
    }

    @Override
    public boolean isValidRepairItem(ItemStack thisItem, ItemStack repairItem) {
        return repairItem.getItem() == ItemRegistry.synapseRepairKit.get() || super.isValidRepairItem(thisItem, repairItem);
    }

    public static final Predicate<ItemStack> ARTEMIS_MISSILE = (item) -> item.getItem().equals(ItemRegistry.artemisMissile.get());

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARTEMIS_MISSILE;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 32;
    }
}

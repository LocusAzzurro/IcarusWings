package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.ProjectileUtils;

import java.util.function.Predicate;

public class ArtemisLauncher extends ProjectileWeaponItem {

    public ArtemisLauncher(){
        super(new Item.Properties().durability(400));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack item = playerIn.getItemInHand(handIn);
        ItemStack projectile = playerIn.getProjectile(item);

        if (!playerIn.getAbilities().instabuild && projectile.isEmpty()){
            return InteractionResultHolder.pass(item);
        }

        LivingEntity entity = ProjectileUtils.rayTraceTarget(playerIn, 0.1f, 300, 1);
        ArtemisMissileEntity missile;
        if (entity != null) {
            missile = new ArtemisMissileEntity(worldIn, playerIn, entity);
            missile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 0.5f, 1.0f);
        } else {
            missile = new ArtemisMissileEntity(worldIn, playerIn);
            missile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 2.0f, 1.0f);
        }
        worldIn.addFreshEntity(missile);
        worldIn.playSound(null, missile, SoundRegistry.artemisMissileLaunch.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        if (!worldIn.isClientSide()) {
            item.hurtAndBreak(1, playerIn, (player) -> {
                player.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        if (!playerIn.getAbilities().instabuild) {
            projectile.shrink(1);
            if (projectile.isEmpty()) {
                playerIn.getInventory().removeItem(projectile);
            }
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.success(item);
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

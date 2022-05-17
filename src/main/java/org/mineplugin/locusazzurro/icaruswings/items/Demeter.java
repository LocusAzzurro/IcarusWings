package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.KayrosBlastEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.KayrosGenUtils;

import java.util.function.Predicate;

public class Demeter extends ProjectileWeaponItem {

    public Demeter(){
        super(new Item.Properties().tab(ModGroup.itemGroup).durability(600));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        ItemStack charge = playerIn.getProjectile(itemStack);
        if (!playerIn.getAbilities().instabuild && charge.isEmpty()){
            return InteractionResultHolder.pass(itemStack);
        }
        if (!worldIn.isClientSide()) {
            KayrosBlastEntity particle = new KayrosBlastEntity(playerIn, worldIn,
                    KayrosGenUtils.generateTerrainTagWithJitter(playerIn.getX(), playerIn.getZ(), worldIn.random, 32f));
            particle.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 1.0f, 0.5f);
            particle.setNoGravity(true);
            worldIn.addFreshEntity(particle);
            //todo sound effect
            //worldIn.playSound(null, playerIn, SoundRegistry.timeRiftGeneratorFire.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            itemStack.hurtAndBreak(1, playerIn, (player) -> player.broadcastBreakEvent(playerIn.getUsedItemHand()));
        }


        if (!playerIn.getAbilities().instabuild) {
            charge.shrink(1);
            if (charge.isEmpty()) {
                playerIn.getInventory().removeItem(charge);
            }
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public boolean isValidRepairItem(ItemStack thisItem, ItemStack repairItem) {
        return repairItem.getItem() == ItemRegistry.synapseRepairKit.get() || super.isValidRepairItem(thisItem, repairItem);
    }

    public static final Predicate<ItemStack> DEMETER_CHARGE = (item) -> item.getItem().equals(ItemRegistry.demeterCharge.get());

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return DEMETER_CHARGE;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 32;
    }

}

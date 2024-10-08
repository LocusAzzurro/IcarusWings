package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.common.entity.TimeRiftParticleEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class TimeRiftGenerator extends ProjectileWeaponItem {

    public TimeRiftGenerator(){
        super(new Item.Properties().durability(400));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        ItemStack charge = playerIn.getProjectile(itemStack);
        if (!playerIn.getAbilities().instabuild && charge.isEmpty()){
            return InteractionResultHolder.pass(itemStack);
        }

        TimeRiftParticleEntity particle = new TimeRiftParticleEntity(playerIn, worldIn);
        particle.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 2.0f, 0.5f);
        particle.setNoGravity(true);
        worldIn.addFreshEntity(particle);
        worldIn.playSound(null, playerIn, SoundRegistry.TIME_RIFT_GENERATOR_FIRE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        if (!worldIn.isClientSide()) {
            itemStack.hurtAndBreak(1, (ServerLevel) worldIn, playerIn,
                    item -> playerIn.onEquippedItemBroken(item, playerIn.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));
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
        return repairItem.getItem() == ItemRegistry.SYNAPSE_REPAIR_KIT.get() || super.isValidRepairItem(thisItem, repairItem);
    }

    public static final Predicate<ItemStack> TIME_RIFT_CHARGE = (item) -> item.getItem().equals(ItemRegistry.TIME_RIFT_CHARGE.get());

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return TIME_RIFT_CHARGE;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 32;
    }

    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, velocity, inaccuracy);
    }

}

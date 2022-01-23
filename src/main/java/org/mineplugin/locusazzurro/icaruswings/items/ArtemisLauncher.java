package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.ProjectileUtils;

import javax.annotation.Nullable;
import java.util.List;

public class ArtemisLauncher extends Item {

    public ArtemisLauncher(){
        super(new Properties().tab(ModGroup.itemGroup));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack item = playerIn.getItemInHand(handIn);
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
        return ActionResult.success(item);
    }


}

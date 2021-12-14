package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.utils.ProjectileUtils;

public class ArtemisTransportCard extends AbstractTransportCard{

    private static final double POWER = 1.1;

    public ArtemisTransportCard(boolean homing){
        super(CardType.ARTEMIS_HOMING);
    }

    public ArtemisTransportCard(){
        super(CardType.ARTEMIS_SCATTER);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        Item item = itemStack.getItem();
        Vector3d pos = playerIn.getPosition(1f);
        Vector3d look = playerIn.getViewVector(1f);
        float halfPI = (float) Math.PI/2;
        Vector3d[] dPos = new Vector3d[]{
            pos.add(look.yRot(halfPI)).add(0,0,0),
            pos.add(look.yRot(halfPI)).add(0,1,0),
            pos.add(look.yRot(halfPI)).add(0,2,0),
            pos.add(look.yRot(-halfPI)).add(0,0,0),
            pos.add(look.yRot(-halfPI)).add(0,1,0),
            pos.add(look.yRot(-halfPI)).add(0,2,0),
        };
        Vector3d vectorBias = Vector3d.ZERO;
        AbstractTransportCard.CardType type = ((AbstractTransportCard) item).getType();
        if (type == CardType.ARTEMIS_HOMING){
            LivingEntity target = ProjectileUtils.rayTraceTarget(playerIn, 0.1d, 300, 0.2);
            if (target == null){ return ActionResult.pass(itemStack); }
            for (int i = 0; i < 6; i++){
                ArtemisMissileEntity homingMissile = new ArtemisMissileEntity(worldIn, playerIn, target);
                homingMissile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 0.5f, 1.0f);
                homingMissile.moveTo(dPos[i]);
                worldIn.addFreshEntity(homingMissile);
            }
            if (!playerIn.isCreative()){ itemStack.shrink(1); }
            return ActionResult.success(itemStack);
        }
        else if (type == CardType.ARTEMIS_SCATTER){
            double p = POWER;
            for (int i = 0; i < 6; i++){
                if (!worldIn.isClientSide){ vectorBias = randomVector(worldIn); }
                ArtemisMissileEntity missile = new ArtemisMissileEntity(worldIn, playerIn);
                missile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 2.0f, 3.0f);
                missile.moveTo(dPos[i]);
                worldIn.addFreshEntity(missile);
            }
            if (!playerIn.isCreative()){ itemStack.shrink(1);}
            return ActionResult.success(itemStack);
        }
        return ActionResult.pass(itemStack);
    }

    private Vector3d randomVector(World worldIn){
        return new Vector3d(
                worldIn.random.nextDouble() * 2 - 1,
                worldIn.random.nextDouble() * 2 - 1,
                worldIn.random.nextDouble() * 2 - 1)
                .scale(0.05);
    }


}

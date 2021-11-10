package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.utils.ProjectileUtils;

public class ArtemisTransportCard extends AbstractTransportCard{

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
        double vectorBias = 0.1d;
        AbstractTransportCard.CardType type = ((AbstractTransportCard) item).getType();
        if (!worldIn.isClientSide){ vectorBias = worldIn.random.nextDouble(); }
        if (type == CardType.ARTEMIS_HOMING){
            LivingEntity target = ProjectileUtils.rayTraceTarget(playerIn, 0.1d, 200, 0.2);
            if (target == null){ return ActionResult.pass(itemStack); }
            for (int i = 0; i < 6; i++){
                ArtemisMissileEntity homingMissile = new ArtemisMissileEntity(worldIn, playerIn, target);
                homingMissile.moveTo(pos.x + vectorBias, pos.y, pos.z);
                worldIn.addFreshEntity(homingMissile);
            }
            if (!playerIn.isCreative()){ itemStack.shrink(1); }
            return ActionResult.success(itemStack);
        }
        else if (type == CardType.ARTEMIS_SCATTER){
            double p = 3;
            for (int i = 0; i < 6; i++){
                ArtemisMissileEntity missile = new ArtemisMissileEntity(worldIn, playerIn, look.x * p, look.y * p, look.z * p);
                missile.moveTo(pos.x + vectorBias, pos.y, pos.z);
                worldIn.addFreshEntity(missile);
            }
            if (!playerIn.isCreative()){ itemStack.shrink(1);}
            return ActionResult.success(itemStack);
        }
        return ActionResult.pass(itemStack);
    }


}

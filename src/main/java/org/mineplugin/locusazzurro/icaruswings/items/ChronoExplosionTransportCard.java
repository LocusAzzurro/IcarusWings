package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.magic.DamageTimeRift;

import java.util.List;

public class ChronoExplosionTransportCard extends AbstractTransportCard{

    private final static float RANGE = 3.0f;
    private final static float DAMAGE = 100.0f;

    public ChronoExplosionTransportCard() {
        super(CardType.CHRONO_EXPLOLSION);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        super.use(worldIn, playerIn, handIn);

        TimeBombEntity bomb = new TimeBombEntity(worldIn, playerIn, DAMAGE, RANGE, 60);
        worldIn.addFreshEntity(bomb);

        if(!playerIn.isCreative()) {itemstack.shrink(1);}

        return ActionResult.consume(itemstack);
    }
}

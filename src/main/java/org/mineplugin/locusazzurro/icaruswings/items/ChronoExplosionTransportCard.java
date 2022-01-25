package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

public class ChronoExplosionTransportCard extends AbstractTransportCard{

    private final static float RANGE = 3.0f;
    private final static float DAMAGE = 100.0f;

    public ChronoExplosionTransportCard() {
        super(CardType.CHRONO_EXPLOLSION);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return ActionResult.fail(itemstack);

        //playerIn.addEffect(new EffectInstance(EffectRegistry.inevitability.get(), 400, 0));
        ((EffectInevitability)EffectRegistry.inevitability.get()).addEffect(playerIn, 1);
        //todo change back to bomb

        TimeBombEntity bomb = new TimeBombEntity(worldIn, playerIn, DAMAGE, RANGE, 60);
        //worldIn.addFreshEntity(bomb);

        if(!playerIn.isCreative()) {itemstack.shrink(1);}

        return ActionResult.consume(itemstack);
    }

}

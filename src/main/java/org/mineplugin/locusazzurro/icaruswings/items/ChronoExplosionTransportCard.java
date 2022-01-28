package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.effect.EffectInevitability;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class ChronoExplosionTransportCard extends AbstractTransportCard{

    private final static float RANGE = 3.0f;
    private final static float DAMAGE = 100.0f;

    public ChronoExplosionTransportCard() {
        super(CardType.CHRONO_EXPLOLSION);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return ActionResult.fail(itemstack);

        TimeBombEntity bomb = new TimeBombEntity(worldIn, playerIn, DAMAGE, RANGE, 120);
        bomb.setPulsing();
        worldIn.addFreshEntity(bomb);
        worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationChrono.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(this, 80);

        return ActionResult.consume(itemstack);
    }

}

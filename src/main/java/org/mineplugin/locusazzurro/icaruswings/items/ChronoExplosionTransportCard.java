package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class ChronoExplosionTransportCard extends AbstractTransportCard{

    private final static float RANGE = 3.0f;
    private final static float DAMAGE = 100.0f;

    public ChronoExplosionTransportCard() {
        super(CardType.CHRONO_EXPLOLSION);
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return InteractionResultHolder.fail(itemstack);

        TimeBombEntity bomb = new TimeBombEntity(worldIn, playerIn, DAMAGE, RANGE, 120);
        bomb.setPulsing();
        worldIn.addFreshEntity(bomb);
        worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationChrono.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(this, 80);

        return InteractionResultHolder.consume(itemstack);
    }

}

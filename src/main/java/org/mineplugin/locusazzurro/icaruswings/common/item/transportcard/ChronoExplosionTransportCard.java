package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.common.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class ChronoExplosionTransportCard extends AbstractTransportCard{

    private final static float RANGE = 3.0f;
    private final static float DAMAGE = 100.0f;

    public ChronoExplosionTransportCard(Item.Properties properties) {
        super(CardType.CHRONO_EXPLOLSION, properties);
    }

    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return InteractionResult.FAIL;

        TimeBombEntity bomb = new TimeBombEntity(worldIn, playerIn, DAMAGE, RANGE, 120);
        bomb.setPulsing();
        worldIn.addFreshEntity(bomb);
        worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_ACTIVATION_CHRONO.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(itemstack, 80);

        return InteractionResult.CONSUME;
    }

}

package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.joml.Vector3d;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.MathUtils;

import java.util.List;


public class MaskingFieldTransportCard extends AbstractTransportCard{

    private static final List<Vector3d> PARTICLE_POINTS = MathUtils.cubeMatrixFrame(20);
    private static final float RANGE = 4;

    public MaskingFieldTransportCard(Item.Properties properties) {
        super(CardType.MASKING_FIELD, properties);
    }

    @Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {
            return InteractionResult.FAIL;
        }

        if (worldIn.isClientSide()) {
            PARTICLE_POINTS.forEach((point) -> {
                Vector3d rPoint = MathUtils.mulCopy(point, 0.4);
                worldIn.addParticle(ParticleRegistry.ELECTRONIC_BIT.get(),
                        playerIn.getX(), playerIn.getY() + 0.5, playerIn.getZ(),
                        rPoint.x(), rPoint.y(), rPoint.z());
            });
            for (int i = 0; i < 60; i++) {
                double xR = worldIn.getRandom().nextDouble() * 2 - 1;
                double yR = worldIn.getRandom().nextDouble() * 2 - 1;
                double zR = worldIn.getRandom().nextDouble() * 2 - 1;
                worldIn.addParticle(ParticleRegistry.ELECTRONIC_BIT.get(), playerIn.getX(), playerIn.getY() + 0.5, playerIn.getZ(), xR, yR, zR);
            }
        }

        worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_ACTIVATION_ELECTRONIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        float r = RANGE;
        AABB aabb = new AABB(r, r, r, -r, -r, -r).move(playerIn.position());
        List<Player> players = playerIn.level().getEntitiesOfClass(Player.class, aabb);
        for (Player player : players) {
            player.addEffect(new MobEffectInstance(EffectRegistry.SENSORY_MASKING, 2400, 0));
        }

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(itemstack, 200);
        return InteractionResult.CONSUME;
    }

}

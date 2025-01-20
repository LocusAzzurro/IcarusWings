package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.mineplugin.locusazzurro.icaruswings.common.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.ProjectileUtils;

public class ArtemisTransportCard extends AbstractTransportCard{

    private static final float HALF_PI = (float) Math.PI / 2;
    private static final float POWER = 2.1f;

    public ArtemisTransportCard(CardType type){
        super(type);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {
            return InteractionResultHolder.fail(itemStack);
        }
        Item item = itemStack.getItem();
        AbstractTransportCard.CardType type = ((AbstractTransportCard) item).getType();
        LivingEntity target = null;
        boolean homing = (type == CardType.ARTEMIS_HOMING);
        if (homing) {
            target = ProjectileUtils.rayTraceTarget(playerIn, ProjectileUtils.IS_HOSTILE, 0.5f, 500, 2);
            if (target == null) {
                if (!worldIn.isClientSide()) {
                    playerIn.sendSystemMessage(Component.translatable("item.locusazzurro_icaruswings.transport_card_artemis.error1"));
                }
                worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_FAIL.get(), SoundSource.PLAYERS, 1.0F, 0.5F);
                playerIn.getCooldowns().addCooldown(this, 10);
                return InteractionResultHolder.pass(itemStack);
            }
        }

        Vec3 pos = playerIn.getPosition(1f);
        Vec3 look = playerIn.getViewVector(1f);
        Vec3[] dPos = arrangePoints(pos, look);

        for (int i = 0; i < 6; i++){
            ArtemisMissileEntity missile = new ArtemisMissileEntity(worldIn, playerIn, homing ? target : null);
            missile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, homing ? 0.5f : POWER, 1.0f);
            missile.moveTo(dPos[i]);
            worldIn.addFreshEntity(missile);
            worldIn.playSound(null, missile, SoundRegistry.ARTEMIS_MISSILE_LAUNCH.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_ACTIVATION_ARTEMIS.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        if (!playerIn.isCreative()){ itemStack.shrink(1); }
        playerIn.getCooldowns().addCooldown(this, 40);
        return InteractionResultHolder.success(itemStack);
    }

    private Vec3[] arrangePoints(Vec3 pos, Vec3 look){
        int pointCount = 6;
        Vec3[] points = new Vec3[pointCount];
        float pitch = (float) Math.asin(-look.y());
        float yaw = (float) Mth.atan2(look.x(), look.z());
        for (int i = 0; i < pointCount; i++){
            points[i] = new Vec3(Mth.cos(Mth.TWO_PI / pointCount * i), 0, Mth.sin(Mth.TWO_PI / pointCount * i)).normalize()
                    .xRot(-pitch + Mth.HALF_PI).yRot(yaw).scale(2).add(pos).add(0, 1, 0);
        }
        return points;
    }

}

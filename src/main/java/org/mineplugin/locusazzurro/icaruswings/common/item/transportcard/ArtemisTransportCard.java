package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
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
            ArtemisMissileEntity missile;
            if (homing) {
                missile = new ArtemisMissileEntity(worldIn, playerIn, target);
                missile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 0.5f, 1.0f);
            }
            else {
                missile = new ArtemisMissileEntity(worldIn, playerIn);
                missile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, POWER, 3.0f);
            }
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
        return new Vec3[]{
                pos.add(look.yRot(HALF_PI)).add(0,0,0),
                pos.add(look.yRot(HALF_PI)).add(0,1,0),
                pos.add(look.yRot(HALF_PI)).add(0,2,0),
                pos.add(look.yRot(-HALF_PI)).add(0,0,0),
                pos.add(look.yRot(-HALF_PI)).add(0,1,0),
                pos.add(look.yRot(-HALF_PI)).add(0,2,0),
        };
    }

}

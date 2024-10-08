package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.joml.Vector3d;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.MathUtils;

import java.util.List;

public class InterdictionFieldTransportCard extends AbstractTransportCard{

    private static final List<Vector3d> PARTICLE_POINTS = MathUtils.fibonacciSphere(500);
    private static final List<Vector3d> LINE_ANCHORS = MathUtils.fibonacciSphere(50);
    private static final float RANGE = 10;

    public InterdictionFieldTransportCard() {
        super(CardType.INTERDICTION_FIELD);
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return InteractionResultHolder.fail(itemstack);

        if (worldIn.isClientSide()) {
            PARTICLE_POINTS.forEach((point) -> {
                Vector3d rPoint = MathUtils.mulCopy(point, 2.0d);
                Vector3d[] expand = {MathUtils.mulCopy(point, 0.3), MathUtils.mulCopy(point, 0.6), MathUtils.mulCopy(point, 0.9)};
                for (Vector3d vec : expand) {
                    worldIn.addParticle(ParticleTypes.END_ROD,
                            playerIn.getX() + rPoint.x(),
                            playerIn.getY() + rPoint.y(),
                            playerIn.getZ() + rPoint.z(),
                            vec.x(), vec.y(), vec.z());
                }
            });
            LINE_ANCHORS.forEach((point) -> {
                Vector3d rPoint = MathUtils.mulCopy(point, 2.0d);
                Vector3d[] expand = new Vector3d[10];
                for (int i = 0; i < expand.length; i++){
                    expand[i] = MathUtils.mulCopy(point, 0.1 * i);
                }
                for (Vector3d vec : expand) {
                    worldIn.addParticle(ParticleTypes.END_ROD,
                            playerIn.getX() + rPoint.x(),
                            playerIn.getY() + rPoint.y(),
                            playerIn.getZ() + rPoint.z(),
                            vec.x(), vec.y(), vec.z());
                }
            });
        }
        worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_ACTIVATION_ELECTRONIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

        float r = RANGE;
        AABB aabb = new AABB(r, r, r, -r, -r, -r).move(playerIn.position());
        List<LivingEntity> entities = playerIn.level().getEntitiesOfClass(LivingEntity.class, aabb);
        entities.remove(playerIn);
        for (LivingEntity entity : entities) {
            entity.addEffect(new MobEffectInstance(EffectRegistry.INTERDICTION, 1200, 0));
        }

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(this, 200);
        return InteractionResultHolder.consume(itemstack);
    }
}

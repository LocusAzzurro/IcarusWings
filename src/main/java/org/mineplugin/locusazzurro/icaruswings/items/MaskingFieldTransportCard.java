package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.MathUtils;

import java.util.List;

public class MaskingFieldTransportCard extends AbstractTransportCard{

    private static final List<Vector3d> PARTICLE_POINTS = MathUtils.cubeMatrixFrame(20);
    private static final float RANGE = 4;

    public MaskingFieldTransportCard() {
        super(CardType.MASKING_FIELD);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return ActionResult.fail(itemstack);

        if (worldIn.isClientSide()) {
            PARTICLE_POINTS.forEach((point) -> {
                Vector3f rPoint = new Vector3f(point.scale(0.4));
                worldIn.addParticle(ParticleRegistry.electronicBit.get(),
                        playerIn.getX(), playerIn.getY() + 0.5, playerIn.getZ(),
                        rPoint.x(), rPoint.y(), rPoint.z());
            });
            for (int i = 0; i < 60; i++) {
                double xR = worldIn.random.nextDouble() * 2 - 1;
                double yR = worldIn.random.nextDouble() * 2 - 1;
                double zR = worldIn.random.nextDouble() * 2 - 1;
                worldIn.addParticle(ParticleRegistry.electronicBit.get(), playerIn.getX(), playerIn.getY() + 0.5, playerIn.getZ(), xR, yR, zR);
            }
        }

        worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationElectronic.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        float r = RANGE;
        AxisAlignedBB aabb = new AxisAlignedBB(r, r, r, -r, -r, -r).move(playerIn.position());
        List<PlayerEntity> players = playerIn.level.getEntitiesOfClass(PlayerEntity.class, aabb);
        for (PlayerEntity player : players) {
            player.addEffect(new EffectInstance(EffectRegistry.sensoryMasking.get(), 2400, 0));
        }

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(this, 200);
        return ActionResult.consume(itemstack);
    }

}

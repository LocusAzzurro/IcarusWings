package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.damage.DamageTimeRift;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.MathUtils;

import java.util.List;

public class InterdictionFieldTransportCard extends AbstractTransportCard{

    private static final List<Vector3d> PARTICLE_POINTS = MathUtils.fibonacciSphere(500);
    private static final float RANGE = 10;

    public InterdictionFieldTransportCard() {
        super(CardType.INTERDICTION_FIELD);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return ActionResult.fail(itemstack);

        if (worldIn.isClientSide()) {
            PARTICLE_POINTS.forEach((point) -> {
                Vector3f rPoint = new Vector3f(point.scale(2));
                Vector3f[] expand = {
                        new Vector3f(point.scale(0.3)),
                        new Vector3f(point.scale(0.6)),
                        new Vector3f(point.scale(0.9)),};
                for (int i = 0; i < expand.length; i++) {
                    worldIn.addParticle(ParticleTypes.END_ROD,
                            playerIn.getX() + rPoint.x(),
                            playerIn.getY() + rPoint.y(),
                            playerIn.getZ() + rPoint.z(),
                            expand[i].x(), expand[i].y(), expand[i].z());
                }
            });
        }

        float r = RANGE;
        AxisAlignedBB aabb = new AxisAlignedBB(r, r, r, -r, -r, -r).move(playerIn.position());
        List<LivingEntity> entities = playerIn.level.getEntitiesOfClass(LivingEntity.class, aabb);
        entities.remove(playerIn);
        for (LivingEntity entity : entities) {
            entity.addEffect(new EffectInstance(EffectRegistry.interdiction.get(), 1200, 0));
        }

        if(!playerIn.isCreative()) {itemstack.shrink(1);}

        return ActionResult.consume(itemstack);
    }
}

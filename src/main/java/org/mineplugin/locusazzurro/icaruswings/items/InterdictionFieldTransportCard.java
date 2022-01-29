package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.Entity;
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
import org.mineplugin.locusazzurro.icaruswings.damage.DamageTimeRift;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.MathUtils;

import java.util.List;

public class InterdictionFieldTransportCard extends AbstractTransportCard{

    private static final List<Vector3d> PARTICLE_POINTS = MathUtils.fibonacciSphere(500);
    private static final List<Vector3d> LINE_ANCHORS = MathUtils.fibonacciSphere(50);
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
                Vector3f[] expand = {new Vector3f(point.scale(0.3)), new Vector3f(point.scale(0.6)), new Vector3f(point.scale(0.9))};
                for (Vector3f vec : expand) {
                    worldIn.addParticle(ParticleTypes.END_ROD,
                            playerIn.getX() + rPoint.x(),
                            playerIn.getY() + rPoint.y(),
                            playerIn.getZ() + rPoint.z(),
                            vec.x(), vec.y(), vec.z());
                }
            });
            LINE_ANCHORS.forEach((point) -> {
                Vector3f rPoint = new Vector3f(point.scale(2));
                Vector3f[] expand = new Vector3f[10];
                for (int i = 0; i < expand.length; i++){
                    expand[i] = new Vector3f(point.scale(0.1 * i));
                }
                for (Vector3f vec : expand) {
                    worldIn.addParticle(ParticleTypes.END_ROD,
                            playerIn.getX() + rPoint.x(),
                            playerIn.getY() + rPoint.y(),
                            playerIn.getZ() + rPoint.z(),
                            vec.x(), vec.y(), vec.z());
                }
            });
        }
        worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationElectronic.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        float r = RANGE;
        AxisAlignedBB aabb = new AxisAlignedBB(r, r, r, -r, -r, -r).move(playerIn.position());
        List<LivingEntity> entities = playerIn.level.getEntitiesOfClass(LivingEntity.class, aabb);
        entities.remove(playerIn);
        for (LivingEntity entity : entities) {
            entity.addEffect(new EffectInstance(EffectRegistry.interdiction.get(), 1200, 0));
        }

        if(!playerIn.isCreative()) {itemstack.shrink(1);}
        playerIn.getCooldowns().addCooldown(this, 200);
        return ActionResult.consume(itemstack);
    }
}

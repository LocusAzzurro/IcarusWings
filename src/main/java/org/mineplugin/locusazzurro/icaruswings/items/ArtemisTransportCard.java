package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.ProjectileUtils;

public class ArtemisTransportCard extends AbstractTransportCard{

    private static final float HALF_PI = (float) Math.PI / 2;
    private static final float POWER = 2.1f;

    public ArtemisTransportCard(boolean homing){
        super(CardType.ARTEMIS_HOMING);
    }

    public ArtemisTransportCard(){
        super(CardType.ARTEMIS_SCATTER);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return ActionResult.fail(itemStack);
        Item item = itemStack.getItem();
        AbstractTransportCard.CardType type = ((AbstractTransportCard) item).getType();
        LivingEntity target = null;
        boolean homing = (type == CardType.ARTEMIS_HOMING);
        if (homing) {
            target = ProjectileUtils.rayTraceTarget(playerIn, 0.1d, 300, 0.2);
            if (target == null) {
                worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundCategory.PLAYERS, 1.0F, 0.5F);
                playerIn.getCooldowns().addCooldown(this, 10);
                return ActionResult.pass(itemStack);
            }
        }

        Vector3d pos = playerIn.getPosition(1f);
        Vector3d look = playerIn.getViewVector(1f);
        Vector3d[] dPos = arrangePoints(pos, look);

        for (int i = 0; i < 6; i++){
            ArtemisMissileEntity missile;
            if (homing) {
                missile = new ArtemisMissileEntity(worldIn, playerIn, target);
                missile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 0.5f, 1.0f);
            }
            else {
                missile = new ArtemisMissileEntity(worldIn, playerIn);
                missile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, POWER, 3.0f);
            }
            missile.moveTo(dPos[i]);
            worldIn.addFreshEntity(missile);
            worldIn.playSound(null, missile, SoundRegistry.artemisMissileLaunch.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
        worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationArtemis.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (!playerIn.isCreative()){ itemStack.shrink(1); }
        playerIn.getCooldowns().addCooldown(this, 40);
        return ActionResult.success(itemStack);
    }

    private Vector3d[] arrangePoints(Vector3d pos, Vector3d look){
        return new Vector3d[]{
                pos.add(look.yRot(HALF_PI)).add(0,0,0),
                pos.add(look.yRot(HALF_PI)).add(0,1,0),
                pos.add(look.yRot(HALF_PI)).add(0,2,0),
                pos.add(look.yRot(-HALF_PI)).add(0,0,0),
                pos.add(look.yRot(-HALF_PI)).add(0,1,0),
                pos.add(look.yRot(-HALF_PI)).add(0,2,0),
        };
    }

}

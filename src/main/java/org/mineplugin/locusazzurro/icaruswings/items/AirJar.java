package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class AirJar extends Item {

    private AirType type;

    public AirJar(AirType type) {
        super(new Properties().tab(ModGroup.itemGroup).stacksTo(16));
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        AirType type = ((AirJar)itemStack.getItem()).getType();
        Vector3d mov = playerIn.getDeltaMovement();
        playerIn.stopFallFlying();
        switch (type) {
            case ZEPHIR:
                playerIn.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 0));
                break;
            case NETHER:
                playerIn.setDeltaMovement(mov.reverse());
                break;
            case VOID:
                playerIn.addEffect(new EffectInstance(Effects.LEVITATION, 20, 0));
                break;
        }
        if (!playerIn.abilities.instabuild){
            itemStack.shrink(1);
            ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegistry.glassJar.get()));
        }
        return ActionResult.consume(itemStack);
    }

    public AirType getType(){
        return this.type;
    }

    public enum AirType{
        ZEPHIR, NETHER, VOID;
    }

}

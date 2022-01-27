package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeRiftParticleEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public class TimeRiftGenerator extends Item{

    public TimeRiftGenerator(){
        super(new Item.Properties().tab(ModGroup.itemGroup).durability(400));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        TimeRiftParticleEntity particle = new TimeRiftParticleEntity(playerIn, worldIn);
        particle.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0f, 2.0f, 1.0f);
        particle.setNoGravity(true);
        worldIn.addFreshEntity(particle);
        return ActionResult.pass(itemStack);
    }
    //todo sound effects + particle

    @Override
    public boolean isValidRepairItem(ItemStack thisItem, ItemStack repairItem) {
        return repairItem.getItem() == ItemRegistry.synapseRepairKit.get() || super.isValidRepairItem(thisItem, repairItem);
    }
}

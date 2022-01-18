package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class TreasureTransportCard extends AbstractTransportCard{


    public TreasureTransportCard() {
        super(CardType.TREASURE);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {
            return ActionResult.fail(itemstack);
        } else if (!worldIn.isClientSide()){
            long lootSeed = worldIn.random.nextLong();
            LootContext.Builder contextBuilder = (new LootContext.Builder((ServerWorld)worldIn))
                    .withParameter(LootParameters.ORIGIN, playerIn.position())
                    .withOptionalRandomSeed(lootSeed);
            if (playerIn != null) {
                contextBuilder.withLuck(playerIn.getLuck()).withParameter(LootParameters.THIS_ENTITY, playerIn);
            }
            LootContext lootContext = contextBuilder.create(LootParameterSets.CHEST);
            LootTable lootTable = worldIn.getServer().getLootTables().get(LootTables.DESERT_PYRAMID);
            List<ItemStack> lootItems = lootTable.getRandomItems(lootContext);
            for (ItemStack item : lootItems){
                ItemHandlerHelper.giveItemToPlayer(playerIn, item);
            }
        }
        return ActionResult.consume(itemstack);
    }
}

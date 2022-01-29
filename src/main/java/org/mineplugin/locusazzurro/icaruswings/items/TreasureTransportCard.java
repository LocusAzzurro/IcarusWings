package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreasureTransportCard extends AbstractTransportCard{


    public TreasureTransportCard() {
        super(CardType.TREASURE);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return ActionResult.fail(itemstack);

        List<ItemStack> lootItems = new ArrayList<>();
        boolean readLootTableSuccess = false;
        byte errorCode = 0;

        if (!worldIn.isClientSide()){
            CompoundNBT nbt = itemstack.getOrCreateTag();
            if (nbt.contains("LootTable")){
                ResourceLocation lootTableResource = ResourceLocation.tryParse(nbt.getString("LootTable"));
                if (lootTableResource != null){
                    try {Objects.requireNonNull(worldIn.getServer());}
                    catch (NullPointerException e) {return ActionResult.fail(itemstack);}
                    LootTable lootTable = worldIn.getServer().getLootTables().get(lootTableResource);
                    long lootSeed = worldIn.random.nextLong();
                    LootContext lootContext = (new LootContext.Builder((ServerWorld)worldIn))
                            .withParameter(LootParameters.ORIGIN, playerIn.position())
                            .withOptionalRandomSeed(lootSeed).withLuck(playerIn.getLuck())
                            .withParameter(LootParameters.THIS_ENTITY, playerIn)
                            .create(LootParameterSets.CHEST);
                    lootItems.addAll(lootTable.getRandomItems(lootContext));
                    if (lootItems.isEmpty()) errorCode = 2;
                    else readLootTableSuccess = true;
                }
                else errorCode = 2; //invalid loot table
            }
            else errorCode = 1; //no loot table
        }

        if (readLootTableSuccess){
            worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationGeneric.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            if (!worldIn.isClientSide()) {
                if (playerIn.isCrouching()){
                    ((ServerWorld) worldIn).sendParticles(ParticleRegistry.goldenSparkle.get(), playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                            30, 0.5d, 0.5d, 0.5d, 0.0d);
                    for (ItemStack item : lootItems){
                        ItemHandlerHelper.giveItemToPlayer(playerIn, item);
                    }
                }
                else {
                    Vector3d[] dropPoints = MathUtils.randomPointsInCircle(lootItems.size(), 5, worldIn.random)
                            .toArray(new Vector3d[lootItems.size()]);
                    int pI = 0;
                    for (ItemStack item : lootItems){
                        double yR = worldIn.random.nextDouble() * 0.5 - 0.25;
                        double yP = playerIn.getY() + 3 + yR;
                        ItemEntity itemEntity = new ItemEntity(worldIn,
                                playerIn.getX() + dropPoints[pI].x, yP,
                                playerIn.getZ() + dropPoints[pI].z, item);
                        worldIn.addFreshEntity(itemEntity);
                        ((ServerWorld) worldIn).sendParticles(ParticleRegistry.goldenSparkle.get(),
                                playerIn.getX() + dropPoints[pI].x,
                                yP, playerIn.getZ() + dropPoints[pI].z,
                                3, 0.1d, 0.1d, 0.1d, 0.0d);
                        pI++;
                    }
                }
            }

            if (!playerIn.isCreative()){itemstack.shrink(1);}
            playerIn.getCooldowns().addCooldown(this, 20);
            return ActionResult.consume(itemstack);
        }
        else {
            worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            if (errorCode == 1) playerIn.sendMessage(new TranslationTextComponent("item.locusazzurro_icaruswings.transport_card_treasure.error1"), Util.NIL_UUID);
            if (errorCode == 2) playerIn.sendMessage(new TranslationTextComponent("item.locusazzurro_icaruswings.transport_card_treasure.error2"), Util.NIL_UUID);
            playerIn.getCooldowns().addCooldown(this, 20);
            return ActionResult.pass(itemstack);
        }
    }
}

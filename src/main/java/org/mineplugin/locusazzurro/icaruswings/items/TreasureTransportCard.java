package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
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
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) return InteractionResultHolder.fail(itemstack);

        List<ItemStack> lootItems = new ArrayList<>();
        boolean readLootTableSuccess = false;
        byte errorCode = 0;

        if (!worldIn.isClientSide()){
            CompoundTag nbt = itemstack.getOrCreateTag();
            if (nbt.contains("LootTable")){
                ResourceLocation lootTableResource = ResourceLocation.tryParse(nbt.getString("LootTable"));
                if (lootTableResource != null){
                    try {Objects.requireNonNull(worldIn.getServer());}
                    catch (NullPointerException e) {return InteractionResultHolder.fail(itemstack);}
                    LootTable lootTable = worldIn.getServer().getLootTables().get(lootTableResource);
                    long lootSeed = worldIn.random.nextLong();
                    LootContext lootContext = (new LootContext.Builder((ServerLevel)worldIn))
                            .withParameter(LootContextParams.ORIGIN, playerIn.position())
                            .withOptionalRandomSeed(lootSeed).withLuck(playerIn.getLuck())
                            .withParameter(LootContextParams.THIS_ENTITY, playerIn)
                            .create(LootContextParamSets.CHEST);
                    lootItems.addAll(lootTable.getRandomItems(lootContext));
                    if (lootItems.isEmpty()) {
                        errorCode = 2;
                    } else {
                        readLootTableSuccess = true;
                    }
                }
                else {
                    errorCode = 2; //invalid loot table
                }
            }
            else {
                errorCode = 1; //no loot table
            }
        }

        if (readLootTableSuccess){
            worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationGeneric.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!worldIn.isClientSide()) {
                if (playerIn.isCrouching()){
                    ((ServerLevel) worldIn).sendParticles(ParticleRegistry.goldenSparkleBase.get(), playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                            30, 0.5d, 0.5d, 0.5d, 0.0d);
                    for (ItemStack item : lootItems){
                        ItemHandlerHelper.giveItemToPlayer(playerIn, item);
                    }
                }
                else {
                    Vec3[] dropPoints = MathUtils.randomPointsInCircle(lootItems.size(), 5, worldIn.random)
                            .toArray(new Vec3[lootItems.size()]);
                    int pI = 0;
                    for (ItemStack item : lootItems){
                        double yR = worldIn.random.nextDouble() * 0.5 - 0.25;
                        double yP = playerIn.getY() + 3 + yR;
                        ItemEntity itemEntity = new ItemEntity(worldIn,
                                playerIn.getX() + dropPoints[pI].x, yP,
                                playerIn.getZ() + dropPoints[pI].z, item);
                        worldIn.addFreshEntity(itemEntity);
                        ((ServerLevel) worldIn).sendParticles(ParticleRegistry.goldenSparkleBase.get(),
                                playerIn.getX() + dropPoints[pI].x,
                                yP, playerIn.getZ() + dropPoints[pI].z,
                                3, 0.1d, 0.1d, 0.1d, 0.0d);
                        pI++;
                    }
                }
            }

            if (!playerIn.isCreative()){itemstack.shrink(1);}
            playerIn.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.consume(itemstack);
        }
        else {
            worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            if (errorCode == 1) {
                playerIn.sendMessage(new TranslatableComponent("item.locusazzurro_icaruswings.transport_card_treasure.error1"), Util.NIL_UUID);
            }
            if (errorCode == 2) {
                playerIn.sendMessage(new TranslatableComponent("item.locusazzurro_icaruswings.transport_card_treasure.error2"), Util.NIL_UUID);
            }
            playerIn.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.pass(itemstack);
        }
    }
}

package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            SeededContainerLoot containerLoot = itemstack.get(DataComponents.CONTAINER_LOOT);
            if (containerLoot != null){
                ResourceKey<LootTable> resourceKey = containerLoot.lootTable();
                ResourceLocation resourceLocation = resourceKey.location();
                try {
                    Objects.requireNonNull(worldIn.getServer());
                } catch (NullPointerException e) {
                    return InteractionResultHolder.fail(itemstack);
                }
                LootTable lootTable = worldIn.getServer().reloadableRegistries().getLootTable(resourceKey);
                long lootSeed = worldIn.random.nextLong();
                LootContext lootContext = new LootContext.Builder(new LootParams.Builder((ServerLevel) worldIn)
                        .withParameter(LootContextParams.ORIGIN, playerIn.position())
                        .withLuck(playerIn.getLuck())
                        .create(LootContextParamSets.CHEST))
                        .withOptionalRandomSeed(lootSeed)
                        .create(Optional.of(resourceLocation));
                lootTable.getRandomItems(lootContext, lootItems::add);
                if (lootItems.isEmpty()) {
                    errorCode = 2;
                } else {
                    readLootTableSuccess = true;
                }
            }
            else {
                errorCode = 1; //no loot table
            }
        }

        if (readLootTableSuccess){
            worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_ACTIVATION_GENERIC.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!worldIn.isClientSide()) {
                if (playerIn.isCrouching()){
                    ((ServerLevel) worldIn).sendParticles(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), playerIn.getX(), playerIn.getY(), playerIn.getZ(),
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
                        ((ServerLevel) worldIn).sendParticles(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(),
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
            worldIn.playSound(null, playerIn, SoundRegistry.TRANSPORT_CARD_FAIL.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            if (errorCode == 1) {
                playerIn.sendSystemMessage(Component.translatable("item.locusazzurro_icaruswings.transport_card_treasure.error1"));
            }
            if (errorCode == 2) {
                playerIn.sendSystemMessage(Component.translatable("item.locusazzurro_icaruswings.transport_card_treasure.error2"));
            }
            playerIn.getCooldowns().addCooldown(this, 20);
            return InteractionResultHolder.pass(itemstack);
        }
    }


}

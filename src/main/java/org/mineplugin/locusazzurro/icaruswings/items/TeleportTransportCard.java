package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.MathUtils;

import java.util.List;

public class TeleportTransportCard extends AbstractTransportCard{

    public TeleportTransportCard() {
        super(CardType.TELEPORT);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 40;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {
            return InteractionResultHolder.fail(itemstack);
        }
        CompoundTag nbt = itemstack.getOrCreateTag();
        if (!nbt.contains("Destination")){
            if (playerIn.isCrouching()) {
                CompoundTag dest = new CompoundTag();
                this.saveDestination(dest, playerIn, worldIn);
                nbt.put("Destination", dest);

                if (worldIn.isClientSide()){
                    List<Vec3> points = MathUtils.squareMatrixFrame(5);
                    for (Vec3 point : points){
                        for (int i = 1; i <= 4; i++){
                            worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                                    playerIn.getX() + point.x, playerIn.getY(), playerIn.getZ() + point.z,
                                    0, 0.03 * i, 0);
                        }
                    }
                }
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundRegistry.transportCardTeleportAnchor.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                playerIn.getCooldowns().addCooldown(this, 40);
                return InteractionResultHolder.consume(itemstack);
            }
            else {
                worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!worldIn.isClientSide())
                playerIn.sendMessage(new TranslatableComponent("item.locusazzurro_icaruswings.transport_card_teleport.error1"), Util.NIL_UUID);
                playerIn.getCooldowns().addCooldown(this, 20);
                return InteractionResultHolder.pass(itemstack);
            }
        }
        else {
            playerIn.startUsingItem(handIn);
            worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationChrono.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void onUseTick(Level worldIn, LivingEntity livingIn, ItemStack itemStack, int useTicks) {
        List<Vec3> points = MathUtils.circlePoints(10);
        if (useTicks % 3 == 0 && worldIn.isClientSide()) {
            for (Vec3 point : points) {
                worldIn.addParticle(ParticleTypes.FALLING_OBSIDIAN_TEAR, livingIn.getX() + point.x, livingIn.getY() + 2, livingIn.getZ() + point.z, 0, -0.1, 0);
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level worldIn, LivingEntity entityIn) {
        if (!worldIn.isClientSide() && entityIn instanceof ServerPlayer){
            ServerPlayer playerIn = (ServerPlayer) entityIn;
            if (playerIn.connection.getConnection().isConnected() && !playerIn.isSleeping()) {
                CompoundTag dest = itemStack.getOrCreateTag().getCompound("Destination");
                String dim = dest.getString("Dimension");
                ResourceKey<Level> dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(dim));
                if (worldIn.dimension().equals(dimension)) {
                    double x = dest.getDouble("X");
                    double y = dest.getDouble("Y");
                    double z = dest.getDouble("Z");

                    if (playerIn.isPassenger()) playerIn.stopRiding();
                    playerIn.fallDistance = 0.0F;
                    ((ServerLevel)worldIn).sendParticles(ParticleTypes.PORTAL, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                            20, 1d, 0.5d, 1d, 0.0d);
                    worldIn.playSound(null, playerIn, SoundRegistry.transportCardTeleport.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                    playerIn.teleportTo(x, y, z);

                    worldIn.playSound(null, playerIn, SoundRegistry.transportCardTeleport.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    ((ServerLevel)worldIn).sendParticles(ParticleTypes.PORTAL, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                            20, 1d, 0.5d, 1d, 0.0d);

                    playerIn.getCooldowns().addCooldown(this, 40);
                    if (!playerIn.isCreative()) {
                        itemStack.shrink(1);
                        if (itemStack.isEmpty()) itemStack = new ItemStack(Items.AIR);
                    }
                    return itemStack;
                }
                else {
                    worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    playerIn.sendMessage(new TranslatableComponent("item.locusazzurro_icaruswings.transport_card_teleport.error2"), Util.NIL_UUID);
                    playerIn.getCooldowns().addCooldown(this, 20);
                    return itemStack;
                }
            }
            else {
                worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                playerIn.sendMessage(new TranslatableComponent("item.locusazzurro_icaruswings.transport_card_teleport.error3"), Util.NIL_UUID);
                playerIn.getCooldowns().addCooldown(this, 20);
                return itemStack;
            }
        }

        return itemStack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.BOW;
    }

    private CompoundTag saveDestination(CompoundTag nbt, Player playerIn, Level worldIn){
        nbt.putDouble("X", playerIn.getX());
        nbt.putDouble("Y", playerIn.getY());
        nbt.putDouble("Z", playerIn.getZ());
        nbt.putString("Dimension", worldIn.dimension().location().getPath());
        return nbt;
    }

}

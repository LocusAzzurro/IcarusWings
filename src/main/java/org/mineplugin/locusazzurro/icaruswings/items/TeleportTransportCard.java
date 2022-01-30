package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {
            return ActionResult.fail(itemstack);
        }
        CompoundNBT nbt = itemstack.getOrCreateTag();
        if (!nbt.contains("Destination")){
            if (playerIn.isCrouching()) {
                CompoundNBT dest = new CompoundNBT();
                this.saveDestination(dest, playerIn, worldIn);
                nbt.put("Destination", dest);

                if (worldIn.isClientSide()){
                    List<Vector3d> points = MathUtils.squareMatrixFrame(5);
                    for (Vector3d point : points){
                        for (int i = 1; i <= 4; i++){
                            worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                                    playerIn.getX() + point.x, playerIn.getY(), playerIn.getZ() + point.z,
                                    0, 0.03 * i, 0);
                        }
                    }
                }
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundRegistry.transportCardTeleportAnchor.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                playerIn.getCooldowns().addCooldown(this, 40);
                return ActionResult.consume(itemstack);
            }
            else {
                worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!worldIn.isClientSide())
                playerIn.sendMessage(new TranslationTextComponent("item.locusazzurro_icaruswings.transport_card_teleport.error1"), Util.NIL_UUID);
                playerIn.getCooldowns().addCooldown(this, 20);
                return ActionResult.pass(itemstack);
            }
        }
        else {
            playerIn.startUsingItem(handIn);
            worldIn.playSound(null, playerIn, SoundRegistry.transportCardActivationChrono.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
        return ActionResult.consume(itemstack);
    }

    @Override
    public void onUseTick(World worldIn, LivingEntity livingIn, ItemStack itemStack, int useTicks) {
        List<Vector3d> points = MathUtils.circlePoints(10);
        if (useTicks % 3 == 0 && worldIn.isClientSide()) {
            for (Vector3d point : points) {
                worldIn.addParticle(ParticleTypes.FALLING_OBSIDIAN_TEAR, livingIn.getX() + point.x, livingIn.getY() + 2, livingIn.getZ() + point.z, 0, -0.1, 0);
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World worldIn, LivingEntity entityIn) {
        if (!worldIn.isClientSide() && entityIn instanceof ServerPlayerEntity){
            ServerPlayerEntity playerIn = (ServerPlayerEntity) entityIn;
            if (playerIn.connection.getConnection().isConnected() && !playerIn.isSleeping()) {
                CompoundNBT dest = itemStack.getOrCreateTag().getCompound("Destination");
                String dim = dest.getString("Dimension");
                RegistryKey<World> dimension = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(dim));
                if (worldIn.dimension().equals(dimension)) {
                    double x = dest.getDouble("X");
                    double y = dest.getDouble("Y");
                    double z = dest.getDouble("Z");

                    if (playerIn.isPassenger()) playerIn.stopRiding();
                    playerIn.fallDistance = 0.0F;
                    ((ServerWorld)worldIn).sendParticles(ParticleTypes.PORTAL, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                            20, 1d, 0.5d, 1d, 0.0d);
                    worldIn.playSound(null, playerIn, SoundRegistry.transportCardTeleport.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

                    playerIn.teleportTo(x, y, z);

                    worldIn.playSound(null, playerIn, SoundRegistry.transportCardTeleport.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                    ((ServerWorld)worldIn).sendParticles(ParticleTypes.PORTAL, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                            20, 1d, 0.5d, 1d, 0.0d);

                    playerIn.getCooldowns().addCooldown(this, 40);
                    if (!playerIn.isCreative()) {
                        itemStack.shrink(1);
                        if (itemStack.isEmpty()) itemStack = new ItemStack(Items.AIR);
                    }
                    return itemStack;
                }
                else {
                    worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                    playerIn.sendMessage(new TranslationTextComponent("item.locusazzurro_icaruswings.transport_card_teleport.error2"), Util.NIL_UUID);
                    playerIn.getCooldowns().addCooldown(this, 20);
                    return itemStack;
                }
            }
            else {
                worldIn.playSound(null, playerIn, SoundRegistry.transportCardFail.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                playerIn.sendMessage(new TranslationTextComponent("item.locusazzurro_icaruswings.transport_card_teleport.error3"), Util.NIL_UUID);
                playerIn.getCooldowns().addCooldown(this, 20);
                return itemStack;
            }
        }

        return itemStack;
    }

    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.BOW;
    }

    private CompoundNBT saveDestination(CompoundNBT nbt, PlayerEntity playerIn, World worldIn){
        nbt.putDouble("X", playerIn.getX());
        nbt.putDouble("Y", playerIn.getY());
        nbt.putDouble("Z", playerIn.getZ());
        nbt.putString("Dimension", worldIn.dimension().location().getPath());
        return nbt;
    }

}

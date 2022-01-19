package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TeleportTransportCard extends AbstractTransportCard{

    public TeleportTransportCard() {
        super(CardType.TELEPORT);
    }

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
                return ActionResult.consume(itemstack);
            }
            else return ActionResult.pass(itemstack);
        }
        else {
            playerIn.startUsingItem(handIn);
        }
        return ActionResult.consume(itemstack);
    }

    public void onUseTick(World p_219972_1_, LivingEntity p_219972_2_, ItemStack p_219972_3_, int p_219972_4_) {
        //todo add charging effect here
    }

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
                    if (!playerIn.isCreative()) itemStack.shrink(1);
                    if (playerIn.isPassenger()) playerIn.stopRiding();
                    playerIn.fallDistance = 0.0F;
                    playerIn.teleportTo(x, y, z);
                }
            }
        }
        return itemStack;
    }

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

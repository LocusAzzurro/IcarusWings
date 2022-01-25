package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.BiConsumer;

/**
 * For glass jar filling mechanic:
 * @see org.mineplugin.locusazzurro.icaruswings.items.GlassJar#collectAir
 */

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
        playerIn.stopFallFlying();
        effectProcessor.accept(playerIn, type);
        if (worldIn.isClientSide()) {
            IParticleData particle = new RedstoneParticleData(type.r, type.g, type.b, 1.0f);
            for(int i = 0; i < 20; i++){
                double xR = worldIn.random.nextDouble() * 0.5 - 0.25;
                double yR = worldIn.random.nextDouble() * 0.2 - 0.1;
                double zR = worldIn.random.nextDouble() * 0.5 - 0.25;
                worldIn.addParticle(particle, playerIn.getX() + xR, playerIn.getY() + yR, playerIn.getZ() + zR,
                        1, 1, 1);
            }
        }
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundRegistry.airJarEmpty.get(), SoundCategory.NEUTRAL, 0.5f, 0.6f);
        if (!playerIn.abilities.instabuild){
            itemStack.shrink(1);
            ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegistry.glassJar.get()));
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.consume(itemStack);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(ItemRegistry.glassJar.get());
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    public AirType getType(){
        return this.type;
    }

    public enum AirType{
        ZEPHIR(0xabd1f1),
        NETHER(0xf8a84c),
        VOID(0x58b3a2),;

        private final float r,g,b;

        AirType(int color){
            this.r = ((color & 0xff0000) >> 16) / 255f;
            this.g = ((color & 0x00ff00) >> 8) / 255f;
            this.b = (color & 0x0000ff) / 255f;
        }
    }

    private final BiConsumer<PlayerEntity, AirType> effectProcessor = (playerEntity, airType) -> {
        switch (airType) {
            case ZEPHIR:
                playerEntity.addEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 0));
                break;
            case NETHER:
                playerEntity.setDeltaMovement(playerEntity.getDeltaMovement().reverse());
                break;
            case VOID:
                playerEntity.addEffect(new EffectInstance(Effects.LEVITATION, 20, 0));
                break;
        }
    };



}

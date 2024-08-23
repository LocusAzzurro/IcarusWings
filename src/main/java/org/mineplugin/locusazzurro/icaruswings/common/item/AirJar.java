package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.joml.Vector3f;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.BiConsumer;

/**
 * For glass jar filling mechanic:
 * @see GlassJar#collectAir
 */

public class AirJar extends Item {

    private AirType type;

    public AirJar(AirType type) {
        super(new Properties().stacksTo(16));
        this.type = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        AirType type = ((AirJar)itemStack.getItem()).getType();
        playerIn.stopFallFlying();
        effectProcessor.accept(playerIn, type);
        if (worldIn.isClientSide()) {
            ParticleOptions particle = new DustParticleOptions(new Vector3f(type.r, type.g, type.b), 1.0f);
            for(int i = 0; i < 20; i++){
                double xR = worldIn.random.nextDouble() * 0.5 - 0.25;
                double yR = worldIn.random.nextDouble() * 0.2 - 0.1;
                double zR = worldIn.random.nextDouble() * 0.5 - 0.25;
                worldIn.addParticle(particle, playerIn.getX() + xR, playerIn.getY() + yR, playerIn.getZ() + zR,
                        1, 1, 1);
            }
        }
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundRegistry.AIR_JAR_EMPTY.get(), SoundSource.NEUTRAL, 0.5f, 0.6f);
        if (!playerIn.getAbilities().instabuild){
            itemStack.shrink(1);
            ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemRegistry.GLASS_JAR.get()));
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(ItemRegistry.GLASS_JAR.get());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
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

    private final BiConsumer<Player, AirType> effectProcessor = (playerEntity, airType) -> {
        switch (airType) {
            case ZEPHIR -> playerEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0));
            case NETHER -> playerEntity.setDeltaMovement(playerEntity.getDeltaMovement().reverse());
            case VOID -> playerEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 0));
        }
    };
}

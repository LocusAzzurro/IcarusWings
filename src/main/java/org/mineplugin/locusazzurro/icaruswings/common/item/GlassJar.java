package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Supplier;

@EventBusSubscriber
public class GlassJar extends Item {

    public GlassJar(){
        super(new Item.Properties().stacksTo(16));
    }

    @SubscribeEvent
    public static void collectAir(PlayerTickEvent.Pre e){
        Player player = e.getEntity();
        Level world = player.level();
        if (player.isFallFlying() && player.tickCount % 20 == 0 && !world.isClientSide()){
            int slot = player.getInventory().findSlotMatchingUnusedItem(new ItemStack(ItemRegistry.GLASS_JAR.get()));
            if (slot != -1){
                float rand = world.random.nextFloat();
                int altitude = (int)Math.round(Mth.clamp(player.getY(), 0, 255));
                float speedFactor = (float) Mth.clamp(player.getDeltaMovement().length() / 2.0, 1.0, 1.25);
                int dim = getDimNum(world.dimension());
                float chance = 0.0f;
                ItemStack targetStack = player.getInventory().getItem(slot);
                if (dim != -1){
                    int distance = Mth.clamp(Mth.abs(OPTIMALS[dim] - altitude), 0, 127);
                    chance = CT[dim][distance] * speedFactor;
                }
                if (rand <= chance) {
                    targetStack.shrink(1);
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(getResultItem(dim).get()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.AIR_JAR_FILL.get(), SoundSource.NEUTRAL, 0.9f, 0.8f);
                }
            }
        }
    }

    private static final float ZEPHIR_BASE_CHANCE = 0.05f;
    private static final float NETHER_BASE_CHANCE = 0.04f;
    private static final float VOID_BASE_CHANCE = 0.03f;
    private static final int[] OPTIMALS = {255, 63, 0};

    private static int getDimNum(ResourceKey<Level> world){
        if (world == Level.OVERWORLD) return 0;
        else if (world == Level.NETHER) return 1;
        else if (world == Level.END) return 2;
        else return -1;
    }

    private static Supplier<? extends Item> getResultItem(int dimN){
        if (dimN == 0) return ItemRegistry.ZEPHIR_AIR_JAR;
        else if (dimN == 1) return ItemRegistry.NETHER_AIR_JAR;
        else if (dimN == 2) return ItemRegistry.VOID_AIR_JAR;
        else return ItemRegistry.GLASS_JAR;
    }

    private static final float[][] CT = Util.make(new float[3][128], (t) -> {
        for(int i = 0; i < 128; i++){
           t[0][i] = ZEPHIR_BASE_CHANCE * F(i);
           t[1][i] = NETHER_BASE_CHANCE * F(i);
           t[2][i] = VOID_BASE_CHANCE * F(i);
        }
    });

    private static float F(int x){
        return (float) Mth.clamp(Math.log(-x + 128)/4.8f, 0.1f, 1.0f);
    }

}



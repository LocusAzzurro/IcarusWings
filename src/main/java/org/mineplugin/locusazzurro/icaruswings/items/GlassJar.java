package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

@Mod.EventBusSubscriber
public class GlassJar extends Item {

    public GlassJar(){
        super(new Item.Properties().stacksTo(16));
    }

    @SubscribeEvent
    public static void collectAir(TickEvent.PlayerTickEvent e){
        Player player = e.player;
        Level world = player.level;
        if (player.isFallFlying() && player.tickCount % 20 == 0 && !world.isClientSide() && e.phase == TickEvent.Phase.END){
            int slot = player.getInventory().findSlotMatchingUnusedItem(new ItemStack(ItemRegistry.glassJar.get()));
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
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.airJarFill.get(), SoundSource.NEUTRAL, 0.9f, 0.8f);
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

    private static RegistryObject<Item> getResultItem(int dimN){
        if (dimN == 0) return ItemRegistry.zephirAirJar;
        else if (dimN == 1) return ItemRegistry.netherAirJar;
        else if (dimN == 2) return ItemRegistry.voidAirJar;
        else return ItemRegistry.glassJar;
    }

    private static float[][] CT = Util.make(new float[3][128], (t) -> {
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



package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

@Mod.EventBusSubscriber
public class GlassJar extends Item{

    public GlassJar(){
        super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(16));
    }

    @SubscribeEvent
    public static void collectAir(TickEvent.PlayerTickEvent e){
        PlayerEntity player = e.player;
        World world = player.level;
        if (player.isFallFlying() && player.tickCount % 20 == 0 && !world.isClientSide() && e.phase == TickEvent.Phase.END){
            int slot = player.inventory.findSlotMatchingItem(new ItemStack(ItemRegistry.glassJar.get()));
            if (slot != -1){
                float rand = world.random.nextFloat();
                int altitude = (int)Math.round(MathHelper.clamp(player.getY(), 0, 255));
                float speedFactor = (float) MathHelper.clamp(player.getDeltaMovement().length() / 2.0, 1.0, 1.25);
                int dim = getDimNum(world.dimension());
                float chance = 0.0f;
                ItemStack targetStack = player.inventory.getItem(slot);
                if (dim != -1){
                    int distance = MathHelper.clamp(MathHelper.abs(OPTIMALS[dim] - altitude), 0, 127);
                    chance = CT[dim][distance] * speedFactor;
                }
                if (rand <= chance) {
                    targetStack.shrink(1);
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(getResultItem(dim).get()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.airJarFill.get(), SoundCategory.NEUTRAL, 0.9f, 0.8f);
                }
            }
        }
    }

    private static final float ZEPHIR_BASE_CHANCE = 0.05f;
    private static final float NETHER_BASE_CHANCE = 0.04f;
    private static final float VOID_BASE_CHANCE = 0.03f;
    private static final int[] OPTIMALS = {255, 63, 0};

    private static int getDimNum(RegistryKey<World> world){
        if (world == World.OVERWORLD) return 0;
        else if (world == World.NETHER) return 1;
        else if (world == World.END) return 2;
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
        return (float)MathHelper.clamp(Math.log(-x + 128)/4.8f, 0.1f, 1.0f);
    }

}



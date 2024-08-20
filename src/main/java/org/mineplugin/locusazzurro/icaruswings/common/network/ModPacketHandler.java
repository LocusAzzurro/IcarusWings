package org.mineplugin.locusazzurro.icaruswings.common.network;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.network.NetworkRegistry;
import net.neoforged.network.simple.SimpleChannel;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

public class ModPacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ModData.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {

        int index = 0;
        INSTANCE.registerMessage(index++, SparklePacket.class, SparklePacket::encode, SparklePacket::new, SparklePacket::handle);
    }

}

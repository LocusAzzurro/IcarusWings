package org.mineplugin.locusazzurro.icaruswings.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

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
        INSTANCE.registerMessage(index++, FlagPacket.class, FlagPacket::encode, FlagPacket::new, FlagPacket::handle);
    }

}

package org.mineplugin.locusazzurro.icaruswings.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class FlagPacket {

    private final boolean data;

    FlagPacket(PacketBuffer buf) {
        this.data = buf.readBoolean();
    }

    FlagPacket(boolean data) {
        this.data = data;
    }

    void encode(PacketBuffer buf) {
        buf.writeBoolean(data);
    }

    static void handle(FlagPacket message, Supplier<NetworkEvent.Context> ctx) {
    }


}

package org.mineplugin.locusazzurro.icaruswings.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

public record ClientBoundSparklePacket(double x, double y, double z) implements CustomPacketPayload {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "sparkle");

    public static ClientBoundSparklePacket create(FriendlyByteBuf buf) {
        return new ClientBoundSparklePacket(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    @Override
    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeDouble(x);
        pBuffer.writeDouble(y);
        pBuffer.writeDouble(z);
    }

    public void handle(PlayPayloadContext ctx) {
        ctx.workHandler().submitAsync(() -> {
            if (ctx.level().isEmpty()) return;
            Level world = ctx.level().get();
            if (ctx.flow() == PacketFlow.CLIENTBOUND) {
                world.addParticle(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), x, y, z, 0, 0, 0);
            }
        });
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}

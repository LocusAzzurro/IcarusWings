package org.mineplugin.locusazzurro.icaruswings.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.DistExecutor;
import net.neoforged.network.NetworkEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

import java.util.function.Supplier;

public class SparklePacket {

    private final double x;
    private final double y;
    private final double z;

    SparklePacket(FriendlyByteBuf buf) {
        this.z = buf.readDouble();
        this.y = buf.readDouble();
        this.x = buf.readDouble();
    }

    SparklePacket(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    void encode(FriendlyByteBuf buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world = ctx.get().getSender().level();
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                world.addParticle(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), x, y, z, 0, 0, 0);
            });
        });
    }


}

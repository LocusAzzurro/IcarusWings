package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;
public class NullityParticle extends NoRenderParticle {

    public NullityParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        this.lifetime = 1;
    }

    @Override
    public void tick() {
        this.remove();
    }
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        public Factory(SpriteSet sprites) {
        }

        @Override
        public @Nullable Particle createParticle(
            SimpleParticleType options,
            ClientLevel level,
            double x,
            double y,
            double z,
            double xSpeed,
            double ySpeed,
            double zSpeed,
            RandomSource random
        ) {
            return new NullityParticle(level, x, y, z);
        }
    }
}


package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;
public class PlasmaTrailParticle extends SimpleAnimatedParticle {

    public PlasmaTrailParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        this.setColor(1f, 1f, 1f);
        this.scale(0.9f);
        this.xd = xSpeed + (level.getRandom().nextDouble() * 2.0d - 1.0d) * 0.005d;
        this.yd = ySpeed + (level.getRandom().nextDouble() * 2.0d - 1.0d) * 0.005d;
        this.zd = zSpeed + (level.getRandom().nextDouble() * 2.0d - 1.0d) * 0.005d;
        this.lifetime = (int) Math.min(50f, (10 / Math.max(0.1d, level.getRandom().nextDouble()) * 1.5f));
        this.hasPhysics = true;
        this.setSpriteFromAge(sprites);
    }
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
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
            return new PlasmaTrailParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}


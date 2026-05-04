package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;
public class TimeRiftExplosionParticle extends SimpleAnimatedParticle {

    public TimeRiftExplosionParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        this.setColor(1f, 1f, 1f);
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.lifetime = 20;
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.xd *= 0.95d;
        this.yd *= 0.95d;
        this.zd *= 0.95d;
        this.scale(1.3f);
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
            return new TimeRiftExplosionParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}


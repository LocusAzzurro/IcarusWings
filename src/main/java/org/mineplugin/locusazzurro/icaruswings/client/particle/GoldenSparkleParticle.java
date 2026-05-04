package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;
public class GoldenSparkleParticle extends SimpleAnimatedParticle {

    public GoldenSparkleParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        this.setColor(1f, 1f, 1f);
        this.scale(level.getRandom().nextFloat() / 0.8f);
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
        this.xd *= 0.8d;
        this.yd *= 0.8d;
        this.zd *= 0.8d;
    }
    public static class BaseFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public BaseFactory(SpriteSet sprites) {
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
            return new GoldenSparkleParticle(
                level,
                x,
                y,
                z,
                level.getRandom().nextDouble() - 0.5,
                level.getRandom().nextDouble() - 0.5,
                level.getRandom().nextDouble() - 0.5,
                this.sprites
            );
        }
    }
    public static class AdvFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public AdvFactory(SpriteSet sprites) {
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
            return new GoldenSparkleParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}


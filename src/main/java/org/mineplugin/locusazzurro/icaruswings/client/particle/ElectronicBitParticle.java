package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;
public class ElectronicBitParticle extends SimpleAnimatedParticle {
    private final int phase;

    public ElectronicBitParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        float rS = level.getRandom().nextFloat() * 0.05f;
        float gS = level.getRandom().nextFloat() * 0.05f;
        float bS = level.getRandom().nextFloat() * 0.05f;
        this.setColor(1f - rS, 1f - gS, 1f - bS);
        this.scale(level.getRandom().nextFloat() * 2);
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.phase = level.getRandom().nextInt(4);
        this.lifetime = (int) (40 - level.getRandom().nextFloat() * 5);
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.xd *= 0.9d;
        this.yd *= 0.9d;
        this.zd *= 0.9d;
        this.setAlpha((float) (((this.age + this.phase) % 5) * 0.1 + 0.5));
        this.scale(1.01f);
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
            return new ElectronicBitParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}


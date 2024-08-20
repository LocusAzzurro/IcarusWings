package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ElectronicBitParticle extends TextureSheetParticle {

    private int phase;

    public ElectronicBitParticle(ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        float rS = world.random.nextFloat() * 0.05f;
        float gS = world.random.nextFloat() * 0.05f;
        float bS = world.random.nextFloat() * 0.05f;
        this.setColor(1f - rS,1f - gS,1f - bS);
        this.setSize(0.05f, 0.05f);
        this.scale(world.random.nextFloat() * 2);
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.phase = world.random.nextInt(4);
        this.lifetime = (int) (40 - world.random.nextFloat() * 5);
        this.hasPhysics = false;
    }

    @Override
    public void tick(){
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) this.remove();
        else {
            this.move(xd, yd, zd);
            this.xd *= 0.9d;
            this.yd *= 0.9d;
            this.zd *= 0.9d;
            this.setAlpha((float) (((age + phase) % 5) * 0.1 + 0.5));
            this.scale(1.01f);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Factory(SpriteSet sprite) {
            this.sprites = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType dataIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ElectronicBitParticle particle = new ElectronicBitParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setColor(1f,1f,1f);
            particle.pickSprite(sprites);
            return particle;
        }
    }
}

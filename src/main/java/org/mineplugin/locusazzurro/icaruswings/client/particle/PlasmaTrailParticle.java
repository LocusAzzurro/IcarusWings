package org.mineplugin.locusazzurro.icaruswings.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class PlasmaTrailParticle extends TextureSheetParticle {

    public PlasmaTrailParticle(ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setColor(1f,1f,1f);
        this.setSize(0.03f, 0.03f);
        this.scale(0.9f);
        this.xd = xSpeed + (Math.random() * 2.0d - 1.0d) * 0.005d;
        this.yd = ySpeed + (Math.random() * 2.0d - 1.0d) * 0.005d;
        this.zd = zSpeed + (Math.random() * 2.0d - 1.0d) * 0.005d;
        this.lifetime = (int)Math.min(50f, (10 / Math.random() * 1.5f));
        this.hasPhysics = true;
    }

    @Override
    public void tick(){
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) this.remove();
        else {
            this.move(xd, yd, zd);
            this.xd *= 1.0d;
            this.yd *= 1.0d;
            this.zd *= 1.0d;
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
            PlasmaTrailParticle particle = new PlasmaTrailParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setColor(1f,1f,1f);
            particle.pickSprite(sprites);
            return particle;
        }
    }
}

package org.mineplugin.locusazzurro.icaruswings.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class PlasmaTrailParticle extends SpriteTexturedParticle {

    protected PlasmaTrailParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setColor(1f,1f,1f);
        this.setSize(0.03f, 0.03f);
        this.scale(0.9f);
        this.xd = xSpeed + (Math.random() * 2.0d - 1.0d) * 0.005d;
        this.yd = ySpeed + (Math.random() * 2.0d - 1.0d) * 0.005d;
        this.zd = zSpeed + (Math.random() * 2.0d - 1.0d) * 0.005d;
        this.lifetime = (int)(10 / Math.random() * 1.5f);
        this.hasPhysics = true;
    }

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
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {

        private final IAnimatedSprite sprites;

        public Factory(IAnimatedSprite sprite) {
            this.sprites = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(BasicParticleType dataIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            PlasmaTrailParticle particle = new PlasmaTrailParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setColor(1f,1f,1f);
            particle.pickSprite(sprites);
            return particle;
        }
    }
}

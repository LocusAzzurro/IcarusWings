package org.mineplugin.locusazzurro.icaruswings.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/*
    Particle used to get around classes/methods that require a particle without making a complicated workaround
 */
public class NullityParticle extends SpriteTexturedParticle {

    public NullityParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setSize(0.00f, 0.00f);
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;
        this.lifetime = 0;
        this.hasPhysics = false;
    }

    @Override
    public void tick(){
        this.remove();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.NO_RENDER;
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
            NullityParticle particle = new NullityParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(sprites);
            return particle;
        }
    }
}

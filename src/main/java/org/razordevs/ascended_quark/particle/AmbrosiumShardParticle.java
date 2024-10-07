package org.razordevs.ascended_quark.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 *  [VANILLA COPY] of net.minecraft.client.particle.FlameParticle
 */
@OnlyIn(Dist.CLIENT)
public class AmbrosiumShardParticle extends RisingParticle {
    AmbrosiumShardParticle(ClientLevel level, double v, double v1, double v2, double v3, double v4, double v5) {
        super(level, v, v1, v2, v3, v4, v5);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double v, double v1, double v2) {
        this.setBoundingBox(this.getBoundingBox().move(v, v1, v2));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float v) {
        float f = ((float)this.age + v) / (float)this.lifetime;
        return this.quadSize * (1.0F - f * f * 0.5F);
    }

    public int getLightColor(float v) {
        float f = ((float)this.age + v) / (float)this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(v);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double v, double v1, double v2, double v3, double v4, double v5) {
            AmbrosiumShardParticle shardParticle = new AmbrosiumShardParticle(level, v, v1, v2, v3, v4, v5);
            shardParticle.pickSprite(this.sprite);
            return shardParticle;
        }
    }
}

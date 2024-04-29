package org.razordevs.ascended_quark.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.razordevs.ascended_quark.AscendedQuark;

public class AQParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AscendedQuark.MODID);
    public static final RegistryObject<SimpleParticleType> AMBROSIUM_SHARD_PARTICLE = PARTICLE_TYPES.register("ambrosium_shard_particle",
            () -> new SimpleParticleType(true));
}

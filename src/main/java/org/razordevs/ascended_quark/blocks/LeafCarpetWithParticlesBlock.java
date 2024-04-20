package org.razordevs.ascended_quark.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class LeafCarpetWithParticlesBlock extends AQLeafCarpetBlock  {

    private final Supplier<? extends ParticleOptions> particle;

    public LeafCarpetWithParticlesBlock(Supplier<? extends ParticleOptions> particle, Block base, BlockBehaviour.Properties properties) {
        super(base, properties);
        this.particle = particle;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (level.isClientSide() && random.nextInt(10) == 0) {
            for(int i = 0; i < 15; ++i) {
                double x = (double)pos.getX() + ((double)random.nextFloat() - 0.5) * 8.0;
                double y = (double)pos.getY() + ((double)random.nextFloat() - 0.5) * 8.0;
                double z = (double)pos.getZ() + ((double)random.nextFloat() - 0.5) * 8.0;
                double dx = ((double)random.nextFloat() - 0.5) * 0.5;
                double dy = ((double)random.nextFloat() - 0.5) * 0.5;
                double dz = ((double)random.nextFloat() - 0.5) * 0.5;
                level.addParticle(this.particle.get(), x, y, z, dx, dy, dz);
            }
        }
    }
}

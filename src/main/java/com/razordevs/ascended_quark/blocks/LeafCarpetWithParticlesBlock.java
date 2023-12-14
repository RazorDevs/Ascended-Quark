package com.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.arl.interf.IBlockColorProvider;
import vazkii.quark.base.handler.RenderLayerHandler;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class LeafCarpetWithParticlesBlock extends AQLeafCarpetBlock  {

    private final Supplier<? extends ParticleOptions> particle;

    public LeafCarpetWithParticlesBlock(Supplier<? extends ParticleOptions> particle, Block base, BlockBehaviour.Properties properties) {
        super(base, properties);
        this.registerDefaultState((BlockState)this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
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
                level.addParticle((ParticleOptions)this.particle.get(), x, y, z, dx, dy, dz);
            }
        }
    }
}

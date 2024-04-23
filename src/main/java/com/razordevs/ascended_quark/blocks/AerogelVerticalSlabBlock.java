package com.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.construction.AerogelCulling;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vazkii.quark.content.building.block.VerticalSlabBlock;

import java.util.function.Supplier;

public class AerogelVerticalSlabBlock extends VerticalSlabBlock implements AerogelCulling {
    public AerogelVerticalSlabBlock(Supplier<Block> parent, Properties properties) {
        super(parent, properties);
    }

    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return 3;
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir) {
        return AerogelCulling.super.shouldHideNeighboringAerogelFace(level, pos, state, neighborState, dir);
    }

    public boolean skipRendering(BlockState blockState, BlockState blockState1, Direction direction) {
        return blockState1.is(this) ? true : super.skipRendering(blockState, blockState1, direction);
    }
}

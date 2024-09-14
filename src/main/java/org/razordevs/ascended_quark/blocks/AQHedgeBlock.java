package org.razordevs.ascended_quark.blocks;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.base.util.BlockPropertyUtil;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.quark.content.building.module.HedgesModule;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.block.ZetaFenceBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.registry.IZetaBlockColorProvider;

public class AQHedgeBlock extends ZetaFenceBlock implements IZetaBlock, IZetaBlockColorProvider {
    private static final VoxelShape WOOD_SHAPE = box(6.0, 0.0, 6.0, 10.0, 15.0, 10.0);
    private static final VoxelShape HEDGE_CENTER_SHAPE = box(2.0, 1.0, 2.0, 14.0, 16.0, 14.0);
    private static final VoxelShape NORTH_SHAPE = box(2.0, 1.0, 0.0, 14.0, 16.0, 2.0);
    private static final VoxelShape SOUTH_SHAPE = box(2.0, 1.0, 14.0, 14.0, 16.0, 15.0);
    private static final VoxelShape EAST_SHAPE = box(14.0, 1.0, 2.0, 16.0, 16.0, 14.0);
    private static final VoxelShape WEST_SHAPE = box(0.0, 1.0, 2.0, 2.0, 16.0, 14.0);
    private static final VoxelShape EXTEND_SHAPE = box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
    private final Object2IntMap<BlockState> hedgeStateToIndex;
    private final VoxelShape[] hedgeShapes;
    public static final BooleanProperty EXTEND = BooleanProperty.create("extend");

    public AQHedgeBlock(String regname, @Nullable ZetaModule module) {
        super(regname, module, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_FENCE));
        this.hedgeStateToIndex = new Object2IntOpenHashMap<>();
        this.registerDefaultState(this.defaultBlockState().setValue(EXTEND, false));
        this.hedgeShapes = this.cacheHedgeShapes(this.stateDefinition.getPossibleStates());
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return this.hedgeShapes[this.getHedgeAABBIndex(state)];
    }

    private VoxelShape[] cacheHedgeShapes(ImmutableList<BlockState> possibleStates) {
        VoxelShape[] shapes = new VoxelShape[possibleStates.size()];

        for(int i = 0; i < shapes.length; ++i) {
            BlockState state = possibleStates.get(i);
            int realIndex = this.getHedgeAABBIndex(state);
            VoxelShape finishedShape = Shapes.or(state.getValue(EXTEND) ? EXTEND_SHAPE : WOOD_SHAPE, HEDGE_CENTER_SHAPE);
            if (state.getValue(FenceBlock.NORTH)) {
                finishedShape = Shapes.or(finishedShape, NORTH_SHAPE);
            }

            if (state.getValue(FenceBlock.SOUTH)) {
                finishedShape = Shapes.or(finishedShape, SOUTH_SHAPE);
            }

            if (state.getValue(FenceBlock.EAST)) {
                finishedShape = Shapes.or(finishedShape, EAST_SHAPE);
            }

            if (state.getValue(FenceBlock.WEST)) {
                finishedShape = Shapes.or(finishedShape, WEST_SHAPE);
            }

            shapes[realIndex] = finishedShape;
        }

        return shapes;
    }

    protected int getHedgeAABBIndex(BlockState curr) {
        return this.hedgeStateToIndex.computeIntIfAbsent(curr, (state) -> {
            int i = 0;
            if (state.getValue(FenceBlock.NORTH)) {
                i |= 1;
            }

            if (state.getValue(FenceBlock.SOUTH)) {
                i |= 2;
            }

            if (state.getValue(FenceBlock.EAST)) {
                i |= 4;
            }

            if (state.getValue(FenceBlock.WEST)) {
                i |= 8;
            }

            if (state.getValue(EXTEND)) {
                i |= 16;
            }

            return i;
        });
    }

    public boolean connectsTo(BlockState state, boolean isSideSolid, @NotNull Direction direction) {
        return state.is(HedgesModule.hedgesTag);
    }

    public boolean canSustainPlantZeta(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull Direction facing, @NotNull String plantabletype) {
        return facing == Direction.UP && !(Boolean)state.getValue(WATERLOGGED) && "plains".equals(plantabletype);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter iblockreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos down = blockpos.below();
        BlockState downState = iblockreader.getBlockState(down);
        return super.getStateForPlacement(context).setValue(EXTEND, downState.getBlock() instanceof HedgeBlock);
    }

    public @NotNull BlockState updateShape(BlockState stateIn, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor worldIn, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return facing == Direction.DOWN ? stateIn.setValue(EXTEND, facingState.getBlock() instanceof HedgeBlock) : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    protected void createBlockStateDefinition(@NotNull StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXTEND);
    }

    public @Nullable String getBlockColorProviderName() {
        return "hedge";
    }

    public @Nullable String getItemColorProviderName() {
        return "hedge";
    }
}

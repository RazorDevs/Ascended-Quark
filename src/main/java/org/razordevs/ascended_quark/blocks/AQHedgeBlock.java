package org.razordevs.ascended_quark.blocks;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.quark.content.building.module.HedgesModule;
import org.violetmoon.zeta.registry.IZetaBlockColorProvider;

import javax.annotation.Nonnull;
import java.util.function.BooleanSupplier;

public class AQHedgeBlock extends FenceBlock implements IZetaBlockColorProvider {

    private static final VoxelShape WOOD_SHAPE = box(6F, 0F, 6F, 10F, 15F, 10F);
    private static final VoxelShape HEDGE_CENTER_SHAPE = box(2F, 1F, 2F, 14F, 16F, 14F);
    private static final VoxelShape NORTH_SHAPE = box(2F, 1F, 0F, 14F, 16F, 2F);
    private static final VoxelShape SOUTH_SHAPE = box(2F, 1F, 14F, 14F, 16F, 15F);
    private static final VoxelShape EAST_SHAPE = box(14F, 1F, 2F, 16F, 16F, 14F);
    private static final VoxelShape WEST_SHAPE = box(0F, 1F, 2F, 2F, 16F, 14F);
    private static final VoxelShape EXTEND_SHAPE = box(2F, 0F, 2F, 14F, 1F, 14F);

    private final Object2IntMap<BlockState> hedgeStateToIndex;
    private final VoxelShape[] hedgeShapes;
    private final Block leaf;
    private BooleanSupplier enabledSupplier = () -> true;

    public static final BooleanProperty EXTEND = BooleanProperty.create("extend");

    public AQHedgeBlock(Block fence, Block leaf) {
        super(Block.Properties.copy(fence));
        this.leaf = leaf;

        registerDefaultState(defaultBlockState().setValue(EXTEND, false));

        hedgeStateToIndex = new Object2IntOpenHashMap<>();
        hedgeShapes = cacheHedgeShapes(stateDefinition.getPossibleStates());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return hedgeShapes[getHedgeAABBIndex(state)];
    }

    private VoxelShape[] cacheHedgeShapes(ImmutableList<BlockState> possibleStates) {
        VoxelShape[] shapes = new VoxelShape[possibleStates.size()];

        for(int i = 0; i < shapes.length; i++) {
            BlockState state = possibleStates.get(i);
            int realIndex = getHedgeAABBIndex(state);

            VoxelShape finishedShape = Shapes.or(state.getValue(HedgeBlock.EXTEND) ? EXTEND_SHAPE : WOOD_SHAPE, HEDGE_CENTER_SHAPE);
            if(state.getValue(FenceBlock.NORTH))
                finishedShape = Shapes.or(finishedShape, NORTH_SHAPE);
            if(state.getValue(FenceBlock.SOUTH))
                finishedShape = Shapes.or(finishedShape, SOUTH_SHAPE);
            if(state.getValue(FenceBlock.EAST))
                finishedShape = Shapes.or(finishedShape, EAST_SHAPE);
            if(state.getValue(FenceBlock.WEST))
                finishedShape = Shapes.or(finishedShape, WEST_SHAPE);

            shapes[realIndex] = finishedShape;
        }

        return shapes;
    }

    protected int getHedgeAABBIndex(BlockState curr) {
        return hedgeStateToIndex.computeIntIfAbsent(curr, (state) -> {
            int i = 0;

            if(state.getValue(FenceBlock.NORTH))
                i |= 0b00001;
            if(state.getValue(FenceBlock.SOUTH))
                i |= 0b00010;
            if(state.getValue(FenceBlock.EAST))
                i |= 0b00100;
            if(state.getValue(FenceBlock.WEST))
                i |= 0b01000;
            if(state.getValue(EXTEND))
                i |= 0b10000;

            return i;
        });
    }


    @Override
    public boolean connectsTo(BlockState state, boolean isSideSolid, @Nonnull Direction direction) {
        return state.is(HedgesModule.hedgesTag);
    }

    @Override
    public boolean canSustainPlant(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull Direction facing, @Nonnull IPlantable plantable) {
        return facing == Direction.UP && !state.getValue(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter iblockreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos down = blockpos.below();
        BlockState downState = iblockreader.getBlockState(down);

        return super.getStateForPlacement(context)
                .setValue(EXTEND, downState.getBlock() instanceof HedgeBlock);
    }

    @Nonnull
    @Override
    public BlockState updateShape(BlockState stateIn, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor worldIn, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        if(facing == Direction.DOWN)
            return stateIn.setValue(EXTEND, facingState.getBlock() instanceof HedgeBlock);

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXTEND);
    }

    @OnlyIn(Dist.CLIENT)
    public BlockColor getBlockColor() {
        final BlockState leafState = leaf.defaultBlockState();
        return (state, world, pos, tintIndex) -> Minecraft.getInstance().getBlockColors().getColor(leafState, world, pos, tintIndex);
    }

    @OnlyIn(Dist.CLIENT)
    public ItemColor getItemColor() {
        final ItemStack leafStack = new ItemStack(leaf);
        return (stack, tintIndex) -> Minecraft.getInstance().getItemColors().getColor(leafStack, tintIndex);
    }

    @Override
    public @Nullable String getBlockColorProviderName() {
        return null;
    }

    @Override
    public @Nullable String getItemColorProviderName() {
        return null;
    }
}


package org.razordevs.ascended_quark.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.zeta.registry.IZetaBlockColorProvider;

import javax.annotation.Nonnull;

public class AQLeafCarpetBlock extends Block implements IZetaBlockColorProvider {

    private static final VoxelShape SHAPE = box(0, 0, 0, 16, 1, 16);

    private final BlockState baseState;
    private ItemStack baseStack;

    public AQLeafCarpetBlock(Block base) {
        super(Block.Properties.of()
                .noCollission()
                .strength(0F)
                .sound(SoundType.GRASS)
                .noOcclusion()
                .ignitedByLava());

        baseState = base.defaultBlockState();
    }

    @Override
    public boolean canBeReplaced(@Nonnull BlockState state, @Nonnull BlockPlaceContext useContext) {
        return useContext.getItemInHand().isEmpty() || useContext.getItemInHand().getItem() != this.asItem();
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return Shapes.empty();
    }

    @Nonnull
    @Override

    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor world, @Nonnull BlockPos pos, @Nonnull BlockPos facingPos) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, LevelReader world, BlockPos pos) {
        return !world.isEmptyBlock(pos.below());
    }

    @OnlyIn(Dist.CLIENT)
    public ItemColor getItemColor() {
        if (baseStack == null)
            baseStack = new ItemStack(baseState.getBlock());

        return (stack, tintIndex) -> Minecraft.getInstance().getItemColors().getColor(baseStack, tintIndex);
    }

    @OnlyIn(Dist.CLIENT)
    public BlockColor getBlockColor() {
        return (state, worldIn, pos, tintIndex) -> Minecraft.getInstance().getBlockColors().getColor(baseState, worldIn, pos, tintIndex);
    }

    @Override
    public @Nullable String getBlockColorProviderName() {
        return "leaf_carpet";
    }

    @Override
    public @Nullable String getItemColorProviderName() {
        return "leaf_carpet";
    }
}

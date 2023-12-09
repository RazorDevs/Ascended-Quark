package com.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.ToIntFunction;

public class AmbrosiumLampBlock extends Block {

    public static final int MIN_LEVEL = 0;
    public static final int MAX_LEVEL = 4;
    public static final IntegerProperty LIGHT = BlockStateProperties.RESPAWN_ANCHOR_CHARGES;

    public AmbrosiumLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIGHT, Integer.valueOf(0)));
    }


    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (hand == InteractionHand.MAIN_HAND && !isRespawnFuel(itemstack) && isRespawnFuel(player.getItemInHand(InteractionHand.OFF_HAND))) {
            return InteractionResult.PASS;
        } else if (isRespawnFuel(itemstack) && canBeCharged(blockState)) {
            charge(level, blockPos, blockState);
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else if (!isRespawnFuel(itemstack) && isOn(blockState)) {
            deplete(level, blockPos, blockState);
            if (!player.getAbilities().instabuild) {
                player.addItem(new ItemStack(AetherBlocks.AMBROSIUM_BLOCK.get().asItem()));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    private static boolean isRespawnFuel(ItemStack itemStack) {
        return itemStack.is(AetherBlocks.AMBROSIUM_BLOCK.get().asItem());
    }

    private static boolean canBeCharged(BlockState value) {
        return value.getValue(LIGHT) < 4;
    }

    public static void charge(Level level, BlockPos blockPos, BlockState blockState) {
        level.setBlock(blockPos, blockState.setValue(LIGHT, Integer.valueOf(blockState.getValue(LIGHT) + 1)), 3);
        level.playSound((Player)null, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void deplete(Level level, BlockPos blockPos, BlockState blockState) {
        if(blockState.getValue(LIGHT) > 0)
            level.setBlock(blockPos, blockState.setValue(LIGHT, Integer.valueOf(blockState.getValue(LIGHT) - 1)), 3);
        level.playSound((Player)null, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> add) {
        add.add(LIGHT);
    }

    private static boolean isOn(BlockState value) {
        return value.getValue(LIGHT) > 0;
    }

    public static int getScaledChargeLevel(BlockState blockState, int i) {
        return Mth.floor((float)(blockState.getValue(LIGHT)) / 4.0F * (float)i);
    }
}

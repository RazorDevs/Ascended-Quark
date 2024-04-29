package org.razordevs.ascended_quark.entity.render;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.entity.model.AQAtlases;

public class AQChestRender extends ChestRenderer<ChestBlockEntity> {
    public AQChestRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(@NotNull ChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> AQAtlases.SKYROOT_CHEST_LEFT_MATERIAL;
            case RIGHT -> AQAtlases.SKYROOT_CHEST_RIGHT_MATERIAL;
            case SINGLE -> AQAtlases.SKYROOT_CHEST_MATERIAL;
        };
    }
}

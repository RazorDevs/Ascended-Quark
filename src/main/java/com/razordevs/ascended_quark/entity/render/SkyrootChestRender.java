package com.razordevs.ascended_quark.entity.render;

import com.aetherteam.aether.client.AetherAtlases;
import com.razordevs.ascended_quark.entity.model.AQAtlases;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;

public class SkyrootChestRender extends ChestRenderer<ChestBlockEntity> {
    public SkyrootChestRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(ChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> AQAtlases.SKYROOT_CHEST_LEFT_MATERIAL;
            case RIGHT -> AQAtlases.SKYROOT_CHEST_RIGHT_MATERIAL;
            case SINGLE -> AQAtlases.SKYROOT_CHEST_MATERIAL;
        };
    }
}

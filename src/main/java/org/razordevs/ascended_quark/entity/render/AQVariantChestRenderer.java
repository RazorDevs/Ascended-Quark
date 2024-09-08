package org.razordevs.ascended_quark.entity.render;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.content.building.client.render.be.VariantChestRenderer;
import org.violetmoon.quark.content.building.module.VariantChestsModule;

import java.util.HashMap;
import java.util.Map;

public class AQVariantChestRenderer extends VariantChestRenderer {
    private final Map<Pair<Block, ChestType>, Material> materialMap = new HashMap();

    public AQVariantChestRenderer(BlockEntityRendererProvider.Context context, boolean isTrap) {
        super(context, isTrap);
    }

    public @Nullable Material getMaterial(ChestBlockEntity tile, ChestType type) {
        Block block = tile.getBlockState().getBlock();
        Pair<Block, ChestType> pair = Pair.of(block, type);
        return this.materialMap.computeIfAbsent(pair, (b) -> {
            if (block instanceof VariantChestsModule.IVariantChest v) {
                StringBuilder tex = (new StringBuilder(v.getTextureFolder())).append('/').append(v.getTexturePath()).append('/');
                if (this.isTrap) {
                    tex.append(this.choose(type, "trap", "trap_left", "trap_right"));
                } else {
                    tex.append(this.choose(type, "normal", "left", "right"));
                }

                return new Material(Sheets.CHEST_SHEET, new ResourceLocation(AscendedQuark.MODID, tex.toString()));
            } else {
                return null;
            }
        });
    }
}

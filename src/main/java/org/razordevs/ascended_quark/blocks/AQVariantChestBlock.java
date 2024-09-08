package org.razordevs.ascended_quark.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.entity.block.AQVariantChestBlockEntity;
import org.violetmoon.quark.content.building.block.VariantChestBlock;
import org.violetmoon.quark.content.building.block.be.VariantChestBlockEntity;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.function.Supplier;

public class AQVariantChestBlock extends VariantChestBlock {
    public AQVariantChestBlock(String prefix, String type, ZetaModule module, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, Properties props) {
        super(prefix, type, module, supplier, props);
    }

    public AQVariantChestBlock(String type, ZetaModule module, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, Properties props) {
        super(type, module, supplier, props);
    }

    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AQVariantChestBlockEntity(pos, state);
    }
}

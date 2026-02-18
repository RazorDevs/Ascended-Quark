package org.razordevs.ascended_quark.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.razordevs.ascended_quark.module.SkyrootQuarkBlocksModule;

public class AQVariantChestBlockEntity extends ChestBlockEntity {
    protected AQVariantChestBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    public AQVariantChestBlockEntity(BlockPos pos, BlockState state) {
        super(SkyrootQuarkBlocksModule.aqChestTEType, pos, state);
    }

    public AABB getRenderBoundingBox() {
        var offset1 = this.worldPosition.offset(-1, 0, -1);
        var offset2 = this.worldPosition.offset(2, 2, 2);
        return new AABB(new Vec3(offset1.getX(), offset1.getY(), offset1.getZ()), new Vec3(offset2.getX(), offset2.getY(), offset2.getZ()));
    }
}

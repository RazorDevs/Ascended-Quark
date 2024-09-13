package org.razordevs.ascended_quark.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.HollowPillarBlock;
import org.violetmoon.zeta.block.ZetaPillarBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.registry.RenderLayerRegistry;
import org.violetmoon.zeta.util.MiscUtil;

public class AQHollowLogBlock extends HollowPillarBlock {

    public AQHollowLogBlock(String name, @Nullable ZetaModule module) {
        super(name, module, MiscUtil.copyPropertySafe(Blocks.OAK_LOG).isSuffocating((s, g, p) -> false).noOcclusion());
        if (module != null) {
            module.zeta.renderLayerRegistry.put(this, RenderLayerRegistry.Layer.CUTOUT_MIPPED);
        }
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
}

package org.razordevs.ascended_quark.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.zeta.module.ZetaModule;

public class AQHedgeBlock extends HedgeBlock {

    private final RegistryObject<? extends Block> leaf;
    public AQHedgeBlock(String regname, @Nullable ZetaModule module, RegistryObject<? extends Block> leaf) {
        super(regname, module, Blocks.OAK_FENCE, Blocks.OAK_LEAVES);
        this.leaf = leaf;
    }

    @Override
    public BlockState getLeaf() {
        return this.leaf.get().defaultBlockState();
    }
}


package org.razordevs.ascended_quark.blocks;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.LeafCarpetBlock;
import org.violetmoon.zeta.module.ZetaModule;

public class AQLeafCarpetBlock extends LeafCarpetBlock {

    public AQLeafCarpetBlock(String name, @Nullable ZetaModule module) {
        super(name, Blocks.OAK_LEAVES, module);
    }

    @Override
    public Block setCreativeTab(ResourceKey<CreativeModeTab> tab, ItemLike parent, boolean behindParent) {
        return this;
    }
}

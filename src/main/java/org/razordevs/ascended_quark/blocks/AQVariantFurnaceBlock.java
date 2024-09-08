package org.razordevs.ascended_quark.blocks;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.violetmoon.quark.content.building.block.VariantFurnaceBlock;
import org.violetmoon.zeta.module.ZetaModule;

public class AQVariantFurnaceBlock extends VariantFurnaceBlock {
    public AQVariantFurnaceBlock(String type, ZetaModule module, Properties props) {
        super(type, module, props);
    }

    @Override
    public Block setCreativeTab(ResourceKey<CreativeModeTab> tab, ItemLike parent, boolean behindParent) {
        return this;
    }
}

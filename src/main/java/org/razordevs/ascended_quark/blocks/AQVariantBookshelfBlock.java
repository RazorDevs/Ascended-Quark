package org.razordevs.ascended_quark.blocks;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.VariantBookshelfBlock;
import org.violetmoon.zeta.module.ZetaModule;

public class AQVariantBookshelfBlock extends VariantBookshelfBlock {
    public AQVariantBookshelfBlock(String type, @Nullable ZetaModule module, boolean flammable, SoundType sound) {
        super(type, module, flammable, sound);
    }

    @Override
    public Block setCreativeTab(ResourceKey<CreativeModeTab> tab, ItemLike parent, boolean behindParent) {
        return this;
    }
}

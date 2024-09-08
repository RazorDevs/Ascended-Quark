package org.razordevs.ascended_quark.blocks;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.util.BlockPropertyUtil;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.block.ZetaSlabBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.registry.CreativeTabManager;
import org.violetmoon.zeta.util.BooleanSuppliers;

import java.util.function.BooleanSupplier;

public class AQVerticalSlabBlock extends VerticalSlabBlock implements IZetaBlock {
    private final ZetaModule module;
    private BooleanSupplier enabledSupplier;

    public AQVerticalSlabBlock(Block parent, ZetaModule module) {
        super(()->parent, BlockPropertyUtil.copyPropertySafe(parent));
        this.enabledSupplier = BooleanSuppliers.TRUE;
        String resloc = AscendedQuark.ZETA.registryUtil.inherit(parent, (s) -> s.replace("_slab", "_vertical_slab"));
        AscendedQuark.ZETA.registry.registerBlock(this, resloc, true);
        this.module = module;
        if (module.category.isAddon()) {
            module.zeta.requiredModTooltipHandler.map(this, module.category.requiredMod);
        }

        if (!(parent instanceof SlabBlock)) {
            throw new IllegalArgumentException("Can't rotate a non-slab block into a vertical slab.");
        } else {
            if (parent instanceof ZetaSlabBlock slab) {
                this.setCondition(slab.parent::isEnabled);
            }
            CreativeTabManager.addToCreativeTabNextTo(CreativeModeTabs.BUILDING_BLOCKS, this, parent, false);
        }
    }

    public AQVerticalSlabBlock setCondition(BooleanSupplier enabledSupplier) {
        this.enabledSupplier = enabledSupplier;
        return this;
    }

    public boolean doesConditionApply() {
        return this.enabledSupplier.getAsBoolean();
    }

    public @Nullable ZetaModule getModule() {
        return this.module;
    }
}

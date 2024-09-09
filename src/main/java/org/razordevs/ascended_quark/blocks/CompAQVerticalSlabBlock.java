package org.razordevs.ascended_quark.blocks;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class CompAQVerticalSlabBlock extends VerticalSlabBlock implements IZetaBlock {
    private final ZetaModule module;
    private BooleanSupplier enabledSupplier;

    @SuppressWarnings("unchecked")
    public CompAQVerticalSlabBlock(String registryName, Supplier<? extends Block> parent, Properties properties, ZetaModule module) {
        super((Supplier<Block>) parent, properties);
        this.enabledSupplier = BooleanSuppliers.TRUE;
        AscendedQuark.ZETA.registry.registerBlock(this, registryName, true);
        this.module = module;

        if (module.category.isAddon()) {
            module.zeta.requiredModTooltipHandler.map(this, module.category.requiredMod);
        }
    }

    public CompAQVerticalSlabBlock setCondition(BooleanSupplier enabledSupplier) {
        this.enabledSupplier = enabledSupplier;
        return this;
    }

    public boolean doesConditionApply() {
        return this.enabledSupplier.getAsBoolean();
    }

    public @Nullable ZetaModule getModule() {
        return this.module;
    }

    @Override
    @Nullable
    public String getBlockColorProviderName() {
        return null;
    }

    @Override
    @Nullable
    public String getItemColorProviderName() {
        return null;
    }
}

package org.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.FrictionCapped;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.IronBarsBlock;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.function.BooleanSupplier;

public class AQGlassPaneBlock extends IronBarsBlock implements FrictionCapped, IZetaBlock {

    private final ZetaModule module;

    public AQGlassPaneBlock(String name, Properties properties, ZetaModule module) {
        super(properties);
        module.zeta.registry.registerBlock(this, name, true);
        this.module = module;
    }

    @SuppressWarnings("InfiniteRecursion")
    @Override
    public float getCappedFriction(@Nullable Entity entity, float defaultFriction) {
        return this.getCappedFriction(entity, super.getFriction());
    }

    public ZetaModule getModule() {
        return this.module;
    }

    public IZetaBlock setCondition(BooleanSupplier condition) {
        return this;
    }

    public boolean doesConditionApply() {
        return true;
    }
}


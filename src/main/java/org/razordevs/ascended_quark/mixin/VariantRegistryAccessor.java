package org.razordevs.ascended_quark.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.registry.VariantRegistry;

@Mixin(VariantRegistry.class)
public interface VariantRegistryAccessor {
    @Accessor(remap = false)
    Zeta getZeta();
}

package org.razordevs.ascended_quark.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.module.ZetaModule;

@Mixin(value = ZetaModule.class, remap = false)
public interface ZetaModuleAccessor {
    @Invoker(remap = false)
    void callSetEnabled(Zeta z, boolean willEnable);
}

package org.razordevs.ascended_quark.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.violetmoon.zeta.module.ModuleFinder;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.Map;

@Mixin(value = ModuleFinder.class, remap = false)
public interface ModuleFinderAccessor {
    @Accessor
    Map<Class<? extends ZetaModule>, ZetaModule> getFoundModules();

    @Mutable
    @Accessor
    void setFoundModules(Map<Class<? extends ZetaModule>, ZetaModule> foundModules);
}

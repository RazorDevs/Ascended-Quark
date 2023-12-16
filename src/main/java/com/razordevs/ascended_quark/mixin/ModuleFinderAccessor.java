package com.razordevs.ascended_quark.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import vazkii.quark.base.module.ModuleFinder;
import vazkii.quark.base.module.QuarkModule;

import java.util.Map;

@Mixin(value = ModuleFinder.class, remap = false)
public interface ModuleFinderAccessor {
    @Accessor
    Map<Class<? extends QuarkModule>, QuarkModule> getFoundModules();

    @Mutable
    @Accessor
    void setFoundModules(Map<Class<? extends QuarkModule>, QuarkModule> foundModules);
}

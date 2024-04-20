package org.razordevs.ascended_quark.mixin;


import org.razordevs.ascended_quark.config.ModuleFinderHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.base.module.ModuleFinder;

@Mixin(value = ModuleFinder.class, remap = false)
public abstract class ModuleFinderMixin {
    @Inject(at = @At("TAIL"), method = "findModules")
    public void findModules(CallbackInfo ci) {
        new ModuleFinderHelper().start(this);
    }
}

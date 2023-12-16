package com.razordevs.ascended_quark.mixin;


import com.razordevs.ascended_quark.ModuleFinderHelper;
import net.minecraftforge.fml.loading.moddiscovery.ModAnnotation;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.ModuleFinder;
import vazkii.quark.base.module.QuarkModule;

import java.util.*;

@Mixin(value = ModuleFinder.class, remap = false)
public abstract class ModuleFinderMixin {
    @Inject(at = @At("TAIL"), method = "findModules")
    public void findModules(CallbackInfo ci) {
        new ModuleFinderHelper().start(this);
    }
}

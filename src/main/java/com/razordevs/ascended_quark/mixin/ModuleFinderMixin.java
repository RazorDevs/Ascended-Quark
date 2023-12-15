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

@Mixin(value = ModuleFinder.class)
public abstract class ModuleFinderMixin {

    @Unique
    ModuleFinderHelper quark_aether$helper = new ModuleFinderHelper();

    @Shadow(remap = false) protected abstract ModuleCategory getOrMakeCategory(ModAnnotation.EnumHolder category);

    @Shadow(remap = false) @Final private Map<Class<? extends QuarkModule>, QuarkModule> foundModules;

    @Inject(at = @At("TAIL"), method = "findModules", remap = false, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void findModules(CallbackInfo ci) {
        quark_aether$helper.start(this);
    }
}

package com.razordevs.ascended_quark.mixin;

import com.razordevs.ascended_quark.events.AQEvents;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.tools.module.SlimeInABucketModule;

@Mixin(value = SlimeInABucketModule.class, remap = false)
public class SlimeInABucketModuleMixin extends QuarkModule {

    @Override
    public void setup() {
        AQEvents.isSlimeInABucketModuleEnabled = this.enabled;
    }
}

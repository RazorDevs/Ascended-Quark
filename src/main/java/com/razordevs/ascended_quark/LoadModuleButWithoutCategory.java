package com.razordevs.ascended_quark;

import net.minecraftforge.api.distmarker.Dist;
import vazkii.quark.base.module.ModuleCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadModuleButWithoutCategory {
    String name() default "";
    String description() default "";
    String[] antiOverlap() default { };

    boolean hasSubscriptions() default false;
    Dist[] subscribeOn() default { Dist.CLIENT, Dist.DEDICATED_SERVER };

    boolean enabledByDefault() default true;

}

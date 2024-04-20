package org.razordevs.ascended_quark.config;

import org.violetmoon.zeta.module.ZetaCategory;

//based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
public class AetherModuleCategory {
    static {
        ZetaCategory.values(); // Ensure class is loaded before the variant is accessed
    }
    public static ZetaCategory THE_AETHER;
}

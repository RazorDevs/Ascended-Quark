package org.razordevs.ascended_quark.mixin;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.violetmoon.zeta.registry.ZetaRegistry;

import java.util.Map;

//TODO: REMOVE BEFORE RELEASE
@Mixin(ZetaRegistry.class)
public interface ZetaRegistryAccessor {

    @Accessor(remap = false)
    Map<Object, ResourceLocation> getInternalNames();
}

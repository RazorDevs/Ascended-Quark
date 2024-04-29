package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.client.renderer.entity.PhygRenderer;
import com.aetherteam.aether.entity.passive.Phyg;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PhygRenderer.class)
public class PhygRendererMixin {
    @Inject(method = "getTextureLocation(Lcom/aetherteam/aether/entity/passive/Phyg;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true, remap = false)
    private void overrideTexture(Phyg pig, CallbackInfoReturnable<ResourceLocation> cir) {
        ResourceLocation loc = AetherVariantAnimalTexturesModule.Client.getPhygTexture(pig);
        if (loc != null)
            cir.setReturnValue(loc);
    }
}

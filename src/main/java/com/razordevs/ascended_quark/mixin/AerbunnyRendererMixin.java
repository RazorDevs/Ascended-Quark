package com.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.client.renderer.entity.AerbunnyRenderer;
import com.aetherteam.aether.client.renderer.entity.PhygRenderer;
import com.aetherteam.aether.entity.passive.Aerbunny;
import com.aetherteam.aether.entity.passive.Phyg;
import com.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AerbunnyRenderer.class)
public class AerbunnyRendererMixin {
    @Inject(method = "getTextureLocation(Lcom/aetherteam/aether/entity/passive/Aerbunny;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true, remap = false)
    private void overrideTexture(Aerbunny aerbunny, CallbackInfoReturnable<ResourceLocation> cir) {
        ResourceLocation loc = AetherVariantAnimalTexturesModule.getAerbunnyTexture(aerbunny);
        if (loc != null)
            cir.setReturnValue(loc);
    }
}

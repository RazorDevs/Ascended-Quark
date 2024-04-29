package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.client.renderer.entity.CockatriceRenderer;
import com.aetherteam.aether.entity.monster.Cockatrice;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CockatriceRenderer.class)
public class CockatriceRendererMixin {
    @Inject(method = "getTextureLocation(Lcom/aetherteam/aether/entity/monster/Cockatrice;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true, remap = false)
    private void overrideTexture(Cockatrice cockatrice, CallbackInfoReturnable<ResourceLocation> cir) {
        ResourceLocation loc = AetherVariantAnimalTexturesModule.Client.getCockatriceTexture(cockatrice);
        if (loc != null)
            cir.setReturnValue(loc);
    }
}

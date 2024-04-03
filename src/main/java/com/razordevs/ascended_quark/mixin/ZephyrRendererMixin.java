package com.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.AerbunnyRenderer;
import com.aetherteam.aether.client.renderer.entity.MultiModelRenderer;
import com.aetherteam.aether.client.renderer.entity.ZephyrRenderer;
import com.aetherteam.aether.client.renderer.entity.model.ClassicZephyrModel;
import com.aetherteam.aether.client.renderer.entity.model.ZephyrModel;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether.entity.passive.Aerbunny;
import com.llamalad7.mixinextras.sugar.Local;
import com.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ZephyrRenderer.class, remap = false)
public class ZephyrRendererMixin extends MultiModelRenderer<Zephyr, EntityModel<Zephyr>, ZephyrModel, ClassicZephyrModel> {
    private static final ResourceLocation ZEPHYR_TEXTURE = new ResourceLocation("aether", "textures/entity/mobs/zephyr/zephyr.png");
    private static final ResourceLocation ZEPHYR_CLASSIC_TEXTURE = new ResourceLocation("aether", "textures/entity/mobs/zephyr/zephyr_classic.png");
    private final ZephyrModel defaultModel;
    private final ClassicZephyrModel oldModel;

    public ZephyrRendererMixin(EntityRendererProvider.Context context, ZephyrModel defaultModel, float shadowRadius) {
        super(context, defaultModel, shadowRadius);
        this.defaultModel = new ZephyrModel(context.bakeLayer(AetherModelLayers.ZEPHYR));
        this.oldModel = new ClassicZephyrModel(context.bakeLayer(AetherModelLayers.ZEPHYR_CLASSIC));
    }

    @Override
    public ResourceLocation getTextureLocation(Zephyr entity) {
        return AetherConfig.CLIENT.legacy_models.get() ? this.getOldTexture() : AetherVariantAnimalTexturesModule.getZephyrTexture(entity);
    }

    public ZephyrModel getDefaultModel() {
        return this.defaultModel;
    }

    public ClassicZephyrModel getOldModel() {
        return this.oldModel;
    }

    public ResourceLocation getDefaultTexture() {
        return ZEPHYR_TEXTURE;
    }

    public ResourceLocation getOldTexture() {
        return ZEPHYR_CLASSIC_TEXTURE;
    }
}

package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.MultiModelRenderer;
import com.aetherteam.aether.client.renderer.entity.ZephyrRenderer;
import com.aetherteam.aether.client.renderer.entity.layers.ZephyrTransparencyLayer;
import com.aetherteam.aether.client.renderer.entity.model.ClassicZephyrModel;
import com.aetherteam.aether.client.renderer.entity.model.ZephyrModel;
import com.aetherteam.aether.entity.monster.Zephyr;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ZephyrRenderer.class)
public class ZephyrRendererMixin extends MultiModelRenderer<Zephyr, EntityModel<Zephyr>, ZephyrModel, ClassicZephyrModel> {
    @Unique
    private static final ResourceLocation ZEPHYR_TEXTURE = new ResourceLocation("aether", "textures/entity/mobs/zephyr/zephyr.png");
    @Unique
    private static final ResourceLocation ZEPHYR_CLASSIC_TEXTURE = new ResourceLocation("aether", "textures/entity/mobs/zephyr/zephyr_classic.png");
    @Unique
    private final ZephyrModel ascended_quark$defaultModel;
    @Unique
    private final ClassicZephyrModel ascended_quark$oldModel;

    public ZephyrRendererMixin(EntityRendererProvider.Context context, ZephyrModel defaultModel, float shadowRadius) {
        super(context, defaultModel, shadowRadius);
        this.addLayer(new ZephyrTransparencyLayer(this, new ZephyrModel(context.getModelSet().bakeLayer(AetherModelLayers.ZEPHYR_TRANSPARENCY))));
        this.ascended_quark$defaultModel = new ZephyrModel(context.bakeLayer(AetherModelLayers.ZEPHYR));
        this.ascended_quark$oldModel = new ClassicZephyrModel(context.bakeLayer(AetherModelLayers.ZEPHYR_CLASSIC));
    }

    @Override
    public ResourceLocation getTextureLocation(Zephyr entity) {
        return AetherConfig.CLIENT.legacy_models.get() ? this.getOldTexture() : AetherVariantAnimalTexturesModule.Client.getZephyrTexture(entity);
    }

    public ZephyrModel getDefaultModel() {
        return this.ascended_quark$defaultModel;
    }

    public ClassicZephyrModel getOldModel() {
        return this.ascended_quark$oldModel;
    }

    public ResourceLocation getDefaultTexture() {
        return ZEPHYR_TEXTURE;
    }

    public ResourceLocation getOldTexture() {
        return ZEPHYR_CLASSIC_TEXTURE;
    }
}

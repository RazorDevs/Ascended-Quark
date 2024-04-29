package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.client.renderer.entity.layers.ZephyrTransparencyLayer;
import com.aetherteam.aether.client.renderer.entity.model.ZephyrModel;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ZephyrTransparencyLayer.class)
public class ZephyrLayerMixin extends RenderLayer<Zephyr, EntityModel<Zephyr>> {
    private static final ResourceLocation ZEPHYR_TRANSPARENCY_TEXTURE = new ResourceLocation("aether", "textures/entity/mobs/zephyr/zephyr_layer.png");
    private final ZephyrModel transparency;

    public ZephyrLayerMixin(RenderLayerParent<Zephyr, EntityModel<Zephyr>> entityRenderer, ZephyrModel transparencyModel) {
        super(entityRenderer);
        this.transparency = transparencyModel;
    }


    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Zephyr zephyr, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (this.getParentModel() instanceof ZephyrModel && !zephyr.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.transparency);
            this.transparency.prepareMobModel(zephyr, limbSwing, limbSwingAmount, partialTicks);
            this.transparency.setupAnim(zephyr, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(AetherVariantAnimalTexturesModule.Client.getZephyrLayerTexture(zephyr)));
            this.transparency.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(zephyr, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }

    }
}

package org.razordevs.ascended_quark.entity.render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.entity.AmbrosiumTorchArrow;

public class AmbrosiumTorchArrowRenderer extends ArrowRenderer<AmbrosiumTorchArrow> {

    public static final ResourceLocation TORCH_ARROW_LOCATION = new ResourceLocation(AscendedQuark.MODID, "textures/model/entity/ambrosium_torch_arrow.png");

    public AmbrosiumTorchArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(AmbrosiumTorchArrow torchArrow) {
        return TORCH_ARROW_LOCATION;
    }
}
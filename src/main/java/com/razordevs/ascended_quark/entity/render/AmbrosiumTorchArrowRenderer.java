package com.razordevs.ascended_quark.entity.render;

import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.items.AmbrosiumTorchArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import vazkii.quark.base.Quark;

public class AmbrosiumTorchArrowRenderer extends ArrowRenderer<AmbrosiumTorchArrow> {

    public static final ResourceLocation TORCH_ARROW_LOCATION = new ResourceLocation(Quark.MOD_ID, "textures/model/entity/torch_arrow.png");

    public AmbrosiumTorchArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(AmbrosiumTorchArrow torchArrow) {
        return TORCH_ARROW_LOCATION;
    }
}
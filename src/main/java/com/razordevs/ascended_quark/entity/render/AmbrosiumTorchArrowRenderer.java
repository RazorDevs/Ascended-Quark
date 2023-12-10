package com.razordevs.ascended_quark.entity.render;

import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.entity.AmbrosiumTorchArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import vazkii.quark.base.Quark;

public class AmbrosiumTorchArrowRenderer extends ArrowRenderer<AmbrosiumTorchArrow> {

    public static final ResourceLocation TORCH_ARROW_LOCATION = new ResourceLocation(AscendedQuarkMod.MODID, "textures/model/entity/ambrosium_torch_arrow.png");

    public AmbrosiumTorchArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(AmbrosiumTorchArrow torchArrow) {
        return TORCH_ARROW_LOCATION;
    }
}
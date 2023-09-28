package com.razordevs.ascended_quark.entity.render;

import com.razordevs.ascended_quark.entity.Stool;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class StoolEntityRender extends EntityRenderer<Stool> {

	public StoolEntityRender(EntityRendererProvider.Context context) {
            super(context);
        }

        @Nonnull
        @Override
        public ResourceLocation getTextureLocation(@Nonnull Stool entity) {
            return null;
        }

        @Override
        public boolean shouldRender(@Nonnull Stool livingEntityIn, @Nonnull Frustum camera, double camX, double camY, double camZ) {
            return false;
        }
    }

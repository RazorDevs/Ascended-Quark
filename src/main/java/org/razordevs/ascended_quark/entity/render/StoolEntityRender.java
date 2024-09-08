package org.razordevs.ascended_quark.entity.render;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.entity.AQStool;

import javax.annotation.Nonnull;

public class StoolEntityRender extends EntityRenderer<AQStool> {

	public StoolEntityRender(EntityRendererProvider.Context context) {
            super(context);
        }
        @Nonnull
        @Override
        public ResourceLocation getTextureLocation(@Nonnull AQStool entity) {
            return null;
        }

        @Override
        public boolean shouldRender(@Nonnull AQStool livingEntityIn, @Nonnull Frustum camera, double camX, double camY, double camZ) {
            return false;
        }
    }

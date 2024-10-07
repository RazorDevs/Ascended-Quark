package org.razordevs.ascended_quark.entity.render;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.entity.AQStool;
import org.violetmoon.quark.content.building.client.render.entity.StoolEntityRenderer;

import javax.annotation.Nonnull;

/**
 *  CODE COPY - {@link StoolEntityRenderer}
 */
public class AQStoolEntityRenderer extends EntityRenderer<AQStool> {

	public AQStoolEntityRenderer(EntityRendererProvider.Context context) {
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

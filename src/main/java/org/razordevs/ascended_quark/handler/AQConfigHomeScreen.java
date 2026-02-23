package org.razordevs.ascended_quark.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.proxy.AQClient;
import org.violetmoon.quark.base.client.config.SocialButton;
import org.violetmoon.zeta.client.config.screen.ZetaConfigHomeScreen;

import java.util.Iterator;
import java.util.List;

public class AQConfigHomeScreen extends ZetaConfigHomeScreen {

	// TODO: Better Panorama

	private static final CubeMap CUBE_MAP = new CubeMap(AscendedQuark.asResource("textures/misc/panorama/panorama"));
	private static final PanoramaRenderer PANORAMA = new PanoramaRenderer(CUBE_MAP);
	private float time;

	public AQConfigHomeScreen(Screen parent) {
		super(AQClient.ZETA_CLIENT, parent);
	}

	@Override
	protected void init() {
		super.init();

		List<Integer> socialButtonPlacements = centeredRow(width / 2, 20, 5, 5);
		Iterator<Integer> iter = socialButtonPlacements.iterator();
		addRenderableWidget(new SocialButton(iter.next(), height - 55,
				Component.translatable("ascended_quark.gui.config.social.discord"), 0x7289da, 1,
				"https://discord.gg/Y6fabygHRk"));
		addRenderableWidget(new SocialButton(iter.next(), height - 55,
				Component.translatable("ascended_quark.gui.config.social.website"), 0x7289da, 2,
				"https://razordevs.github.io/"));
	}

	// annoyingly it's not passed to renderBackground
	protected float partialTicks;

	@Override
	public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
		time += partialTicks;

		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) {
			float spin = partialTicks * 2;
			float blur = 0.85F;

			if (time < 20F && !AQGeneralConfig.disableAQMenuEffects) {
				spin += (20F - time);
				blur = (time / 20F) * 0.75F + 0.1F;
			}

			PANORAMA.render(graphics, this.width, this.height, blur, partialTicks);
		} else
			super.renderBackground(graphics, mouseX, mouseY, partialTick);

		int boxWidth = 400;
		graphics.fill(width / 2 - boxWidth / 2, 0, width / 2 + boxWidth / 2, this.height, 0x66000000);
		graphics.fill(width / 2 - boxWidth / 2 - 1, 0, width / 2 - boxWidth / 2, this.height, 0x66999999); // nice
		graphics.fill(width / 2 + boxWidth / 2, 0, width / 2 + boxWidth / 2 + 1, this.height, 0x66999999);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.partialTicks = partialTicks;

		super.render(guiGraphics, mouseX, mouseY, partialTicks);
	}

}

package org.razordevs.ascended_quark.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.proxy.AQClient;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.base.client.config.SocialButton;
import org.violetmoon.zeta.client.config.screen.ZetaConfigHomeScreen;

import java.util.Iterator;
import java.util.List;

public class AQConfigHomeScreen extends ZetaConfigHomeScreen {

    private static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation(AscendedQuark.MODID, "textures/misc/panorama/panorama"));
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
        addRenderableWidget(new SocialButton(iter.next(), height - 55, Component.translatable("ascended_quark.gui.config.social.discord"), 0x7289da, 1, "https://discord.gg/Y6fabygHRk"));
    }

    //annoyingly it's not passed to renderBackground
    protected float partialTicks;

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        time += partialTicks;

        Minecraft mc = Minecraft.getInstance();
        if(mc.level == null) {
            float spin = partialTicks * 2;
            float blur = 0.85F;

            if(time < 20F && !AQGeneralConfig.disableAQMenuEffects) {
                spin += (20F - time);
                blur = (time / 20F) * 0.75F + 0.1F;
            }

            PANORAMA.render(spin, blur);
        } else
            super.renderBackground(guiGraphics);

        int boxWidth = 400;
        guiGraphics.fill(width / 2 - boxWidth / 2, 0, width / 2 + boxWidth / 2, this.height, 0x66000000);
        guiGraphics.fill(width / 2 - boxWidth / 2 - 1, 0, width / 2 - boxWidth / 2, this.height, 0x66999999); // nice
        guiGraphics.fill(width / 2 + boxWidth / 2, 0, width / 2 + boxWidth / 2 + 1, this.height, 0x66999999);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.partialTicks = partialTicks;

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

}

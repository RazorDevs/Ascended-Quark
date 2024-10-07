package org.razordevs.ascended_quark.handler;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.razordevs.ascended_quark.proxy.AQClient;
import org.violetmoon.quark.base.client.handler.ClientUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AQButton extends Button {

    private final boolean showBubble;

    public AQButton(int x, int y) {
        super(Button.builder(Component.literal("aq"), AQButton::click).size(20, 20).pos(x, y));
        showBubble = !getAQMarkerFile().exists();
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);

        int iconIndex = 4;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);

        int rx = getX() - 2;
        int ry = getY() - 2;

        int w = 9;
        int h = 9;

        int v = 26;

        int u = 256 - iconIndex * w;

        guiGraphics.blit(ClientUtil.GENERAL_ICONS, rx, ry, u, v, w, h);

        if(showBubble && AQGeneralConfig.enableOnboarding) {
            Font font = Minecraft.getInstance().font;
            int cy = getY() - 2;
            if(AQClient.ticker.total % 20 > 10)
                cy++;

            ClientUtil.drawChatBubble(guiGraphics, getX() + 16, cy, font, I18n.get("ascended_quark.misc.configure_ascended_quark_here"), alpha, true);
        }
    }

    private static File getAQMarkerFile() {
        return new File(Minecraft.getInstance().gameDirectory, ".qmenu_opened.marker");
    }

    public static void click(Button b) {
        if(b instanceof AQButton qb && qb.showBubble)
            try {
                getAQMarkerFile().createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create file.");
                e.printStackTrace();
            }

        Minecraft.getInstance().setScreen(new AQConfigHomeScreen(Minecraft.getInstance().screen));
    }
}

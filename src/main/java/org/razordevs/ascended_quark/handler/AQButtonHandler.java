package org.razordevs.ascended_quark.handler;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.resources.language.I18n;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AQGeneralConfig;
import org.violetmoon.quark.base.config.QuarkGeneralConfig;
import org.violetmoon.zeta.client.event.play.ZScreen;
import org.violetmoon.zeta.event.bus.PlayEvent;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AQButtonHandler {

    @PlayEvent
    public static void onGuiInit(ZScreen.Init.Post event) {
        if(!AQGeneralConfig.enableAQButton)
            return;

        Set<String> targetButtonTranslationKeys = getTargetButtons(event.getScreen());
        if(targetButtonTranslationKeys == null || targetButtonTranslationKeys.isEmpty())
            return;

        Set<String> targetButtonNames = targetButtonTranslationKeys.stream()
                .map(I18n::get)
                .collect(Collectors.toSet());

        List<GuiEventListener> listeners = event.getListenersList();
        for(GuiEventListener listener : listeners)
            if(listener instanceof AbstractWidget widget) {
                if(targetButtonNames.contains(widget.getMessage().getString())) {
                    int x = widget.getX();

                    if(AQGeneralConfig.aqButtonOnRight)
                        if(QuarkGeneralConfig.qButtonOnRight && QuarkGeneralConfig.enableQButton)
                            x += widget.getWidth() + 28;
                        else
                            x += widget.getWidth() + 4;
                    else
                        if(QuarkGeneralConfig.qButtonOnRight || !QuarkGeneralConfig.enableQButton)
                            x -= 24;
                        else
                            x -= 48;

                    Button aqButton = new AQButton(x, widget.getY());
                    event.addListener(aqButton);
                    return;
                }
            }
    }

    private static @Nullable Set<String> getTargetButtons(Screen gui) {
        if(gui instanceof TitleScreen)
            if(AQGeneralConfig.aqButtonOnRight)
                return Set.of("menu.online"); // Minecraft Realms
            else
                return Set.of("fml.menu.mods.title", "fml.menu.mods"); // Mods (idk which one is used)

        if(gui instanceof PauseScreen)
            if(AQGeneralConfig.aqButtonOnRight)
                return Set.of("menu.shareToLan", "menu.playerReporting"); // Open to LAN, Player Reporting
            else
                return Set.of("menu.options"); // Options...

        return null;
    }

}

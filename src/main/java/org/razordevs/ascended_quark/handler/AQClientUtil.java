package org.razordevs.ascended_quark.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZConfigChanged;

public class AQClientUtil {
    @LoadEvent
    public static void handleAQConfigChange(ZConfigChanged z) {
        Minecraft mc = Minecraft.getInstance();
        mc.submit(() -> {
            if(mc.hasSingleplayerServer() && mc.player != null && mc.getSingleplayerServer() != null)
                for(int i = 0; i < 3; i++)
                    mc.player.sendSystemMessage(Component.translatable("ascended_quark.misc.reloaded" + i).withStyle(i == 0 ? ChatFormatting.AQUA : ChatFormatting.WHITE));
        });
    }
}

package org.razordevs.ascended_quark.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.violetmoon.quark.base.QuarkClient;
import org.violetmoon.quark.base.client.config.QButtonHandler;
import org.violetmoon.quark.base.client.config.QuarkConfigHomeScreen;
import org.violetmoon.quark.base.client.handler.ClientUtil;
import org.violetmoon.quark.base.client.handler.InventoryButtonHandler;
import org.violetmoon.quark.base.client.handler.ModelHandler;
import org.violetmoon.quark.base.client.handler.QuarkProgrammerArtHandler;
import org.violetmoon.quark.base.handler.ContributorRewardHandler;
import org.violetmoon.quark.base.handler.WoodSetHandler;
import org.violetmoon.quark.mixin.mixins.client.accessor.AccessorMultiPlayerGameMode;

import java.time.LocalDateTime;
import java.time.Month;

public class ACClientProxy extends ACCommonProxy {

    public void start() {
        AQClient.start();
        AscendedQuark.ZETA.loadBus.subscribe(ModelHandler.class);
        super.start();

        //TODO: CONFIG SCREEN
        /*
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> {
            return new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
                return new QuarkConfigHomeScreen(screen);
            });
        });

         */
    }

    public InteractionResult clientUseItem(Player player, Level level, InteractionHand hand, BlockHitResult hit) {
        if (player instanceof LocalPlayer lPlayer) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.gameMode != null && mc.level != null) {
                if (!mc.level.getWorldBorder().isWithinBounds(hit.getBlockPos())) {
                    return InteractionResult.FAIL;
                }

                return ((AccessorMultiPlayerGameMode)mc.gameMode).quark$performUseItemOn(lPlayer, hand, hit);
            }
        }

        return InteractionResult.PASS;
    }

    public boolean isClientPlayerHoldingShift() {
        return Screen.hasShiftDown();
    }

    public float getVisualTime() {
        return QuarkClient.ticker.total;
    }

    public @Nullable RegistryAccess hackilyGetCurrentClientLevelRegistryAccess() {
        return QuarkClient.ZETA_CLIENT.hackilyGetCurrentClientLevelRegistryAccess();
    }
}

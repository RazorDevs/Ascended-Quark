package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.item.AetherItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.violetmoon.quark.base.client.config.QuarkConfigHomeScreen;
import org.violetmoon.quark.base.client.config.SocialButton;
import org.violetmoon.zeta.client.ZetaClient;
import org.violetmoon.zeta.client.config.screen.ZetaConfigHomeScreen;

//Based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
@Mixin(value = QuarkConfigHomeScreen.class)
abstract class QuarkConfigHomeScreenMixin extends ZetaConfigHomeScreen {

    public QuarkConfigHomeScreenMixin(ZetaClient parent, Screen screen) {
        super(parent, screen);
    }


    //Evil hack to not make the last config row shift to the left
    @ModifyVariable(method = "init", at = @At("STORE"), ordinal = 7)
    private int modify(int original) {
        return 1000;
    }

    @WrapOperation(method = "init", at = @At(value = "NEW", target = "(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/gui/components/Button$OnPress;)Lvazkii/quark/base/client/config/screen/widgets/IconButton;"))
    private SocialButton changeIconIfAether(int x, int y, int w, int h, Component text, ItemStack icon, Button.OnPress onClick, Operation<SocialButton> original) {
        if(text.getString().equals("The Aether")) {
            return new SocialButton(x, y, w, h, text, new ItemStack(AetherItems.AETHER_PORTAL_FRAME.get()), onClick);
        }
        else return original.call(x,y,w,h,text,icon,onClick);
    }
}
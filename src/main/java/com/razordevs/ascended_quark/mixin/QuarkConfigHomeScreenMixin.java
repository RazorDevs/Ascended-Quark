package com.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.item.AetherItems;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.quark.api.config.IConfigCategory;
import vazkii.quark.api.config.IConfigObject;
import vazkii.quark.base.client.config.screen.AbstractQScreen;
import vazkii.quark.base.client.config.screen.QuarkConfigHomeScreen;
import vazkii.quark.base.client.config.screen.widgets.CheckboxButton;
import vazkii.quark.base.client.config.screen.widgets.IconButton;

//Based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
@Mixin(value = QuarkConfigHomeScreen.class)
abstract class QuarkConfigHomeScreenMixin extends AbstractQScreen {

    public QuarkConfigHomeScreenMixin(Screen parent) {
        super(parent);
    }


    //Evil hack to not make the last config row shift to the left
    @ModifyVariable(method = "init", at = @At("STORE"), ordinal = 7, remap = false)
    private int modify(int original) {
        return 1000;
    }

    @WrapOperation(method = "init", at = @At(value = "NEW", target = "(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/gui/components/Button$OnPress;)Lvazkii/quark/base/client/config/screen/widgets/IconButton;"), remap = false)
    private IconButton changeIconIfAether(int x, int y, int w, int h, Component text, ItemStack icon, Button.OnPress onClick, Operation<IconButton> original) {
        if(text.getString().equals("The Aether")) {
            return new IconButton(x, y, w, h, text, new ItemStack(AetherItems.AETHER_PORTAL_FRAME.get()), onClick);
        }
        else return original.call(x,y,w,h,text,icon,onClick);
    }
}
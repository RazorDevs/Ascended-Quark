package com.razordevs.ascended_quark.mixin;

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
@Mixin(value = QuarkConfigHomeScreen.class, remap = false)
abstract class QuarkConfigHomeScreenMixin extends AbstractQScreen {


    @Shadow
    private static Component componentFor(IConfigCategory c) {
        return null;
    }

    @Shadow public abstract void commit(Button button);

    public QuarkConfigHomeScreenMixin(Screen parent) {
        super(parent);
    }


    @ModifyVariable(method = "init", at = @At("STORE"), ordinal = 7)
    private int modify(int original) {
        return 1000;
    }

/*

    @Overwrite
    protected void init() {
        super.init();

        final int perLine = 3;
        boolean addExternal = ExternalConfigHandler.instance.hasAny();

        int pad = 10;
        int vpad = 23;
        int bWidth = 120;
        int left = width / 2 - ((bWidth + pad) * perLine / 2) + 4;
        int vStart = 70;

        int i = 0;
        int catCount = ModuleCategory.values().length+1;
        if(addExternal)
            catCount++;

        boolean shiftedLeft = false;
        int useLeft = left;

        for(ModuleCategory category : ModuleCategory.values()) {
            if(!shiftedLeft && catCount - i < perLine && false) {
                useLeft = width / 2 - ((bWidth + pad) * (catCount - i) / 2);
                shiftedLeft = true;
            }

            int x = useLeft + (bWidth + pad) * (i % perLine);
            int y = vStart + (i / perLine) * vpad;

            IConfigCategory configCategory = IngameConfigHandler.INSTANCE.getConfigCategory(category);
            Component comp = componentFor(configCategory);

            Button icon = new IconButton(x, y, bWidth - 20, 20, comp, new ItemStack(category.item), categoryLink(configCategory));
            Button checkbox = new CheckboxButton(x + bWidth - 20, y, IngameConfigHandler.INSTANCE.getCategoryEnabledObject(category));

            addRenderableWidget(icon);
            addRenderableWidget(checkbox);

            System.out.println(category + " " + icon.x);
            System.out.println(category + " " + icon.x);

            if(category.requiredMod != null && !ModList.get().isLoaded(category.requiredMod)) {
                icon.active = false;
                checkbox.active = false;
            }

            i++;
        }

        IConfigCategory cat = IngameConfigHandler.INSTANCE.getConfigCategory(null);
        addRenderableWidget(new Button(useLeft + (bWidth + pad) * (i % perLine), vStart + (i / perLine) * vpad, bWidth, 20, componentFor(cat), categoryLink(cat)));
        i++;

        if(addExternal) {
            cat = ExternalConfigHandler.instance.mockCategory;
            addRenderableWidget(new Button(useLeft + (bWidth + pad) * (i % perLine), vStart + (i / perLine) * vpad, bWidth, 20, componentFor(cat), categoryLink(cat)));
        }

        bWidth = 200;
        addRenderableWidget(new Button(width / 2 - bWidth / 2, height - 30, bWidth, 20, Component.translatable("quark.gui.config.save"), this::commit));

        vStart = height - 55;
        bWidth = 20;
        pad = 5;
        left = (width - (bWidth + pad) * 5) / 2;
        addRenderableWidget(new SocialButton(left, vStart, Component.translatable("quark.gui.config.social.website"), 0x48ddbc, 0, webLink("https://quarkmod.net")));
        addRenderableWidget(new SocialButton(left + bWidth + pad, vStart, Component.translatable("quark.gui.config.social.discord"), 0x7289da, 1, webLink("https://discord.gg/vm")));
        addRenderableWidget(new SocialButton(left + (bWidth + pad) * 2, vStart, Component.translatable("quark.gui.config.social.patreon"), 0xf96854, 2, webLink("https://patreon.com/vazkii")));
        addRenderableWidget(new SocialButton(left + (bWidth + pad) * 3, vStart, Component.translatable("quark.gui.config.social.reddit"), 0xff4400, 3, webLink("https://reddit.com/r/quarkmod")));
        addRenderableWidget(new SocialButton(left + (bWidth + pad) * 4, vStart, Component.translatable("quark.gui.config.social.twitter"), 0x1da1f2, 4, webLink("https://twitter.com/VazkiiMods")));
    }
*/
}
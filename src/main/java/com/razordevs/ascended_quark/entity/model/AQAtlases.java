package com.razordevs.ascended_quark.entity.model;

import com.razordevs.ascended_quark.AscendedQuark;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AscendedQuark.MODID, value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AQAtlases {
    public static Material SKYROOT_CHEST_MATERIAL = getChestMaterial("skyroot/normal");
    public static Material SKYROOT_CHEST_LEFT_MATERIAL = getChestMaterial("skyroot/left");
    public static Material SKYROOT_CHEST_RIGHT_MATERIAL = getChestMaterial("skyroot/right");

    public static Material SKYROOT_TRAPPED_CHEST_MATERIAL = getChestMaterial("skyroot/trapped");
    public static Material SKYROOT_TRAPPED_CHEST_LEFT_MATERIAL = getChestMaterial("skyroot/trapped_left");
    public static Material SKYROOT_TRAPPED_CHEST_RIGHT_MATERIAL = getChestMaterial("skyroot/trapped_right");


    public static Material getChestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(AscendedQuark.MODID, "entity/chest/" + chestName));
    }

    @SubscribeEvent
    public static void onTextureStitchPre(TextureStitchEvent event) {
        if (event.getAtlas().location().equals(Sheets.CHEST_SHEET)) {
            event.addSprite(SKYROOT_CHEST_MATERIAL.texture());
            event.addSprite(SKYROOT_CHEST_LEFT_MATERIAL.texture());
            event.addSprite(SKYROOT_CHEST_RIGHT_MATERIAL.texture());
            event.addSprite(SKYROOT_TRAPPED_CHEST_MATERIAL.texture());
            event.addSprite(SKYROOT_TRAPPED_CHEST_LEFT_MATERIAL.texture());
            event.addSprite(SKYROOT_TRAPPED_CHEST_RIGHT_MATERIAL.texture());
        }
    }
}

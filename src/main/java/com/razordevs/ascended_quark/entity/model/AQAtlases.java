package com.razordevs.ascended_quark.entity.model;

import com.aetherteam.aether.block.AetherWoodTypes;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AscendedQuarkMod.MODID, value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AQAtlases {
    public static Material SKYROOT_CHEST_MATERIAL = getChestMaterial("skyroot_chest");
    //public static Material SKYROOT_CHEST_LEFT_MATERIAL = getChestMaterial("treasure_chest_left");
    //public static Material SKYROOT_CHEST_RIGHT_MATERIAL = getChestMaterial("treasure_chest_right");


    public static Material getChestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(AscendedQuarkMod.MODID, "entity/chest/" + chestName));
    }

    @SubscribeEvent
    public static void onTextureStitchPre(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(Sheets.CHEST_SHEET)) {
            event.addSprite(SKYROOT_CHEST_MATERIAL.texture());
            //event.addSprite(SKYROOT_CHEST_LEFT_MATERIAL.texture());
            //event.addSprite(SKYROOT_CHEST_RIGHT_MATERIAL.texture());
        }
    }
}

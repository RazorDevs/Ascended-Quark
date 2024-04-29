package org.razordevs.ascended_quark.entity.model;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.razordevs.ascended_quark.AscendedQuark;

@Mod.EventBusSubscriber(modid = AscendedQuark.MODID, value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AQAtlases {
    public static Material SKYROOT_CHEST_MATERIAL;
    public static Material SKYROOT_CHEST_LEFT_MATERIAL;
    public static Material SKYROOT_CHEST_RIGHT_MATERIAL;

    public static Material SKYROOT_TRAPPED_CHEST_MATERIAL;
    public static Material SKYROOT_TRAPPED_CHEST_LEFT_MATERIAL;
    public static Material SKYROOT_TRAPPED_CHEST_RIGHT_MATERIAL;


    public static Material getChestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(AscendedQuark.MODID, "entity/chest/" + chestName));
    }

    @SubscribeEvent
    public static void registerChestAtlases(FMLClientSetupEvent event) {
        event.enqueueWork(()-> {
            SKYROOT_CHEST_MATERIAL = getChestMaterial("skyroot/normal");
            SKYROOT_CHEST_LEFT_MATERIAL = getChestMaterial("skyroot/left");
            SKYROOT_CHEST_RIGHT_MATERIAL = getChestMaterial("skyroot/right");
            SKYROOT_TRAPPED_CHEST_MATERIAL = getChestMaterial("skyroot/trapped");
            SKYROOT_TRAPPED_CHEST_LEFT_MATERIAL = getChestMaterial("skyroot/trapped_left");
            SKYROOT_TRAPPED_CHEST_RIGHT_MATERIAL = getChestMaterial("skyroot/trapped_right");
        });
    }
}

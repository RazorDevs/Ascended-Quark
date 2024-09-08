package org.razordevs.ascended_quark;

//TODO: IMPLEMENT BETTER TABS HANDLING


import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = AscendedQuark.MODID)
public class AQTabs {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if(RegistryUtil.TABS.containsKey(tab)) {
            HashMap<ItemLike, Supplier<? extends ItemLike>> map = RegistryUtil.TABS.get(tab);

            for(ItemLike item : map.keySet()) {
                addToTab(map.get(item).get().asItem(), item.asItem(), event);
            }
        }
    }

    private static void addToTab(Item parent, Item stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
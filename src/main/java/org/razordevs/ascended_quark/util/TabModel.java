package org.razordevs.ascended_quark.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.function.Supplier;

public class TabModel {
    private ResourceKey<CreativeModeTab> tab;
    private final HashMap<ItemLike, Supplier<? extends ItemLike>> itemMap = new HashMap<>();

    public TabModel(ResourceKey<CreativeModeTab> tab, HashMap<ItemLike, Supplier<? extends ItemLike>> itemMap) {
        this.tab = tab;
        this.itemMap.putAll(itemMap);
    }

    public ResourceKey<CreativeModeTab> getTab() {
        return tab;
    }

    public HashMap<ItemLike, Supplier<? extends ItemLike>> getItemMap() {
        return itemMap;
    }

    public void add(ItemLike item, Supplier<? extends ItemLike> supplier) {
        itemMap.put(item, supplier);
    }
}

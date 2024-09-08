package org.razordevs.ascended_quark;

//TODO: IMPLEMENT BETTER TABS HANDLING
/*

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = AscendedQuark.MODID)
public class AQTabs {
    @SubscribeEvent
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if (tab == AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey()) {
            for(Block item : TABS_PAIR_BUILDING.keySet())  {
                addToTab(TABS_PAIR_BUILDING.get(item).get(), item, event);
            }

            TABS_PAIR_BUILDING.clear();
        }

        if (tab == AetherCreativeTabs.AETHER_DUNGEON_BLOCKS.getKey())
            addToTab(AetherBlocks.CHEST_MIMIC.get().asItem(), dungeonBlocks, event);

        if (tab == AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES.getKey()) {
            for(Item item : TABS_PAIR_EQUIPMENT.keySet())  {
                addToTab(TABS_PAIR_EQUIPMENT.get(item).get(), item, event);
            }
            TABS_PAIR_EQUIPMENT.clear();
        }

        if (tab == AetherCreativeTabs.AETHER_INGREDIENTS.getKey()) {
            for (Item item : TABS_PAIR_INGREDIENTS.keySet()) {
                addToTab(TABS_PAIR_INGREDIENTS.get(item).get(), item, event);
            }
            TABS_PAIR_INGREDIENTS.clear();
        }

        if (tab == AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey()) {
            for (Block item : TABS_PAIR_NATURAL.keySet()) {
                System.out.println("aaasawqa<");
                addToTab(TABS_PAIR_NATURAL.get(item).get(), item, event);
            }
            TABS_PAIR_NATURAL.clear();
        }

    }

    private static void addToTab(Item parent, Item stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
    private static void addToTab(Block parent, Block stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }


    private static void addToTab(Item parent, List<Block> stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack.get(0)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        for (int i = 1; i < stack.size(); i++) {
            event.getEntries().putAfter(new ItemStack(stack.get(i-1)), new ItemStack(stack.get(i)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}*/
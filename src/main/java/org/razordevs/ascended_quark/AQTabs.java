package org.razordevs.ascended_quark;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.razordevs.ascended_quark.items.AQItems;

import static org.razordevs.ascended_quark.blocks.AQBlocks.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = AscendedQuark.MODID)
public class AQTabs {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if (tab == AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey()) {
            addToTab(AetherBlocks.SKYROOT_BUTTON.get().asItem(), blockList(skyrootBlocks()), event);
            addToTab(AetherBlocks.GOLDEN_OAK_WOOD.get().asItem(), blockList(goldenOakBlocks()), event);
            addToTabBefore(AetherBlocks.SKYROOT_LOG.get().asItem(), blockList(aetherDirtBlocks()), event);
            addToTab(AetherBlocks.HOLYSTONE_WALL.get().asItem(), blockList(holystoneBlocks()), event);
            addToTab(AetherBlocks.ICESTONE_WALL.get().asItem(), blockList(icestoneBlocks()), event);
            addToTab(AetherBlocks.QUICKSOIL_GLASS_PANE.get().asItem(), blockList(quicksoilBlocks()), event);
            addToTab(AetherBlocks.AEROGEL_WALL.get().asItem(), blockList(aerogelBlocks()), event);
        }

        if (tab == AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey())
            addToTab(AetherBlocks.CHEST_MIMIC.get().asItem(), blockList(functionalBlocks()), event);

        if (tab == AetherCreativeTabs.AETHER_DUNGEON_BLOCKS.getKey())
            addToTab(AetherBlocks.CHEST_MIMIC.get().asItem(), blockList(dungeonBlocks()), event);

        if (tab == AetherCreativeTabs.AETHER_EQUIPMENT_AND_UTILITIES.getKey()) {
            addToTab(AetherItems.SKYROOT_TADPOLE_BUCKET.get(), new Item[]{
                    AQItems.SLIME_IN_A_SKYROOT_BUCKET_ITEM.get(),
                    AQItems.BLUE_SWET_IN_A_BUCKET_ITEM.get(),
                    AQItems.BLUE_SWET_IN_A_SKYROOT_BUCKET_ITEM.get(),
                    AQItems.GOLDEN_SWET_IN_A_BUCKET_ITEM.get(),
                    AQItems.GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM.get()
            }, event);

            addToTab(AetherItems.ENCHANTED_DART.get(), AQItems.AMBROSIUM_TORCH_ARROW.get(), event);
        }

        if (tab == AetherCreativeTabs.AETHER_INGREDIENTS.getKey())
            addToTab(AetherItems.SWET_BALL.get(), AQItems.QUICKSOIL_GLASS_SHARD.get(), event);

    }

    private static void addToTab(Item parent, Item stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void addToTab(Item parent, Item[] stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack[0]), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        for (int i = 1; i < stack.length; i++) {
            event.getEntries().putAfter(new ItemStack(stack[i-1]), new ItemStack(stack[i]), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private static void addToTab(Item parent, Block[] stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(new ItemStack(parent), new ItemStack(stack[0]), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        for (int i = 1; i < stack.length; i++) {
            event.getEntries().putAfter(new ItemStack(stack[i-1]), new ItemStack(stack[i]), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private static void addToTabBefore(Item parent, Block[] stack, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putBefore(new ItemStack(parent), new ItemStack(stack[0]), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        for (int i = 1; i < stack.length; i++) {
            event.getEntries().putAfter(new ItemStack(stack[i-1]), new ItemStack(stack[i]), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}

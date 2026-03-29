package org.razordevs.ascended_quark;

//TODO: IMPLEMENT BETTER TABS HANDLING

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.razordevs.ascended_quark.util.TabModel;

import java.util.HashMap;
import java.util.function.Supplier;

@EventBusSubscriber(value = Dist.CLIENT, modid = AscendedQuark.MODID)
public class AQTabs {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
		ResourceKey<CreativeModeTab> tab = event.getTabKey();

		// Applies generated items to their respective CreativeTab
		for (TabModel tm : RegistryUtil.TABS) {
			if (tm.getTab().equals(tab)) {
				// If the iterated tab is the current one, get all the items and their parents
				// for registration
				HashMap<ItemLike, Supplier<? extends ItemLike>> map = tm.getItemMap();

				// For each item register it after their parent
				for (ItemLike item : map.keySet()) {
					addToTab(map.get(item).get().asItem(), item.asItem(), event);
				}
			}
		}
	}

	private static void addToTab(Item parent, Item stack, BuildCreativeModeTabContentsEvent event) {

		if (event.getParentEntries().contains((new ItemStack(parent)))
				&& !event.getParentEntries().contains((new ItemStack(stack)))
				&& !event.getSearchEntries().contains((new ItemStack(stack)))) {
			event.insertAfter(new ItemStack(parent), new ItemStack(stack),
					CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}
	}
}
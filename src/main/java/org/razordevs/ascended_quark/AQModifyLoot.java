package org.razordevs.ascended_quark;

import com.aetherteam.aether.loot.AetherLoot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.razordevs.ascended_quark.module.AQPickarangModule;
import org.razordevs.ascended_quark.util.RegistryUtil;

@Mod.EventBusSubscriber(modid = AscendedQuark.MODID)
public class AQModifyLoot {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void buildLootTables(LootTableLoadEvent event) {
        RegistryUtil.registerModifiedLootTable(AetherLoot.BRONZE_DUNGEON, AQPickarangModule.valk_pickarang, 5, 1, event);
        RegistryUtil.registerModifiedLootTable(AetherLoot.SILVER_DUNGEON, AQPickarangModule.phoenix_flamerang, 5, 1, event);
    }
}

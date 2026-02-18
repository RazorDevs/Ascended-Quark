package org.razordevs.ascended_quark.datagen.builders.loot.modifiers;

import com.aetherteam.aether.loot.AetherLoot;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.module.AQPickarangModule;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AQLootDataProvider extends GlobalLootModifierProvider {

    private final HashMap<String, Item> itemMap;

    public AQLootDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, HashMap<String, Item> itemMap)
    {
        super(output, provider, AscendedQuark.MODID);
        this.itemMap = itemMap;
    }

    @Override
    protected void start() {
        add("pickarang_bronze_loot_modifiers", new AQDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(AQPickarangModule.valk_pickarang.asItem()), 1)
                ),
                110,
                0.01f
        ));

        add("pickarang_silver_loot_modifiers", new AQDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON.location()).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(AQPickarangModule.valk_pickarang.asItem()), 1),
                        WeightedEntry.wrap(new ItemStack(AQPickarangModule.phoenix_flamerang.asItem()), 1)

                ),
                110,
                0.01f
        ));
    }
}

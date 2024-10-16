package org.razordevs.ascended_quark.datagen.loot.modifiers;

import com.aetherteam.aether.loot.AetherLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.razordevs.ascended_quark.AscendedQuark;

import java.util.List;

public class AQLootDataProvider extends GlobalLootModifierProvider {

    public AQLootDataProvider(PackOutput output)
    {
        super(output, AscendedQuark.MODID);
    }

    @Override
    protected void start() {
        add("silver_loot_modifiers", new AQAddDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON).build() },
                List.of(
                ),
                100,
                0.0f
        ));
    }
}

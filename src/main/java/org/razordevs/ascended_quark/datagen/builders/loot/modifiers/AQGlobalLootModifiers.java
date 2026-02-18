package org.razordevs.ascended_quark.datagen.builders.loot.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.razordevs.ascended_quark.AscendedQuark;

public class AQGlobalLootModifiers {
    public static DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AscendedQuark.MODID);

    public static DeferredHolder<MapCodec<? extends IGlobalLootModifier>, ? extends MapCodec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("pickarang_aether_dungeon_loot", AQDungeonLootModifier.CODEC);

}

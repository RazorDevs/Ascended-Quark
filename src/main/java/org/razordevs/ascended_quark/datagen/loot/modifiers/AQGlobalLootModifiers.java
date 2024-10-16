package org.razordevs.ascended_quark.datagen.loot.modifiers;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.razordevs.ascended_quark.AscendedQuark;

public class AQGlobalLootModifiers {
    public static DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AscendedQuark.MODID);

    public static RegistryObject<Codec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_dungeon_loot", AQAddDungeonLootModifier.CODEC);
}
